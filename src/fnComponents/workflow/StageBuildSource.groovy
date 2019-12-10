// SharedLibrary/src/fnComponents/workflow/StageBuildSource.groovy
package fnComponents.workflow;

import java.text.MessageFormat
import entity.EntityPipelineModel
import static utils.common.EmailNotification.*
import static utils.common.Helpers.*

class StageBuildSource {
    public static def PLBuildSource(pipeline_script, Integer stageIndex, pipelineSettings, EntityPipelineModel pipelineModel, Map emailParams) {
        def buildSourceParams = pipelineSettings['StageParams']['BuildSource']

        try {
            def scmRepoListJsonString = ConvertGroovyObjToJsonString(pipelineModel.Request.SourceList)
            def packageRepoSources = buildSourceParams['PackageRepoSources']

            // Build source for functional and test components:
            def buildSourcePSScriptPath = buildSourceParams['PSScriptPath']
            def buildSourcePSCmdToExecute = ". '${buildSourcePSScriptPath}'; InvokeBuildSource -SCMRepoListJsonString '${scmRepoListJsonString}' -PackageRepoSources '${packageRepoSources}'"
            def psResponseJsonString = pipeline_script.powershell(returnStdout: true, script: buildSourcePSCmdToExecute)
            def psResponseGroovyObj = ConvertJsonStringToGroovyObj(psResponseJsonString)
            pipelineModel.Request.SourceList = psResponseGroovyObj.Result
            emailParams.put("SourceList", psResponseGroovyObj.Result)    // Bind "BuildStatus" to SourceList
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