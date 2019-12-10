// SharedLibrary/src/fnComponents/validation/StageInitialization.groovy
package fnComponents.validation;

class StageInitialization {
    public static def ValidateStageInitialization(pipeline_script, requiredPlugins, requiredClis) {
        // Check if plugins that need to be used in pipeline are installed:
        def installedPlugins = jenkins.model.Jenkins.instance.getPluginManager().getPlugins().collect { it.getShortName() }
        requiredPlugins.collect { plugin ->
            if(!(plugin in installedPlugins)) {
                throw new Exception("Plugin ${plugin} is not installed, please install it and continue.")
            }
        }
        // // Check if clis that need to be used in pipeline are installed:
        // requiredClis.collect { cli ->
        //     def psCmdToExecute = "Get-Command ${cli} -ErrorAction SilentlyContinue"
        //     def cliInfo = pipeline_script.powershell(returnStdout: true, script: psCmdToExecute)
        //     if(cliInfo.trim() == "") {
        //         throw new Exception("Utility ${cli} is not installed, please install it and continue.")
        //     }
        // }
    }
}