// SharedLibrary/src/common/GeneralCheckout.groovy
package utils.common;

class GeneralCheckout {
    public static def CheckoutFromSubversion(pipeline_script, String credentialsId, String remoteUri, String localRelativePath) {
        pipeline_script.checkout([$class: 'SubversionSCM', 
                                    additionalCredentials: [], 
                                    excludedCommitMessages: '', 
                                    excludedRegions: '', 
                                    excludedRevprop: '', 
                                    excludedUsers: '', 
                                    filterChangelog: false, 
                                    ignoreDirPropChanges: false, 
                                    includedRegions: '', 
                                    locations: [[cancelProcessOnExternalsFail: true, 
                                                    credentialsId: credentialsId, 
                                                    depthOption: 'infinity', 
                                                    ignoreExternalsOption: true, 
                                                    local: localRelativePath, 
                                                    remote: remoteUri]], 
                                    quietOperation: true, 
                                    workspaceUpdater: [$class: 'UpdateUpdater']])
    }
    public static def CheckoutFromGit(pipeline_script) {

    }
}