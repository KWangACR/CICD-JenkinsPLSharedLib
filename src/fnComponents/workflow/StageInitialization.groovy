// SharedLibrary/src/fnComponents/workflow/StageInitialization.groovy
package fnComponents.workflow;

import static utils.common.GeneralCheckout.*
import static utils.common.EmailNotification.*
import entity.EntityPipelineModel
import static fnComponents.validation.StageInitialization.*

class StageInitialization {
    public static def PLInitialization(pipeline_script, Integer stageIndex, pipelineSettings, String jenkinsWorkspace, EntityPipelineModel pipelineModel, Map emailParams) {
        def commonParams = pipelineSettings['CommonParams']
        def initializationParams = pipelineSettings['StageParams']['Initialization']
        
        try {
            // Check if all required plugins and clis are properly installed:
            ValidateStageInitialization(pipeline_script, initializationParams['RequiredPlugins'], initializationParams['RequiredClis'])

            // Bind folder info to pipelineModel:
            pipelineModel.BuildOutcomeFolderPathInWorkspace = "${jenkinsWorkspace}/${initializationParams['WsTokenForBuildOutcome']}"
            pipelineModel.NugetPackageOutputFolderPathInWorkspace = "${jenkinsWorkspace}/${initializationParams['WsTokenForNugetPackageOutput']}"
            pipelineModel.TempFolderPathInWorkspace = "${jenkinsWorkspace}/${initializationParams['WsTokenForTemp']}"
            pipelineModel.PreBuildTestResultFolderPathInWorkspace = "${jenkinsWorkspace}/${initializationParams['WsTokenForPreBuildTestResult']}"
            pipelineModel.PreDeploymentTestResultFolderPathInWorkspace = "${jenkinsWorkspace}/${initializationParams['WsTokenForPreDeploymentTestResult']}"
            pipelineModel.PostDeploymentTestResultFolderPathInWorkspace = "${jenkinsWorkspace}/${initializationParams['WsTokenForPostDeploymentTestResult']}"

            // Create folders in Jenkins Workspace:
            pipeline_script.echo "Setup folder hierarchy in workspace ..."
            new File(pipelineModel.BuildOutcomeFolderPathInWorkspace).mkdirs()
            new File(pipelineModel.NugetPackageOutputFolderPathInWorkspace).mkdirs()
            new File(pipelineModel.TempFolderPathInWorkspace).mkdirs()
            new File(pipelineModel.PreBuildTestResultFolderPathInWorkspace).mkdirs()
            new File(pipelineModel.PreDeploymentTestResultFolderPathInWorkspace).mkdirs()
            new File(pipelineModel.PostDeploymentTestResultFolderPathInWorkspace).mkdirs()

            SetEmailStageInfo(emailParams, stageIndex, true)
        } catch (Exception ex) {
            SetEmailStageInfo(emailParams, stageIndex, false)
            throw ex
        }
    }
}