// SharedLibrary/src/fnComponents/workflow/StagePostDeploymentAction.groovy
package fnComponents.workflow;

import java.text.MessageFormat
import entity.EntityPipelineModel
import enumType.EnumStageToRunTest
import static utils.common.Helpers.*
import static utils.common.RecordTestReport.*
import static utils.common.EmailNotification.*

class StagePostDeploymentAction {
    public static def PLPostDeploymentAction(pipeline_script, Integer stageIndex, pipelineSettings, EntityPipelineModel pipelineModel, Map emailParams) {
        def postDeploymentActionParams = pipelineSettings['StageParams']['PostDeploymentAction']
        
        try {
            def scmRepoListJsonString = ConvertGroovyObjToJsonString(pipelineModel.Request.SourceList)

            // // Run Saint Scan:
            // if(pipelineModel.Request.Scan.Saint) {
            //     pipeline_script.echo "Run Saint Scan ..."
            // }

            // Run post-deployment tests:
            def postDeploymentTestPSScriptPath = postDeploymentActionParams['PSScriptPath']
            def postDeploymentPSCmdToExecute = ". '${postDeploymentTestPSScriptPath}'; InvokePostDeploymentTest -SCMRepoListJsonString '${scmRepoListJsonString}'"
            def psResponseJsonString = pipeline_script.powershell(returnStdout: true, script: postDeploymentPSCmdToExecute)
            def psResponseGroovyObj = ConvertJsonStringToGroovyObj(psResponseJsonString)
            pipelineModel.Request.SourceList = psResponseGroovyObj.Result
            emailParams.put("SourceList", psResponseGroovyObj.Result)    // Bind "PostDeploymentTestStatus" to SourceList
            if(psResponseGroovyObj.ExitCode == 0) {
                SetEmailStageInfo(emailParams, stageIndex, true)
            } else {
                throw new Exception(psResponseGroovyObj.ExceptionMsg)
            }
        } catch(Exception ex) {
            SetEmailStageInfo(emailParams, stageIndex, false)
            throw ex
        }
    }
}