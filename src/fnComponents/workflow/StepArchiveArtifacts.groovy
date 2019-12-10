// SharedLibrary/src/fnComponents/workflow/StepArchiveArtifacts.groovy
package fnComponents.workflow;

import java.text.MessageFormat
import entity.EntityPipelineModel

class StepArchiveArtifacts {
    public static def ArchiveArtifacts(pipeline_script, pipelineSettings, EntityPipelineModel pipelineModel) {
        def archiveArtifactsParams = pipelineSettings['StepParams']['ArchiveArtifacts']

        try {
            def archiveArtifactsPSScriptPath = archiveArtifactsParams['PSScriptPath']
            def urlToUploadSingleFile = archiveArtifactsParams['UrlToUploadSingleFile']
            def usernameForNexusRepo = archiveArtifactsParams['UsernameForNexusRepo']
            def passwordForNexusRepo = archiveArtifactsParams['PasswordForNexusRepo']

            def projectName = pipelineModel.Request.Project.Name
            def buildEnvironmentSDLCToken = pipelineModel.Request.Environment.SDLCToken
            def buildEnvironmentInstanceName = pipelineModel.Request.Environment.DeployInstance.Name
            def buildFrequency = pipelineModel.Request.Environment.BuildFrequency
            def buildIdentifier = MessageFormat.format("BN@{0}_RId@{1}", "${pipeline_script.env.BUILD_NUMBER}", "${pipeline_script.params.RequestID}")

            // Archive Build Outcome:
            pipeline_script.echo "Archive Build Outcome to Nexus ..."
            def buildOutcomeFolderPath = pipelineModel.TempFolderPathInWorkspace
            def nexusDirForBuildOutcomeFolder = "${projectName}/${buildEnvironmentSDLCToken}/${buildEnvironmentInstanceName}/${buildFrequency}/${buildIdentifier}/BuildOutcome"
            def archiveBuildOutcomePSCmdToExecute = ". '${archiveArtifactsPSScriptPath}';" + 
                                                    " InvokeArchiveArtifacts -FolderPath '${buildOutcomeFolderPath}'" + 
                                                    " -NexusDirForFolder '${nexusDirForBuildOutcomeFolder}'" + 
                                                    " -UrlToUploadSingleFile '${urlToUploadSingleFile}'" + 
                                                    " -UsernameForNexusRepo '${usernameForNexusRepo}'" + 
                                                    " -PasswordForNexusRepo '${passwordForNexusRepo}'"
            pipeline_script.powershell(archiveBuildOutcomePSCmdToExecute)

            // Archive SDLC Documents:
            pipeline_script.echo "Archive SDLC Documents to Nexus ..."
            def sdlcDocumentsFolderPath = MessageFormat.format(archiveArtifactsParams['SDLCDocumentsFolderPath'], projectName, buildEnvironmentSDLCToken, buildEnvironmentInstanceName, buildFrequency, "Request--${pipeline_script.params.RequestID}")
            def nexusDirForSDLCDocumentsFolder = "${projectName}/${buildEnvironmentSDLCToken}/${buildEnvironmentInstanceName}/${buildFrequency}/${buildIdentifier}/SDLCDocuments"
            def archiveSDLCDocumentsPSCmdToExecute = ". '${archiveArtifactsPSScriptPath}';" + 
                                                    " InvokeArchiveArtifacts -FolderPath '${sdlcDocumentsFolderPath}'" + 
                                                    " -NexusDirForFolder '${nexusDirForSDLCDocumentsFolder}'" + 
                                                    " -UrlToUploadSingleFile '${urlToUploadSingleFile}'" + 
                                                    " -UsernameForNexusRepo '${usernameForNexusRepo}'" + 
                                                    " -PasswordForNexusRepo '${passwordForNexusRepo}'"
            pipeline_script.powershell(archiveSDLCDocumentsPSCmdToExecute)
        } catch(Exception ex) {
            throw ex
        }
    }
}