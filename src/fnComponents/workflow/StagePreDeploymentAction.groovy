// SharedLibrary/src/fnComponents/workflow/StagePreDeploymentAction.groovy
package fnComponents.workflow;

import java.text.MessageFormat
import entity.EntityPipelineModel
import enumType.EnumStageToRunTest
import enumType.EnumTestFrameworkType
import enumType.EnumTestFrameworkValue
import static utils.common.Helpers.*
import static utils.common.RecordTestReport.*
import static utils.common.EmailNotification.*

class StagePreDeploymentAction {
    public static def PLPreDeploymentAction(pipeline_script, Integer stageIndex, pipelineSettings, EntityPipelineModel pipelineModel, Map emailParams) {
        def initializationParams = pipelineSettings['StageParams']['Initialization']
        def preDeploymentActionParams = pipelineSettings['StageParams']['PreDeploymentAction']
        
        try {
            def scmRepoListJsonString = ConvertGroovyObjToJsonString(pipelineModel.Request.SourceList)

            // Run pre-deployment tests:
            def preDeploymentTestPSScriptPath = preDeploymentActionParams['PSScriptPath']
            def preDeploymentPSCmdToExecute = ". '${preDeploymentTestPSScriptPath}'; InvokePreDeploymentTest -SCMRepoListJsonString '${scmRepoListJsonString}'"
            def psResponseJsonString = pipeline_script.powershell(returnStdout: true, script: preDeploymentPSCmdToExecute)
            def psResponseGroovyObj = ConvertJsonStringToGroovyObj(psResponseJsonString)
            pipelineModel.Request.SourceList = psResponseGroovyObj.Result
            emailParams.put("SourceList", psResponseGroovyObj.Result)    // Bind "PreDeploymentTestStatus" to SourceList
            if(psResponseGroovyObj.ExitCode == 0) {
                SetEmailStageInfo(emailParams, stageIndex, true)
            } else {
                throw new Exception(psResponseGroovyObj.ExceptionMsg)
            }
        } catch(Exception ex) {
            SetEmailStageInfo(emailParams, stageIndex, false)
            throw ex
        } finally {
            // Generate reports for test results:
            pipelineModel.Request.SourceList.collect { scmRepo ->
                scmRepo.CompAfterSave.TestCompList.collect { testComp ->
                    if(testComp.Selected && testComp.OperationalProp.RunConfiguration.Selected && testComp.OperationalProp.RunConfiguration.WhenToRun == EnumStageToRunTest.PreDeployment.name()) {
                        pipeline_script.echo "Record pre-deployment test reports for ${testComp.IntrinsicProp.Name} ..."
                        def testCompIdentifier = GetSourceCompIdentifier(testComp.IntrinsicProp.Category, testComp.IntrinsicProp.Name)
                        def testResultFolderRelativePath = "${initializationParams['WsTokenForPreDeploymentTestResult']}/${testCompIdentifier}"
                        switch(testComp.IntrinsicProp.TestFramework.Value) {
                            case EnumTestFrameworkValue.MSTest.name():
                                RecordVSTestReport(pipeline_script, testResultFolderRelativePath); break;
                            case EnumTestFrameworkValue.xUnit.name():
                                RecordVSTestReport(pipeline_script, testResultFolderRelativePath); break;
                            case EnumTestFrameworkValue.NUnit.name():
                                RecordVSTestReport(pipeline_script, testResultFolderRelativePath); break;
                        }
                    }
                }
            }
        }
    }
}