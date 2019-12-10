// SharedLibrary/src/utils/stage/StagePreBuildAction.groovy
package utils.stage;

import entity.EntityCheckmarxVulnerabilityThreshold

// Define utility methods that will be used in stage "Pre-Build Action":
class StagePreBuildAction {
    // Run checkmarx scan using Jenkins Checkmarx plugin:
    public static def CheckmarxScan(pipeline_script, String credentialsId, String serverUri, String projectName, Integer highThreshold, Integer mediumThreshold, Integer lowThreshold) {
        pipeline_script.step([$class: 'CxScanBuilder', 
                                comment: '', 
                                credentialsId: credentialsId, 
                                excludeFolders: '', 
                                excludeOpenSourceFolders: '', 
                                exclusionsSetting: 'job', 
                                failBuildOnNewResults: true, 
                                failBuildOnNewSeverity: 'HIGH', 
                                filterPattern: '''!**/_cvs/**/*, !**/.svn/**/*,   !**/.hg/**/*,   !**/.git/**/*,  !**/.bzr/**/*, !**/bin/**/*,
                                                !**/obj/**/*,  !**/backup/**/*, !**/.idea/**/*, !**/*.DS_Store, !**/*.ipr,     !**/*.iws,
                                                !**/*.bak,     !**/*.tmp,       !**/*.aac,      !**/*.aif,      !**/*.iff,     !**/*.m3u, !**/*.mid, !**/*.mp3,
                                                !**/*.mpa,     !**/*.ra,        !**/*.wav,      !**/*.wma,      !**/*.3g2,     !**/*.3gp, !**/*.asf, !**/*.asx,
                                                !**/*.avi,     !**/*.flv,       !**/*.mov,      !**/*.mp4,      !**/*.mpg,     !**/*.rm,  !**/*.swf, !**/*.vob,
                                                !**/*.wmv,     !**/*.bmp,       !**/*.gif,      !**/*.jpg,      !**/*.png,     !**/*.psd, !**/*.tif, !**/*.swf,
                                                !**/*.jar,     !**/*.zip,       !**/*.rar,      !**/*.exe,      !**/*.dll,     !**/*.pdb, !**/*.7z,  !**/*.gz,
                                                !**/*.tar.gz,  !**/*.tar,       !**/*.gz,       !**/*.ahtm,     !**/*.ahtml,   !**/*.fhtml, !**/*.hdm,
                                                !**/*.hdml,    !**/*.hsql,      !**/*.ht,       !**/*.hta,      !**/*.htc,     !**/*.htd, !**/*.war, !**/*.ear,
                                                !**/*.htmls,   !**/*.ihtml,     !**/*.mht,      !**/*.mhtm,     !**/*.mhtml,   !**/*.ssi, !**/*.stm,
                                                !**/*.stml,    !**/*.ttml,      !**/*.txn,      !**/*.xhtm,     !**/*.xhtml,   !**/*.class, !**/*.iml, !Checkmarx/Reports/*.*''', 
                                fullScanCycle: 10, 
                                groupId: 'b0b3593c-5df8-44ed-ad5a-e1defc48ab01', 
                                highThreshold: highThreshold, 
                                includeOpenSourceFolders: '', 
                                jobStatusOnError: 'FAILURE', 
                                lowThreshold: lowThreshold, 
                                mediumThreshold: mediumThreshold, 
                                osaArchiveIncludePatterns: '*.zip, *.war, *.ear, *.tgz', 
                                osaInstallBeforeScan: false, 
                                password: '{AQAAABAAAAAQm3frMXOlY5Grb0Rvx0UtVDZae4ZW1zQfU4mHpvuP9bQ=}', 
                                preset: '36', 
                                projectName: projectName, 
                                serverUrl: serverUri, 
                                sourceEncoding: '1', 
                                username: '', 
                                vulnerabilityThresholdEnabled: true, 
                                vulnerabilityThresholdResult: 'FAILURE', 
                                waitForResultsEnabled: true])
    }
    // Parse Checkmarx report to get vulnerabilities info and whether source code has passed scan:
    public static def ParseCheckmarxReport(pipeline_script, String reportPath, EntityCheckmarxVulnerabilityThreshold checkmarxVulnerabilityThreshold) {

    }
}