// SharedLibrary/src/fnComponents/workflow/StepCleanupWorkspace.groovy
package fnComponents.workflow;

class StepCleanupWorkspace {
    public static def CleanupWorkspace(pipeline_script, pipelineSettings) {
        // def cleanupWorkspaceParams = pipelineSettings['StepParams']['CleanupWorkspace']

        try {
            // def cleanupWorkspacePSScriptPath = cleanupWorkspaceParams['PSScriptPath']
            // def cleanupWorkspacePSCmdToExecute = ". '${cleanupWorkspacePSScriptPath}'; InvokeCleanupWorkspace" + 
            //                                         " -ProjectPipelineWorkspace '${buildOutcomeFolderPath}'" + 
            //                                         " -MaxKeepsOfBuilds '${nexusDirForBuildOutcomeFolder}'"
            // pipeline_script.powershell(cleanupWorkspacePSCmdToExecute)


            // Cleanup Workspace ...
            pipeline_script.echo "Cleanup Workspace ..."
            pipeline_script.cleanWs notFailBuild: true
        } catch(Exception ex) {
            throw ex
        }
    }
}