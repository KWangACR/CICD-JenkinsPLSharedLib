// SharedLibrary/src/common/RecordTestReport.groovy
package utils.common;

class RecordTestReport {
    public static def RecordJUnitTestReport(pipeline_script, String testResultFolderRelativePath) {
        pipeline_script.junit "${testResultFolderRelativePath}/*"
    }
    public static def RecordMSTestTestReport(pipeline_script, String testResultFolderRelativePath) {
        pipeline_script.step([$class: 'MSTestPublisher', 
                                testResultsFile:"${testResultFolderRelativePath}/MSTest_Report.xml", 
                                failOnError: true, 
                                keepLongStdio: true])
    }
    public static def RecordxUnitTestReport(pipeline_script, String testResultFolderRelativePath) {
        pipeline_script.step([$class: 'XUnitBuilder',
                                thresholds: [
                                    [$class: 'SkippedThreshold', 
                                        failureThreshold: '0'],
                                    [$class: 'FailedThreshold', 
                                        failureThreshold: '0']],
                                tools: [[$class: 'XUnitDotNetTestType', 
                                            pattern: "${testResultFolderRelativePath}/xUnit_Report.xml"]]])
    }
    public static def RecordNUnitTestReport(pipeline_script, String testResultFolderRelativePath) {
        pipeline_script.step([$class: 'NUnitPublisher', 
                                testResultsPattern: "${testResultFolderRelativePath}/NUnit_Report.xml", 
                                debug: false, 
                                keepJUnitReports: true, 
                                skipJUnitArchiver:false, 
                                failIfNoResults: true])
    }
    public static def RecordVSTestReport(pipeline_script, String testResultFolderRelativePath) {
        pipeline_script.step([$class: 'MSTestPublisher', 
                                testResultsFile:"${testResultFolderRelativePath}/*.xml", 
                                failOnError: true, 
                                keepLongStdio: true])
    }
}