// SharedLibrary/src/fnComponents/workflow/StageCheckoutSource.groovy
package fnComponents.workflow;

import java.text.MessageFormat
import entity.EntityPipelineModel
import enumType.EnumSCMType
import static utils.common.GeneralCheckout.*
import static utils.common.EmailNotification.*

class StageCheckoutSource {
    public static def PLCheckoutSource(pipeline_script, Integer stageIndex, pipelineSettings, EntityPipelineModel pipelineModel, Map emailParams) {
        def checkoutSourceParams = pipelineSettings['StageParams']['CheckoutSource']

        try {
            def checkoutSourcePSScriptPath = checkoutSourceParams['PSScriptPath']

            // Download/Update source code:
            pipelineModel.Request.SourceList.collect { scmRepo ->
                def repoUrl = scmRepo.RepoInfo.Url
                pipeline_script.echo "Download/Update source code from ${repoUrl} ..."
                def repoProtocol = scmRepo.RepoInfo.Protocol
                def repoSpecifierType = scmRepo.RepoInfo.Specifier.Type
                def srcDestFolderPath = scmRepo.RepoInfo.RootSourceDirForPipelineExecution

                // If user doesn't specify repo specifier value and leave it as default, read it and bind it to scmRepo object so that it can be displayed in email:
                switch(repoProtocol) {
                    case EnumSCMType.Subversion.name():
                        if(scmRepo.RepoInfo.Specifier.Value == "") {
                            def headRevisionOfSubversionRepo = pipeline_script.powershell(returnStdout: true, script: "Write-Host 'Get HEAD Revision ...'; svn.exe info '${repoUrl}' --show-item revision")
                            scmRepo.RepoInfo.Specifier.Value = headRevisionOfSubversionRepo.trim()
                        };
                        break;
                    case EnumSCMType.Git.name():
                        break;
                }
                def repoSpecifierValue = scmRepo.RepoInfo.Specifier.Value
                
                def checkoutSourcePSCmdToExecute = ". '${checkoutSourcePSScriptPath}'; DownloadOrUpdateSrc -RepoUrl '${repoUrl}' -RepoProtocol '${repoProtocol}' -RepoSpecifierType '${repoSpecifierType}' -RepoSpecifierValue '${repoSpecifierValue}' -SrcDestFolderPath '${srcDestFolderPath}'"
                pipeline_script.powershell(checkoutSourcePSCmdToExecute)
            }
            
            // Download/Update configs for all components:
            def buildEnvironmentSDLCToken = pipelineModel.Request.Environment.SDLCToken
            def buildEnvironmentInstanceName = pipelineModel.Request.Environment.DeployInstance.Name
            def buildFrequency = pipelineModel.Request.Environment.BuildFrequency
            def repoUrl = MessageFormat.format(checkoutSourceParams['SVNRootPathForConfigs'][pipelineModel.Request.Project.Name], buildEnvironmentSDLCToken, buildEnvironmentInstanceName, buildFrequency)
            pipeline_script.echo "Download/Update configs for all components from ${repoUrl} ..."
            def repoProtocol = EnumSCMType.Subversion.name()
            def repoSpecifierType = "Revision"
            def repoSpecifierValue = ""
            def srcDestFolderPath = pipelineModel.Request.Environment.RootConfigDir
            def checkoutSourcePSCmdToExecute = ". '${checkoutSourcePSScriptPath}'; DownloadOrUpdateSrc -RepoUrl '${repoUrl}' -RepoProtocol '${repoProtocol}' -RepoSpecifierType '${repoSpecifierType}' -RepoSpecifierValue '${repoSpecifierValue}' -SrcDestFolderPath '${srcDestFolderPath}'"
            pipeline_script.powershell(checkoutSourcePSCmdToExecute)

            SetEmailStageInfo(emailParams, stageIndex, true)
        } catch (Exception ex) {
            SetEmailStageInfo(emailParams, stageIndex, false)
            throw ex
        }
    }
}