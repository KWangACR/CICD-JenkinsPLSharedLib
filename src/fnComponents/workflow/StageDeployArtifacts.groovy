// SharedLibrary/src/fnComponents/workflow/StageDeployArtifacts.groovy
package fnComponents.workflow;

import java.text.MessageFormat
import entity.EntityPipelineModel
import static utils.common.EmailNotification.*
import static utils.common.Helpers.*

class StageDeployArtifacts {
    public static def PLDeployArtifacts(pipeline_script, Integer stageIndex, pipelineSettings, EntityPipelineModel pipelineModel, Map emailParams) {
        def deployArtifactsParams = pipelineSettings['StageParams']['DeployArtifacts']
        
        try {
            def scmRepoListJsonString = ConvertGroovyObjToJsonString(pipelineModel.Request.SourceList)

            // Publish artifacts:
            def publishArtifactsPSScriptPath = deployArtifactsParams['PSScriptPath']['Publish']
            def packageSourceRepoUrl = deployArtifactsParams['PackageSourceRepoUrl']
            def packageSourceRepoApiKey = deployArtifactsParams['PackageSourceRepoApiKey']
            def publishArtifactsPSCmdToExecute = ". '${publishArtifactsPSScriptPath}'; InvokePublishArtifacts -SCMRepoListJsonString '${scmRepoListJsonString}' -PackageSourceRepoUrl '${packageSourceRepoUrl}' -PackageSourceRepoApiKey '${packageSourceRepoApiKey}'"
            def publishPSResponseJsonString = pipeline_script.powershell(returnStdout: true, script: publishArtifactsPSCmdToExecute)
            def publishPSResponseGroovyObj = ConvertJsonStringToGroovyObj(publishPSResponseJsonString)
            pipelineModel.Request.SourceList = publishPSResponseGroovyObj.Result
            emailParams.put("SourceList", publishPSResponseGroovyObj.Result)    // Bind "DeployStatus" to SourceList
            if(publishPSResponseGroovyObj.ExitCode == 0) {
                SetEmailStageInfo(emailParams, stageIndex, true)
            } else {
                throw new Exception(publishPSResponseGroovyObj.ExceptionMsg)
            }


            // Deploy artifacts:
            def deployArtifactsPSScriptPath = ""
            if(pipelineSettings['CommonParams']['IsLocalEnvironment']) {
                deployArtifactsPSScriptPath = deployArtifactsParams['PSScriptPath']['Deploy']['Local']    // To run pipeline on local machine, winRM session will not be established.
            } else {
                deployArtifactsPSScriptPath = deployArtifactsParams['PSScriptPath']['Deploy']['Remote']    // To run pipeline on remote servers, winRM session will be established.
            }
            def deployArtifactsPSCmdToExecute = ". '${deployArtifactsPSScriptPath}'; InvokeDeployArtifacts -SCMRepoListJsonString '${scmRepoListJsonString}'"
            def deployPSResponseJsonString = pipeline_script.powershell(returnStdout: true, script: deployArtifactsPSCmdToExecute)
            def deployPSResponseGroovyObj = ConvertJsonStringToGroovyObj(deployPSResponseJsonString)
            pipelineModel.Request.SourceList = deployPSResponseGroovyObj.Result
            emailParams.put("SourceList", deployPSResponseGroovyObj.Result)    // Bind "DeployStatus" to SourceList
            if(deployPSResponseGroovyObj.ExitCode == 0) {
                SetEmailStageInfo(emailParams, stageIndex, true)
            } else {
                throw new Exception(deployPSResponseGroovyObj.ExceptionMsg)
            }
        } catch(Exception ex) {
            SetEmailStageInfo(emailParams, stageIndex, false)
            throw ex
        }
    }
}