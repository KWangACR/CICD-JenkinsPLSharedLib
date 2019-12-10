

////////////////////////////////////////////////////////////////////////////
////////////// Global Variables:
////////////////////////////////////////////////////////////////////////////
// Global variable: name of pipeline this script is dedicated for:
def TargetEnvironment = "${EnvironmentFolder}"
def TestEnvironment = "development"

// Global variables: target environment servers information:
def WebServer = "HDV-ACREDIT18"
def DBServer = "HDV-ACREDIT18DB"
def DBServer_Database = "ACRedit_Main_New"
def DBServer_Username = "acredit_dev_user"
def DBServer_Password = "acreditdevuser+1"

def WinRM_Username = "RESTONUAT\\SRVC_acreditci"
def WinRM_Password = "BVrZc408erOdY"

// Global variable: root directory of current job in Jenkins:
def RootDir_Jenkins = "C:\\Program Files (x86)\\Jenkins"
def RootDir_JenkinsWorkspace = "C:\\Program Files (x86)\\Jenkins\\workspace\\" + "${JOB_NAME}"

// Global variables: SVN Credential ID and SVN remote URLs:
def SVN_CredentialID_CI = "513c49a8-0270-4e66-894f-3363e1e425f1"
def SVN_CredentialID_SRC = "359cc4a1-2fee-40a4-90ec-7b2bd6d37687"
def SVN_URL_Request = "https://acrscm.svn.cloudforge.com/acreditci/trunk/Request/${EnvironmentFolder}"
def SVN_URL_Config = "https://acrscm.svn.cloudforge.com/acreditci/trunk/Configs/${EnvironmentFolder}"

// Global variables: root directory of filesystem on Integration Server:
def RootDir_CIAssets_Jenkins = "E:\\CI_ACRedit\\Assets\\Jenkins"
def RootDir_CIAssets_RequestManager = "E:\\CI_ACRedit\\Assets\\RequestManager"
def RootDir_CIScripts = "E:\\CI_ACRedit\\Scripts\\Pipeline"
def RootDir_CILocalRepo_Merge = "E:\\CI_ACRedit\\LocalRepo\\Merge"
def CurrentBuildFolder = "ACRedit__BN" + "${BUILD_ID}" + "__" + "${BUILD_TIMESTAMP}"
def TargetCIFolder = "C:\\CIPublish"


def SRC_Merge_RemoteURL = "https://acrscm.svn.cloudforge.com/acredit/trunk/ACRedit_CI/AutoMerge"
def SRC_Merge_LocalURL = "Merge"
def SRC_Merge_Script_LocalURL = "${RootDir_JenkinsWorkspace}" + "\\" + "${SRC_Merge_LocalURL}" + "\\src\\ACReditAutoMerger.ps1"
def SRC_E2ETest_RemoteURL = "https://acrscm.svn.cloudforge.com/acredit/trunk/ACRedit_CI/AutoTest"
def SRC_E2ETest_LocalURL = "E2ETest"
def SRC_Scan_RemoteURL = "https://acrscm.svn.cloudforge.com/acredit/trunk/ACRedit_CI/SecurityScan"
def SRC_Scan_LocalURL = "Scan"

// Global variables: test configuration:
def XunitRootDir = "${RootDir_JenkinsWorkspace}\\SRC\\WebProjMain"
def XunitReportsLocation = "${XunitRootDir}\\xunit_test_result\\"
def XunitTestRunnerPath = "C:\\XUnitTestRunner\\xunit.runner.console.2.3.1\\tools\\net452\\"
def KarmaTestJenkinsTemplateConfig = "C:\\Test_Runner_Scripts\\karma.jenkins.config.js"



// Global variables: shell scripts stored on Integration Server:
def Script_CreateFolderStructure = "${RootDir_CIScripts}" + "\\RequestParse\\CreateFolderStructure.ps1 -TargetEnvironment ${TargetEnvironment} -CurrentBuildFolder '${CurrentBuildFolder}' -RootDir_JenkinsWorkspace '${RootDir_JenkinsWorkspace}' -RootDir_CIAssets_Jenkins ${RootDir_CIAssets_Jenkins} -RootDir_CIAssets_RequestManager ${RootDir_CIAssets_RequestManager}"
//def Script_SaveConfigFiles = "${RootDir_CIScripts}" + "\\ConfigFetch\\SaveConfigFiles.ps1 -JenkinsWorkspace '${RootDir_JenkinsWorkspace}' -CIEnvironmentsConfigFolder '${RootDir_CIAssets_Jenkins}\\${TargetEnvironment}\\${CurrentBuildFolder}\\ConfigFiles'"
//def Script_SaveDBScripts = "${RootDir_CIScripts}" + "\\ComponentsSave\\SaveDBScripts.ps1 -JenkinsWorkspace '${RootDir_JenkinsWorkspace}' -CIEnvironmentsArtifactsFolder '${RootDir_CIAssets_Jenkins}\\${TargetEnvironment}\\${CurrentBuildFolder}\\ArtifactsToDeploy'"
def Script_SaveLibraries = "${RootDir_CIScripts}" + "\\ComponentsSave\\SaveLibraries.ps1 -JenkinsWorkspace '${RootDir_JenkinsWorkspace}' -CIEnvironmentsArtifactsFolder '${RootDir_CIAssets_Jenkins}\\${TargetEnvironment}\\${CurrentBuildFolder}\\ArtifactsToDeploy'"
def Script_SaveLibrariesCommon = "${RootDir_CIScripts}" + "\\ComponentsSave\\SaveLibraries_Common_Dev.ps1 -JenkinsWorkspace '${RootDir_JenkinsWorkspace}' -CIEnvironmentsArtifactsFolder '${RootDir_CIAssets_Jenkins}\\${TargetEnvironment}\\${CurrentBuildFolder}\\ArtifactsToDeploy'"
def Script_SaveLibrariesDataAccess = "${RootDir_CIScripts}" + "\\ComponentsSave\\SaveLibraries_DataAccess_Dev.ps1 -JenkinsWorkspace '${RootDir_JenkinsWorkspace}' -CIEnvironmentsArtifactsFolder '${RootDir_CIAssets_Jenkins}\\${TargetEnvironment}\\${CurrentBuildFolder}\\ArtifactsToDeploy'"
//def Script_SaveWindowServices = "${RootDir_CIScripts}" + "\\ComponentsSave\\SaveWindowServices.ps1 -JenkinsWorkspace '${RootDir_JenkinsWorkspace}' -CIEnvironmentsArtifactsFolder '${RootDir_CIAssets_Jenkins}\\${TargetEnvironment}\\${CurrentBuildFolder}\\ArtifactsToDeploy' -CIEnvironmentsConfigFolder '${RootDir_JenkinsWorkspace}\\Configs'"
def Script_SaveWebServicesAndProject = "${RootDir_CIScripts}" + "\\ComponentsSave\\SaveWebServicesAndProject_Dev.ps1 -CIEnvironmentsArtifactsFolder '${RootDir_CIAssets_Jenkins}\\${TargetEnvironment}\\${CurrentBuildFolder}\\ArtifactsToDeploy' -CIEnvironmentsConfigFolder '${RootDir_JenkinsWorkspace}\\Configs'"
def Script_UnitTestBackend = "C:\\Test_Runner_Scripts\\XUnit_Test_Runner.ps1 -rootDir '${XunitRootDir}' -defaultReportsLocation '${XunitReportsLocation}' -xunitTestRunnerPath '${XunitTestRunnerPath}'"
def Script_UnitTestFrontend = "C:\\Test_Runner_Scripts\\Karma_Test_Runner.ps1 -rootDir '${XunitRootDir}' -karmaTestJenkinsTemplateConfig '${KarmaTestJenkinsTemplateConfig}'"
def Script_DeployDBScripts = "${RootDir_CIScripts}" + "\\ComponentsDeploy\\DeployDBScripts.ps1 -DBServer '${DBServer}' -DBDatabase '${DBServer_Database}' -DBUsername '${DBServer_Username}' -DBPassword '${DBServer_Password}' -LocalCIEnvironmentsArtifactsFolder '${RootDir_CIAssets_Jenkins}\\${TargetEnvironment}\\${CurrentBuildFolder}\\ArtifactsToDeploy' -TargetCIFolder '${TargetCIFolder}'"
//def Script_DeployWindowServices = "${RootDir_CIScripts}" + "\\ComponentsDeploy\\DeployWindowsServices.ps1 -WebServer '${WebServer}' -LocalCIEnvironmentsArtifactsFolder '${RootDir_CIAssets_Jenkins}\\${TargetEnvironment}\\${CurrentBuildFolder}\\ArtifactsToDeploy' -TargetCIFolder '${TargetCIFolder}'"
def Script_DeployWebServices = "${RootDir_CIScripts}" + "\\ComponentsDeploy\\DeployWebServices_Dev.ps1 -WebServer '${WebServer}' -LocalCIEnvironmentsArtifactsFolder '${RootDir_CIAssets_Jenkins}\\${TargetEnvironment}\\${CurrentBuildFolder}\\ArtifactsToDeploy' -TargetCIFolder '${TargetCIFolder}'"
def Script_DeployWebProject = "${RootDir_CIScripts}" + "\\ComponentsDeploy\\DeployWebProject.ps1 -WebServer '${WebServer}' -LocalCIEnvironmentsArtifactsFolder '${RootDir_CIAssets_Jenkins}\\${TargetEnvironment}\\${CurrentBuildFolder}\\ArtifactsToDeploy' -TargetCIFolder '${TargetCIFolder}'"
def LogSavedPath = "${RootDir_CIAssets_Jenkins}\\${TargetEnvironment}\\${CurrentBuildFolder}\\BuildLogs"
def Script_CopyLogs = "${RootDir_CIScripts}" + "\\CopyLogs\\CopyLog.ps1 -JenkinsRoot '${RootDir_Jenkins}' -JobName '${JOB_NAME}' -JobNum '${BUILD_ID}' -TargetPath '${LogSavedPath}'"





// Global Map variables: arguments (configuration settings and stage result variables) sent to email template:
Map Email_Configuration = [:]
Email_Configuration.put("RecipientsList", "samatya@acr.org; knair@acr.org; aisaac@acr.org; iliu@acr.org; nvora@acr.org; wkevin@acr.org; msethi@acr.org; cwang@acr.org; nbandlamudi@acr.org; plingineni@acr.org; rkodimalla@acr.org")
Email_Configuration.put("EmailSubject", "Pipeline Notification (Development)")
Email_Configuration.put("EmailBodyFile", "CIEmailTemplate.html")
Map Email_Arguments = [:]
Email_Arguments.put("Email_BuildURL", "${BUILD_URL}")
Email_Arguments.put("Email_RequestedBy", "")
Email_Arguments.put("Email_TargetEnvironment", "Development")
Email_Arguments.put("Email_DateOfBuild", "${BUILD_TIMESTAMP}")
Email_Arguments.put("Email_RequestParseResult", "")
Email_Arguments.put("Email_ConfigFetchResult", "")
Email_Arguments.put("Email_SourceCodeCheckoutResult", "")
Email_Arguments.put("Email_MergeRequest", "")
Email_Arguments.put("Email_MergeResult", "")
Email_Arguments.put("Email_BuildStatus", "")
Email_Arguments.put("Email_BuildComponentStrings", "")
Email_Arguments.put("Email_FailedBuildJob", "")
Email_Arguments.put("Email_BackendTestResult", "")
Email_Arguments.put("Email_FrontendTestResult", "")
Email_Arguments.put("Email_DeployStatus", "")
Email_Arguments.put("Email_DeployComponentStrings", "")
Email_Arguments.put("Email_FailedDeployJob", "")
Email_Arguments.put("Email_E2ETestRequest", "")
Email_Arguments.put("Email_E2ETestModality", "")
Email_Arguments.put("Email_E2ETestCycle", "")
Email_Arguments.put("Email_E2ETestPoint", "")
Email_Arguments.put("Email_E2ETestResult", "")
Email_Arguments.put("Email_ScanRequest", "")
Email_Arguments.put("Email_ScanResult", "")



////////////////////////////////////////////////////////////////////////////
////////////// Global Functions:
////////////////////////////////////////////////////////////////////////////
// Global function: run powershell command/script:
def PowerShell(String psCmd) {
    bat "powershell.exe $psCmd"
}

// Global function: checkout source code from subversion repository:
def SVNCheckout(String credentialsId, String localPath, String remoteUrl) {
    checkout([$class: 'SubversionSCM', 
                  additionalCredentials: [], 
                  excludedCommitMessages: '', 
                  excludedRegions: '', 
                  excludedRevprop: '', 
                  excludedUsers: '', 
                  filterChangelog: false, 
                  ignoreDirPropChanges: false, 
                  includedRegions: '', 
                  locations: [[credentialsId: "${credentialsId}", 
                               depthOption: 'infinity', 
                               ignoreExternalsOption: true, 
                               local: "${localPath}", 
                               remote: "${remoteUrl}"]], 
                  workspaceUpdater: [$class: 'UpdateUpdater']])
}

// Global functions: publish test report (xunit and junit):
def GenerateTestReport(String ReportType, String ReportPattern) {
    if ("${ReportType}" == "xunit") {
        step([$class: 'XUnitBuilder',
                tools: [[$class: 'XUnitDotNetTestType', pattern: "${ReportPattern}", skipNoTestFiles: true, failIfNotNew: false, stopProcessingIfError: false]]])
    } else if ("${ReportType}" == "junit") {
        junit allowEmptyResults: true, testResults: "${ReportPattern}"
    }
}

// Global functions: generate email template and send notification email:
def GenerateEmailTemplate(String RootDir_CIScripts, String RootDir_JenkinsWorkspace, Map Email_Arguments) {
    def email_BuildURL = Email_Arguments.get("Email_BuildURL")
    def email_RequestedBy = Email_Arguments.get("Email_RequestedBy")
    def email_TargetEnvironment = Email_Arguments.get("Email_TargetEnvironment")
    def email_DateOfBuild = Email_Arguments.get("Email_DateOfBuild")
    def email_RequestParseResult = Email_Arguments.get("Email_RequestParseResult")
    def email_ConfigFetchResult = Email_Arguments.get("Email_ConfigFetchResult")
    def email_SourceCodeCheckoutResult = Email_Arguments.get("Email_SourceCodeCheckoutResult")
    def email_MergeRequest = Email_Arguments.get("Email_MergeRequest")
    def email_MergeResult = Email_Arguments.get("Email_MergeResult")
    def email_BuildStatus = Email_Arguments.get("Email_BuildStatus")
    def email_BuildComponentStrings = Email_Arguments.get("Email_BuildComponentStrings")
    def email_FailedBuildJob = Email_Arguments.get("Email_FailedBuildJob")
    def email_BackendTestResult = Email_Arguments.get("Email_BackendTestResult")
    def email_FrontendTestResult = Email_Arguments.get("Email_FrontendTestResult")
    def email_DeployStatus = Email_Arguments.get("Email_DeployStatus")
    def email_DeployComponentStrings = Email_Arguments.get("Email_DeployComponentStrings")
    def email_FailedDeployJob = Email_Arguments.get("Email_FailedDeployJob")
    def email_E2ETestRequest = Email_Arguments.get("Email_E2ETestRequest")
    def email_E2ETestModality = Email_Arguments.get("Email_E2ETestModality")
    def email_E2ETestCycle = Email_Arguments.get("Email_E2ETestCycle")
    def email_E2ETestPoint = Email_Arguments.get("Email_E2ETestPoint")
    def email_E2ETestResult = Email_Arguments.get("Email_E2ETestResult")
    def email_ScanRequest = Email_Arguments.get("Email_ScanRequest")
    def email_ScanResult = Email_Arguments.get("Email_ScanResult")
    
    def script_GenerateEmailTemplate = "${RootDir_CIScripts}" + "\\EmailTemplate\\EmailCustomTemplate.ps1"
    script_GenerateEmailTemplate += " -JenkinsWorkspace '${RootDir_JenkinsWorkspace}'"
    script_GenerateEmailTemplate += " -BuildURL '${email_BuildURL}'"
    script_GenerateEmailTemplate += " -RequestedBy '${email_RequestedBy}'"
    script_GenerateEmailTemplate += " -TargetEnvironment '${email_TargetEnvironment}'"
    script_GenerateEmailTemplate += " -DateOfBuild '${email_DateOfBuild}'"
    script_GenerateEmailTemplate += " -RequestParseResult '${email_RequestParseResult}'"
    script_GenerateEmailTemplate += " -ConfigFetchResult '${email_ConfigFetchResult}'"
    script_GenerateEmailTemplate += " -SourceCodeCheckoutResult '${email_SourceCodeCheckoutResult}'"
    script_GenerateEmailTemplate += " -MergeRequest '${email_MergeRequest}'"
    script_GenerateEmailTemplate += " -MergeResult '${email_MergeResult}'"
    script_GenerateEmailTemplate += " -BuildStatus '${email_BuildStatus}'"
    script_GenerateEmailTemplate += " -BuildComponentStrings '${email_BuildComponentStrings}'"
    script_GenerateEmailTemplate += " -FailedBuildJob '${email_FailedBuildJob}'"
    script_GenerateEmailTemplate += " -BackendTestResult '${email_BackendTestResult}'"
    script_GenerateEmailTemplate += " -FrontendTestResult '${email_FrontendTestResult}'"
    script_GenerateEmailTemplate += " -DeployStatus '${email_DeployStatus}'"
    script_GenerateEmailTemplate += " -DeployComponentStrings '${email_DeployComponentStrings}'"
    script_GenerateEmailTemplate += " -FailedDeployJob '${email_FailedDeployJob}'"
    script_GenerateEmailTemplate += " -E2ETestRequest '${email_E2ETestRequest}'"
    script_GenerateEmailTemplate += " -E2ETestModality '${email_E2ETestModality}'"
    script_GenerateEmailTemplate += " -E2ETestCycle '${email_E2ETestCycle}'"
    script_GenerateEmailTemplate += " -E2ETestPoint '${email_E2ETestPoint}'"
    script_GenerateEmailTemplate += " -E2ETestResult '${email_E2ETestResult}'"
    script_GenerateEmailTemplate += " -ScanRequest '${email_ScanRequest}'"
    script_GenerateEmailTemplate += " -ScanResult '${email_ScanResult}'"
    
    PowerShell("${script_GenerateEmailTemplate}")
}
def SendEmail(Map Email_Configuration) {
    def recipientsList = Email_Configuration.get("RecipientsList")
    def emailSubject = Email_Configuration.get("EmailSubject")
    def emailBodyFile = Email_Configuration.get("EmailBodyFile")
    emailext (
        to: "${recipientsList}",
        subject: "${emailSubject}",
        body: readFile("${emailBodyFile}")
    )
}
def EmailNotification(String RootDir_CIScripts, String RootDir_JenkinsWorkspace, Map Email_Arguments, Map Email_Configuration) {
    GenerateEmailTemplate(RootDir_CIScripts, RootDir_JenkinsWorkspace, Email_Arguments)
    SendEmail(Email_Configuration)
}




////////////////////////////////////////////////////////////////////////////
////////////// Pipeline Logic:
////////////////////////////////////////////////////////////////////////////
node {
    stage('01 -- Parse Request') {
        
        echo "---------------------------------------------Start parsing Deployment Request ..."
        
        
        try {
            // 01.1 -- Check out Deployment Request from SVN:
            
            // 01.2 -- Read properties from Deployment Request:
            def deploymentRequestFile = "${RootDir_CIAssets_RequestManager}\\${TargetEnvironment}\\DeploymentRequest.properties"
            def deploymentRequestProps = readProperties file: "${deploymentRequestFile}"
            env.DeploymentRequest_Requester = deploymentRequestProps["RequesterFirstname"] + " " + deploymentRequestProps["RequesterLastname"]
            env.DeploymentRequest_RequesterEmail = deploymentRequestProps["RequesterEmail"]
            env.DeploymentRequest_BuildVersion = deploymentRequestProps["BuildVersion"]
            
            env.DeploymentRequest_CodeSource_Original = deploymentRequestProps["CodeSource"]
            def revisionIndex = "${DeploymentRequest_CodeSource_Original}".indexOf('@')
            if ("${revisionIndex}" == "-1") {
                env.DeploymentRequest_CodeSource = "${DeploymentRequest_CodeSource_Original}"
                env.DeploymentRequest_CodeSource_Revision = ""
            } else {
                env.DeploymentRequest_CodeSource = "${DeploymentRequest_CodeSource_Original}".split('@')[0]
                env.DeploymentRequest_CodeSource_Revision = "@" + "${DeploymentRequest_CodeSource_Original}".split('@')[1]
            }
            if (("${DeploymentRequest_CodeSource}" == "https://acrscm.svn.cloudforge.com/acredit/trunk") || ("${DeploymentRequest_CodeSource}" == "https://acrscm.svn.cloudforge.com/acredit/trunk/")) {
                env.HasNewComponents = "true"
            } else {
                env.HasNewComponents = "true"
            }
            //env.DeploymentRequest_CodeSource = deploymentRequestProps["CodeSource"]
            
            env.DeploymentRequest_RequestMerge = deploymentRequestProps["MergeRequest"]
            env.DeploymentRequest_MergeSources = deploymentRequestProps["MergeSources"]
            env.DeploymentRequest_RequestTest = deploymentRequestProps["E2ETestRequest"]
            env.DeploymentRequest_TestModality = deploymentRequestProps["E2ETestModality"]
            env.DeploymentRequest_TestCycle = deploymentRequestProps["E2ETestCycle"]
            env.DeploymentRequest_TestPoint = deploymentRequestProps["E2ETestPoint"]
            env.DeploymentRequest_RequestScan = deploymentRequestProps["ScanRequest"]
            
            // 01.3 -- Create folder structure for current build on Integration Server:
            PowerShell("${Script_CreateFolderStructure}")
            
            // 01.4 -- Set email arguments "Email_RequestedBy" and "Email_RequestParseResult":
            Email_Arguments.put("Email_RequestedBy", "${DeploymentRequest_Requester}")
            Email_Arguments.put("Email_RequestParseResult", "pass")
            echo "---------------------------------------------Finish parsing Deployment Request."
        } catch(e) {
            // Set email argument "Email_RequestParseResult" to "fail":
            Email_Arguments.put("Email_RequestParseResult", "fail")
            
            // Send email notification to developers:
            EmailNotification("${RootDir_CIScripts}", "${RootDir_JenkinsWorkspace}", Email_Arguments, Email_Configuration)
            echo "---------------------------------------------Something went wrong while parsing Deployment Request."
            throw(e)
        }
    }
    stage('02 -- Fetch Configs') {
        echo "---------------------------------------------Start fetching Config Files ..."
        
        try {
            // 02.1 -- Check out Config files from SVN:
            SVNCheckout("${SVN_CredentialID_CI}", "Configs", "${SVN_URL_Config}")
            
            // 02.2 -- Save Config files to Integration Server:
            def Script_SaveConfigFiles = "${RootDir_CIScripts}" + "\\ConfigFetch\\SaveConfigFiles_EmailSys.ps1 -JenkinsWorkspace '${RootDir_JenkinsWorkspace}' -CIEnvironmentsConfigFolder '${RootDir_CIAssets_Jenkins}\\${TargetEnvironment}\\${CurrentBuildFolder}\\ConfigFiles' -HasNewComponents '${HasNewComponents}'"
            PowerShell("${Script_SaveConfigFiles}")
            
            // 02.3 -- Set email argument "Email_ConfigFetchResult":
            Email_Arguments.put("Email_ConfigFetchResult", "pass")
            echo "---------------------------------------------Finish fetching Config Files."
        } catch(e) {
            // Set email argument "Email_ConfigFetchResult" to "fail":
            Email_Arguments.put("Email_ConfigFetchResult", "fail")
            
            // Send email notification to developers:
            EmailNotification("${RootDir_CIScripts}", "${RootDir_JenkinsWorkspace}", Email_Arguments, Email_Configuration)
            echo "---------------------------------------------Something went wrong while fetching Config Files."
            throw(e)
        }
    }
    stage('03 -- Checkout Source Code') {
        echo "---------------------------------------------Start checking out project source code ..."
        
        try {
            // Global Array variables: source code location (remote SVN relative path & local Jenkins workspace relative path):
            if ("${HasNewComponents}" == "true") {
                def SRC_Location_Array = [[name: "Database Scripts", remoteURL: "/Application/Database", localURL: "SRC/DBScripts"],
                                          [name: "Library ACReditPlus Common", remoteURL: "/Application Components and Libraries/ACReditPlus Common", localURL: "SRC/LibCommon"],
                                          [name: "Library ACReditPlus DataAccess", remoteURL: "/Application Components and Libraries/ACReditPlus DataAccess", localURL: "SRC/LibDataAccess"],
                                          [name: "Library Barcode Generation", remoteURL: "/Application Components and Libraries/Barcode Generation", localURL: "SRC/LibBarcode"],
                                          [name: "Library Payment Integration", remoteURL: "/Application Components and Libraries/Payment Integration", localURL: "SRC/LibPayment"],
                                          [name: "Library Reviewer Selection", remoteURL: "/Application Components and Libraries/ReviewerSelection", localURL: "SRC/LibReviewer"],
                                          [name: "Library Wizard Engine", remoteURL: "/Application Components and Libraries/WizardEngine", localURL: "SRC/LibWizard"],
                                          [name: "Windows Service Email System", remoteURL: "/Application/ACReditPlus.EmailSystem", localURL: "SRC/WinSrvEmailSys"],
                                          [name: "Windows Service Email Backend", remoteURL: "/Application/ACReditPlus.BackendEmailClient", localURL: "SRC/WinSrvEmail"],
                                          [name: "Windows Services other", remoteURL: "/Application/ACReditServices", localURL: "SRC/WinSrv"],
                                          [name: "Web Service ATI", remoteURL: "/Application/ACR18WebApi", localURL: "SRC/WebSrvATI"],
                                          [name: "Web Project Main", remoteURL: "/Application/Source", localURL: "SRC/WebProjMain"]]
            
                // 03.1 -- Check out all source code specified in global array "SRC_Location_Array":
            
                for (src_location in SRC_Location_Array) {
                    echo "---------------------------------------------Start checking out " + src_location.name + " ..."
                    def src_remoteURL = "${DeploymentRequest_CodeSource}" + src_location.remoteURL + "${DeploymentRequest_CodeSource_Revision}"
                    SVNCheckout("${SVN_CredentialID_SRC}", src_location.localURL, src_remoteURL)
                }
                
                // 03.2 -- Save DB Scripts to Integration Server:
                // PowerShell("${Script_SaveDBScripts}")
                
                // 03.3 -- Set email argument "Email_SourceCodeCheckoutResult":
                Email_Arguments.put("Email_SourceCodeCheckoutResult", "pass")
                echo "---------------------------------------------Finish checking out Project Source Code."
            } else {
                def SRC_Location_Array = [[name: "Database Scripts", remoteURL: "/Application/Database", localURL: "SRC/DBScripts"],
                                          [name: "Library Barcode Generation", remoteURL: "/Application Components and Libraries/Barcode Generation", localURL: "SRC/LibBarcode"],
                                          [name: "Library Payment Integration", remoteURL: "/Application Components and Libraries/Payment Integration", localURL: "SRC/LibPayment"],
                                          [name: "Library Reviewer Selection", remoteURL: "/Application Components and Libraries/ReviewerSelection", localURL: "SRC/LibReviewer"],
                                          [name: "Library Wizard Engine", remoteURL: "/Application Components and Libraries/WizardEngine", localURL: "SRC/LibWizard"],
                                          [name: "Windows Service Email Backend", remoteURL: "/Application/AccrAppEmailHandler", localURL: "SRC/WinSrvEmail"],
                                          [name: "Windows Services other", remoteURL: "/Application/ACReditServices", localURL: "SRC/WinSrv"],
                                          [name: "Web Service ATI", remoteURL: "/Application/ACR18WebApi", localURL: "SRC/WebSrvATI"],
                                          [name: "Web Service Email", remoteURL: "/Application/AccrAppEmailHandler", localURL: "SRC/WebSrvEmail"],
                                          [name: "Web Project Main", remoteURL: "/Application/Source", localURL: "SRC/WebProjMain"]]
            
                // 03.1 -- Check out all source code specified in global array "SRC_Location_Array":
            
                for (src_location in SRC_Location_Array) {
                    echo "---------------------------------------------Start checking out " + src_location.name + " ..."
                    def src_remoteURL = "${DeploymentRequest_CodeSource}" + src_location.remoteURL + "${DeploymentRequest_CodeSource_Revision}"
                    SVNCheckout("${SVN_CredentialID_SRC}", src_location.localURL, src_remoteURL)
                }
                
                // 03.2 -- Save DB Scripts to Integration Server:
                // PowerShell("${Script_SaveDBScripts}")
                
                // 03.3 -- Set email argument "Email_SourceCodeCheckoutResult":
                Email_Arguments.put("Email_SourceCodeCheckoutResult", "pass")
                echo "---------------------------------------------Finish checking out Project Source Code."
            }
            
            
        } catch(e) {
            // Set email argument "Email_SourceCodeCheckoutResult" to fail:
            Email_Arguments.put("Email_SourceCodeCheckoutResult", "fail")
            
            // Send email notification to developers:
            EmailNotification("${RootDir_CIScripts}", "${RootDir_JenkinsWorkspace}", Email_Arguments, Email_Configuration)
            echo "---------------------------------------------Something went wrong while checking out Project Source Code."
            throw(e)
        }
    }
    stage('04 -- Merge Sources') {
        echo "---------------------------------------------Start Merging Sources ..."
        
        if ("${DeploymentRequest_RequestMerge}" == "no") {
            // If there is no merge request, set email argument "Email_MergeRequest" to "no", and skip the merging process:
            echo "---------------------------------------------No Merge Source is detected ..."
            Email_Arguments.put("Email_MergeRequest", "no")
        } else if ("${DeploymentRequest_RequestMerge}" == "yes") {
            // If there is a merge request, set email argument "Email_MergeRequest" to "yes", and start the merging process:
            echo "---------------------------------------------Merge Source is detected, now start merging ..."
            Email_Arguments.put("Email_MergeRequest", "yes")
            
            try {
                // Checkout Merge Script:
                SVNCheckout("${SVN_CredentialID_SRC}", "${SRC_Merge_LocalURL}", "${SRC_Merge_RemoteURL}")
                
                // Run auto merge script to merge specified sources:
                def script_MergeSources = "${RootDir_CIScripts}" + "\\SourcesMerge\\MergeSources.ps1 -TargetEnvironment ${TargetEnvironment} -RootDir_CILocalRepo_Merge '${RootDir_CILocalRepo_Merge}' -SRC_Merge_Script_LocalURL '${SRC_Merge_Script_LocalURL}' -MergeSources '${DeploymentRequest_MergeSources}' -CodeSource '${DeploymentRequest_CodeSource}' -SVN_CredentialID '${SVN_CredentialID_SRC}'"
                PowerShell("${script_MergeSources}")
                
                // Set email arguments "Email_MergeResult":
                Email_Arguments.put("Email_MergeResult", "pass")
                echo "---------------------------------------------Finish merging sources."
            } catch(e) {
                // Set email argument "Email_MergeResult" to "fail":
                Email_Arguments.put("Email_MergeResult", "fail")
                
                // Send email notification to developers:
                EmailNotification("${RootDir_CIScripts}", "${RootDir_JenkinsWorkspace}", Email_Arguments, Email_Configuration)
                echo "---------------------------------------------Something went wrong while merging sources."
                throw(e)
            }
            
        }
    }
    stage('05 -- Compile Components') {
        echo "---------------------------------------------Start compiling software components ..."
        
        // echo "---------------------------------------------Restore packages from both public feed and local feed ..."
        // bat "nuget restore SRC/WinSrv/ACReditServices.sln -source 'https://www.nuget.org/api/v3;https://devops-dev.acr.org/CICDPackageRepo/nuget'"
        // bat "nuget restore SRC/WinSrvEmailSys/ACReditPlus.EmailSystem.sln -source 'https://www.nuget.org/api/v3;https://devops-dev.acr.org/CICDPackageRepo/nuget'"
        // bat "nuget restore SRC/WinSrvEmail/ACReditPlus.BackendEmailClient.sln -source 'https://www.nuget.org/api/v3;https://devops-dev.acr.org/CICDPackageRepo/nuget'"
        // bat "nuget restore SRC/WebSrvATI/ACR18WebApi.sln -source 'https://www.nuget.org/api/v3;https://devops-dev.acr.org/CICDPackageRepo/nuget'"
        // bat "nuget restore SRC/WebProjMain/ACReditApp.sln -source 'https://www.nuget.org/api/v3;https://devops-dev.acr.org/CICDPackageRepo/nuget'"
        // bat "nuget restore SRC/LibCommon/ACReditPlus.Common.sln -source 'https://www.nuget.org/api/v3;https://devops-dev.acr.org/CICDPackageRepo/nuget'"
        // bat "nuget restore SRC/LibDataAccess/ACReditPlus.DataAccess.sln -source 'https://www.nuget.org/api/v3;https://devops-dev.acr.org/CICDPackageRepo/nuget'"
        // bat "nuget restore SRC/LibBarcode/Source/ACReditApp.Business.BarcodePDFGenerationLibrary.sln -source 'https://www.nuget.org/api/v3;https://devops-dev.acr.org/CICDPackageRepo/nuget'"
        // bat "nuget restore SRC/LibPayment/Source/ACReditApp.PaymentManagement.sln -source 'https://www.nuget.org/api/v3;https://devops-dev.acr.org/CICDPackageRepo/nuget'"
        // bat "nuget restore SRC/LibReviewer/Source/ReviewerSelection.sln -source 'https://www.nuget.org/api/v3;https://devops-dev.acr.org/CICDPackageRepo/nuget'"
        // bat "nuget restore SRC/LibWizard/ACReditApp.Wizard.sln -source 'https://www.nuget.org/api/v3;https://devops-dev.acr.org/CICDPackageRepo/nuget'"
        // echo "---------------------------------------------Finish restoring packages from local feed."

        def nugetCliPath = "C:/Tools/nuget.exe"
        def nugetPackageRepoSources = "https://api.nuget.org/v3/index.json;https://devops-repo.acr.org:8443/repository/CICDPackageRepo"
                            
        if ("${HasNewComponents}" == "true") {
            // Scope variables: Software Components to be compiled:
            def Compile_Components_Others_Array = [[name: "Windows Service ACRedit Backend",
                                                    restorePackageCmd: "\"${nugetCliPath}\" restore \"SRC/WinSrv/ACReditServices.sln\" -source \"${nugetPackageRepoSources}\"",
                                                    slnCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/WinSrv/ACReditServices.sln /t:build /p:PlatformTarget=x86 /p:Configuration=Release /maxcpucount",
                                                    projCompileCmd: ""],
                                                   [name: "Windows Service Box Close",
                                                    restorePackageCmd: "",
                                                    slnCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/WinSrv/ACReditServices.sln /t:build /p:PlatformTarget=x86 /p:Configuration=Release /maxcpucount",
                                                    projCompileCmd: ""],
                                                   [name: "Windows Service Final Report",
                                                    restorePackageCmd: "",
                                                    slnCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/WinSrv/ACReditServices.sln /t:build /p:PlatformTarget=x86 /p:Configuration=Release /maxcpucount",
                                                    projCompileCmd: ""],
                                                   [name: "Windows Service Email System",
                                                    restorePackageCmd: "\"${nugetCliPath}\" restore \"SRC/WinSrvEmailSys/ACReditPlus.EmailSystem.sln\" -source \"${nugetPackageRepoSources}\"",
                                                    slnCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/WinSrvEmailSys/ACReditPlus.EmailSystem.sln /t:build /p:PlatformTarget=x86 /p:Configuration=Release /maxcpucount",
                                                    projCompileCmd: ""],
                                                   [name: "Windows Service Email Backend",
                                                    restorePackageCmd: "\"${nugetCliPath}\" restore \"SRC/WinSrvEmail/ACReditPlus.BackendEmailClient.sln\" -source \"${nugetPackageRepoSources}\"",
                                                    slnCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/WinSrvEmail/ACReditPlus.BackendEmailClient.sln /t:build /p:PlatformTarget=x86 /p:Configuration=Release /maxcpucount",
                                                    projCompileCmd: ""],
                                                   [name: "Web Service ATI",
                                                    restorePackageCmd: "\"${nugetCliPath}\" restore \"SRC/WebSrvATI/ACR18WebApi.sln\" -source \"${nugetPackageRepoSources}\"",
                                                    slnCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/WebSrvATI/ACR18WebApi.sln /t:build /p:PlatformTarget=x86 /p:Configuration=Release /maxcpucount",
                                                    projCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/WebSrvATI/ACR18WebApi.csproj /p:Configuration=Release /p:Platform=AnyCPU /t:WebPublish /p:WebPublishMethod=FileSystem /p:DeleteExistingFiles=True /p:publishUrl=\"${RootDir_CIAssets_Jenkins}\\${TargetEnvironment}\\${CurrentBuildFolder}\\ArtifactsToDeploy\\WebServiceATI\""],
                                                   [name: "Web Project Main",
                                                    restorePackageCmd: "\"${nugetCliPath}\" restore \"SRC/WebProjMain/ACReditApp.sln\" -source \"${nugetPackageRepoSources}\"",
                                                    slnCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/WebProjMain/ACReditApp.sln /t:build /p:PlatformTarget=anycpu /p:Configuration=Release /maxcpucount",
                                                    projCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/WebProjMain/ACReditApp.Web/ACReditApp.Web.csproj /p:Configuration=Release /p:Platform=AnyCPU /t:WebPublish /p:WebPublishMethod=FileSystem /p:DeleteExistingFiles=True /p:publishUrl=\"${RootDir_CIAssets_Jenkins}\\${TargetEnvironment}\\${CurrentBuildFolder}\\ArtifactsToDeploy\\WebApplicationACReditMain\""]]
            def Compile_Components_Libraries_Common = [[name: "Library ACReditPlus Common",
                                                      restorePackageCmd: "\"${nugetCliPath}\" restore \"SRC/LibCommon/ACReditPlus.Common.sln\" -source \"${nugetPackageRepoSources}\"",
                                                      slnCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/LibCommon/ACReditPlus.Common.sln /t:build /p:PlatformTarget=x86 /p:Configuration=Release /maxcpucount",
                                                      projCompileCmd: ""]]
            def Compile_Components_Libraries_Array = [[name: "Library ACReditPlus DataAccess",
                                                      restorePackageCmd: "\"${nugetCliPath}\" restore \"SRC/LibDataAccess/ACReditPlus.DataAccess.sln\" -source \"${nugetPackageRepoSources}\"",
                                                      slnCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/LibDataAccess/ACReditPlus.DataAccess.sln /t:build /p:PlatformTarget=x86 /p:Configuration=Release /maxcpucount",
                                                      projCompileCmd: ""], 
                                                      [name: "Library Barcode Generation",
                                                      restorePackageCmd: "\"${nugetCliPath}\" restore \"SRC/LibBarcode/Source/ACReditApp.Business.BarcodePDFGenerationLibrary.sln\" -source \"${nugetPackageRepoSources}\"",
                                                      slnCompileCmd: "",
                                                      projCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/LibBarcode/Source/ACReditApp.Business.BarcodePDFGenerationLibrary.sln /t:build /p:PlatformTarget=x86 /p:Configuration=Release /maxcpucount"], 
                                                     [name: "Library Payment Integration",
                                                      restorePackageCmd: "\"${nugetCliPath}\" restore \"SRC/LibPayment/Source/ACReditApp.PaymentManagement.sln\" -source \"${nugetPackageRepoSources}\"",
                                                      slnCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/LibPayment/Source/ACReditApp.PaymentManagement.sln /t:build /p:PlatformTarget=x86 /p:Configuration=Release /maxcpucount",
                                                      projCompileCmd: ""], 
                                                     [name: "Library Reviewer Selection",
                                                      restorePackageCmd: "\"${nugetCliPath}\" restore \"SRC/LibReviewer/Source/ReviewerSelection.sln\" -source \"${nugetPackageRepoSources}\"",
                                                      slnCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/LibReviewer/Source/ReviewerSelection.sln /t:build /p:PlatformTarget=x86 /p:Configuration=Release /maxcpucount",
                                                      projCompileCmd: ""],
                                                     [name: "Library Wizard Engine",
                                                      restorePackageCmd: "\"${nugetCliPath}\" restore \"SRC/LibWizard/ACReditApp.Wizard.sln\" -source \"${nugetPackageRepoSources}\"",
                                                      slnCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/LibWizard/ACReditApp.Wizard.sln /t:build /p:PlatformTarget=x86 /p:Configuration=Release /maxcpucount",
                                                      projCompileCmd: ""]]
            
            def Compile_Components_Array = Compile_Components_Libraries_Common + Compile_Components_Libraries_Array + Compile_Components_Others_Array
            def Compile_Components_Names_Array = Compile_Components_Array.collect { it.name }
            def Compile_Components_Names_String = Compile_Components_Names_Array.join(",")
            
            Email_Arguments.put("Email_BuildComponentStrings", "${Compile_Components_Names_String}")
            
            // First, compile libraries:
            for (compile_Component_Library in Compile_Components_Libraries_Common) {
                try {
                    // Compile solution and project:
                    echo "---------------------------------------------Start Compiling ${compile_Component_Library.name} ..."
                    bat "${compile_Component_Library.restorePackageCmd}"
                    bat "${compile_Component_Library.slnCompileCmd}"
                    bat "${compile_Component_Library.projCompileCmd}"
                    
                    // Set email argument "Email_BuildStatus":
                    Email_Arguments.put("Email_BuildStatus", "pass")
                    echo "---------------------------------------------Finish Compiling ${compile_Component_Library.name}."
                } catch(e) {
                    // Set email argument "Email_BuildStatus" to fail, and set "Email_FailedBuildJob" to the failed component:
                    Email_Arguments.put("Email_BuildStatus", "fail")
                    Email_Arguments.put("Email_FailedBuildJob", "${compile_Component_Library.name}")
                    
                    // Send email notification to developers:
                    EmailNotification("${RootDir_CIScripts}", "${RootDir_JenkinsWorkspace}", Email_Arguments, Email_Configuration)
                    echo "---------------------------------------------Something went wrong while compiling Software Components."
                    throw(e)
                }
            }
            // After first category libraries are compiled, save libraries:
            PowerShell("${Script_SaveLibrariesCommon}")
            for (compile_Component_Library in Compile_Components_Libraries_Array) {
                try {
                    // Compile solution and project:
                    echo "---------------------------------------------Start Compiling ${compile_Component_Library.name} ..."
                    bat "${compile_Component_Library.restorePackageCmd}"
                    bat "${compile_Component_Library.slnCompileCmd}"
                    bat "${compile_Component_Library.projCompileCmd}"
                    
                    // Set email argument "Email_BuildStatus":
                    Email_Arguments.put("Email_BuildStatus", "pass")
                    echo "---------------------------------------------Finish Compiling ${compile_Component_Library.name}."
                } catch(e) {
                    // Set email argument "Email_BuildStatus" to fail, and set "Email_FailedBuildJob" to the failed component:
                    Email_Arguments.put("Email_BuildStatus", "fail")
                    Email_Arguments.put("Email_FailedBuildJob", "${compile_Component_Library.name}")
                    
                    // Send email notification to developers:
                    EmailNotification("${RootDir_CIScripts}", "${RootDir_JenkinsWorkspace}", Email_Arguments, Email_Configuration)
                    echo "---------------------------------------------Something went wrong while compiling Software Components."
                    throw(e)
                }
            }
            // After all libraries are compiled, save the libraries .dll files:
            
            PowerShell("${Script_SaveLibrariesDataAccess}")
            PowerShell("${Script_SaveLibraries}")
            
            // Second, compile other components:
            for (compile_Component_Other in Compile_Components_Others_Array) {
                try {
                    // Compile solution and project:
                    echo "---------------------------------------------Start Compiling ${compile_Component_Other.name} ..."
                    bat "${compile_Component_Other.restorePackageCmd}"
                    bat "${compile_Component_Other.slnCompileCmd}"
                    bat "${compile_Component_Other.projCompileCmd}"
                    
                    // Set email argument "Email_BuildStatus":
                    Email_Arguments.put("Email_BuildStatus", "pass")
                    echo "---------------------------------------------Finish Compiling ${compile_Component_Other.name}."
                } catch(e) {
                    // Set email argument "Email_BuildStatus" to fail, and set "Email_FailedBuildJob" to the failed component:
                    Email_Arguments.put("Email_BuildStatus", "fail")
                    Email_Arguments.put("Email_FailedBuildJob", "${compile_Component_Other.name}")
                    
                    // Send email notification to developers:
                    EmailNotification("${RootDir_CIScripts}", "${RootDir_JenkinsWorkspace}", Email_Arguments, Email_Configuration)
                    echo "---------------------------------------------Something went wrong while compiling Software Components."
                    throw(e)
                }
            }
            // After all components are compiled, save these components:
            def Script_SaveWindowServices = "${RootDir_CIScripts}" + "\\ComponentsSave\\SaveWindowServices_EmailSys_Dev.ps1 -JenkinsWorkspace '${RootDir_JenkinsWorkspace}' -CIEnvironmentsArtifactsFolder '${RootDir_CIAssets_Jenkins}\\${TargetEnvironment}\\${CurrentBuildFolder}\\ArtifactsToDeploy' -CIEnvironmentsConfigFolder '${RootDir_JenkinsWorkspace}\\Configs' -HasNewComponents '${HasNewComponents}'"
            PowerShell("${Script_SaveWindowServices}")
            PowerShell("${Script_SaveWebServicesAndProject}")
            
        } else {
            def Compile_Components_Others_Array = [[name: "Windows Service ACRedit Backend",
                                                    slnCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/WinSrv/ACReditServices.sln /t:build /p:PlatformTarget=x86 /p:Configuration=Release /maxcpucount",
                                                    projCompileCmd: ""],
                                                   [name: "Windows Service Box Close",
                                                    slnCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/WinSrv/ACReditServices.sln /t:build /p:PlatformTarget=x86 /p:Configuration=Release /maxcpucount",
                                                    projCompileCmd: ""],
                                                   [name: "Windows Service Final Report",
                                                    slnCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/WinSrv/ACReditServices.sln /t:build /p:PlatformTarget=x86 /p:Configuration=Release /maxcpucount",
                                                    projCompileCmd: ""],
                                                   [name: "Windows Service Email Backend",
                                                    slnCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/WinSrvEmail/EmailBackgroundService/EmailBackgroundService.sln /t:build /p:PlatformTarget=x86 /p:Configuration=Release /maxcpucount",
                                                    projCompileCmd: ""],
                                                   [name: "Web Service ATI",
                                                    slnCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/WebSrvATI/ACR18WebApi.sln /t:build /p:PlatformTarget=x86 /p:Configuration=Release /maxcpucount",
                                                    projCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/WebSrvATI/ACR18WebApi.csproj /p:Configuration=Release /p:Platform=AnyCPU /t:WebPublish /p:WebPublishMethod=FileSystem /p:DeleteExistingFiles=True /p:publishUrl=\"${RootDir_CIAssets_Jenkins}\\${TargetEnvironment}\\${CurrentBuildFolder}\\ArtifactsToDeploy\\WebServiceATI\""],
                                                   [name: "Web Service Email",
                                                    slnCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/WebSrvEmail/AccrAppEmailHandler.sln /t:build /p:PlatformTarget=x86 /p:Configuration=Release /maxcpucount",
                                                    projCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/WebSrvEmail/EmailBackendWebAPI/EmailBackendWebAPI.csproj /p:Configuration=Release /p:Platform=AnyCPU /t:WebPublish /p:WebPublishMethod=FileSystem /p:DeleteExistingFiles=True /p:publishUrl=\"${RootDir_CIAssets_Jenkins}\\${TargetEnvironment}\\${CurrentBuildFolder}\\ArtifactsToDeploy\\WebServiceEmailBackend\""],
                                                   [name: "Web Project Main",
                                                    slnCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/WebProjMain/ACReditApp.sln /t:build /p:PlatformTarget=anycpu /p:Configuration=Release /maxcpucount",
                                                    projCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/WebProjMain/ACReditApp.Web/ACReditApp.Web.csproj /p:Configuration=Release /p:Platform=AnyCPU /t:WebPublish /p:WebPublishMethod=FileSystem /p:DeleteExistingFiles=True /p:publishUrl=\"${RootDir_CIAssets_Jenkins}\\${TargetEnvironment}\\${CurrentBuildFolder}\\ArtifactsToDeploy\\WebApplicationACReditMain\""]]
            def Compile_Components_Libraries_Array = [[name: "Library Barcode Generation",
                                                      slnCompileCmd: "",
                                                      projCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/LibBarcode/Source/ACReditApp.Business.BarcodePDFGenerationLibrary.sln /t:build /p:PlatformTarget=x86 /p:Configuration=Release /maxcpucount"], 
                                                     [name: "Library Payment Integration",
                                                      slnCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/LibPayment/Source/ACReditApp.PaymentManagement.sln /t:build /p:PlatformTarget=x86 /p:Configuration=Release /maxcpucount",
                                                      projCompileCmd: ""], 
                                                     [name: "Library Reviewer Selection",
                                                      slnCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/LibReviewer/Source/ReviewerSelection.sln /t:build /p:PlatformTarget=x86 /p:Configuration=Release /maxcpucount",
                                                      projCompileCmd: ""],
                                                     [name: "Library Wizard Engine",
                                                      slnCompileCmd: "\"${tool 'MSBuild-14.0'}\" SRC/LibWizard/ACReditApp.Wizard.sln /t:build /p:PlatformTarget=x86 /p:Configuration=Release /maxcpucount",
                                                      projCompileCmd: ""]]
            
            def Compile_Components_Array = Compile_Components_Libraries_Array + Compile_Components_Others_Array
            def Compile_Components_Names_Array = Compile_Components_Array.collect { it.name }
            def Compile_Components_Names_String = Compile_Components_Names_Array.join(",")
            
            Email_Arguments.put("Email_BuildComponentStrings", "${Compile_Components_Names_String}")
            
            for (compile_Component_Library in Compile_Components_Libraries_Array) {
                try {
                    // Compile solution and project:
                    echo "---------------------------------------------Start Compiling ${compile_Component_Library.name} ..."
                    bat "${compile_Component_Library.slnCompileCmd}"
                    bat "${compile_Component_Library.projCompileCmd}"
                    
                    // Set email argument "Email_BuildStatus":
                    Email_Arguments.put("Email_BuildStatus", "pass")
                    echo "---------------------------------------------Finish Compiling ${compile_Component_Library.name}."
                } catch(e) {
                    // Set email argument "Email_BuildStatus" to fail, and set "Email_FailedBuildJob" to the failed component:
                    Email_Arguments.put("Email_BuildStatus", "fail")
                    Email_Arguments.put("Email_FailedBuildJob", "${compile_Component_Library.name}")
                    
                    // Send email notification to developers:
                    EmailNotification("${RootDir_CIScripts}", "${RootDir_JenkinsWorkspace}", Email_Arguments, Email_Configuration)
                    echo "---------------------------------------------Something went wrong while compiling Software Components."
                    throw(e)
                }
            }
            // After all libraries are compiled, save the libraries .dll files:
            PowerShell("${Script_SaveLibraries}")
            
            // Second, compile other components:
            for (compile_Component_Other in Compile_Components_Others_Array) {
                try {
                    // Compile solution and project:
                    echo "---------------------------------------------Start Compiling ${compile_Component_Other.name} ..."
                    bat "${compile_Component_Other.slnCompileCmd}"
                    bat "${compile_Component_Other.projCompileCmd}"
                    
                    // Set email argument "Email_BuildStatus":
                    Email_Arguments.put("Email_BuildStatus", "pass")
                    echo "---------------------------------------------Finish Compiling ${compile_Component_Other.name}."
                } catch(e) {
                    // Set email argument "Email_BuildStatus" to fail, and set "Email_FailedBuildJob" to the failed component:
                    Email_Arguments.put("Email_BuildStatus", "fail")
                    Email_Arguments.put("Email_FailedBuildJob", "${compile_Component_Other.name}")
                    
                    // Send email notification to developers:
                    EmailNotification("${RootDir_CIScripts}", "${RootDir_JenkinsWorkspace}", Email_Arguments, Email_Configuration)
                    echo "---------------------------------------------Something went wrong while compiling Software Components."
                    throw(e)
                }
            }
            // After all components are compiled, save these components:
            def Script_SaveWindowServices = "${RootDir_CIScripts}" + "\\ComponentsSave\\SaveWindowServices_EmailSys.ps1 -JenkinsWorkspace '${RootDir_JenkinsWorkspace}' -CIEnvironmentsArtifactsFolder '${RootDir_CIAssets_Jenkins}\\${TargetEnvironment}\\${CurrentBuildFolder}\\ArtifactsToDeploy' -CIEnvironmentsConfigFolder '${RootDir_JenkinsWorkspace}\\Configs' -HasNewComponents '${HasNewComponents}'"
            PowerShell("${Script_SaveWindowServices}")
            PowerShell("${Script_SaveWebServicesAndProject}")
        }
        
        
        
    }
    stage('06 -- Backend Unit Test') {
        echo "---------------------------------------------Pass this stage for now ..."
    }
    stage('07 -- Frontend Unit Test') {
        echo "---------------------------------------------Pass this stage for now ..."
    }
    stage('08 -- Deploy Components') {
        echo "---------------------------------------------Start Deploying software components:"
        
        def Script_DeployWindowServices = "${RootDir_CIScripts}" + "\\ComponentsDeploy\\DeployWindowsServices_EmailSys_Dev.ps1 -WebServer '${WebServer}' -LocalCIEnvironmentsArtifactsFolder '${RootDir_CIAssets_Jenkins}\\${TargetEnvironment}\\${CurrentBuildFolder}\\ArtifactsToDeploy' -TargetCIFolder '${TargetCIFolder}' -HasNewComponents '${HasNewComponents}'"
        
        def Deploy_Components_Array = [[name: "Windows Services", deployScript: "${Script_DeployWindowServices}"], 
                                       [name: "Web Services", deployScript: "${Script_DeployWebServices}"],
                                       [name: "Web Project", deployScript: "${Script_DeployWebProject}"]]
        def Deploy_Components_Names_Array = Deploy_Components_Array.collect { it.name }
        def Deploy_Components_Names_String = "Database Project," + Deploy_Components_Names_Array.join(",")
        
        Email_Arguments.put("Email_DeployComponentStrings", "${Deploy_Components_Names_String}")
        
        // Deploy Database Project:
        try {
            echo "---------------------------------------------Start deploying database project:"
            bat "\"${tool 'MSBuild-Default'}\" SRC/DBScripts/ACReditData/ACReditData.sln /t:build /p:PlatformTarget=x86 /maxcpucount"
            bat "\"${tool 'MSBuild-Default'}\" SRC/DBScripts/ACReditData/ACReditData.Main/ACReditData.Main.sqlproj /target:Deploy /p:UseSandboxSettings=false /p:BlockOnPossibleDataLoss=false /p:TargetDatabase=${DBServer_Database};TargetConnectionString=\"Data Source=${DBServer};Initial Catalog= ${DBServer_Database};Persist Security Info=True;User ID= ${DBServer_Username};Password=${DBServer_Password};Timeout=0;\""
            
            // Set email argument "Email_DeployStatus":
            Email_Arguments.put("Email_DeployStatus", "pass")
            echo "---------------------------------------------Finish deploying Database Project."
        } catch(e) {
            // Set email argument "Email_DeployStatus" to fail, and set "Email_FailedDeployJob" to the failed component:
            Email_Arguments.put("Email_DeployStatus", "fail")
            Email_Arguments.put("Email_FailedDeployJob", "Database Project")
            
            // Send email notification to developers:
            EmailNotification("${RootDir_CIScripts}", "${RootDir_JenkinsWorkspace}", Email_Arguments, Email_Configuration)
            echo "---------------------------------------------Something went wrong while deploying Software Components."
            throw(e)
        }
        
        // Deploy other software components:
        for (Deploy_Component_Item in Deploy_Components_Array) {
            try {
                // Deploy software component:
                echo "---------------------------------------------Start Deploying ${Deploy_Component_Item.name} ..."
                PowerShell("${Deploy_Component_Item.deployScript}")
                
                // Set email argument "Email_DeployStatus":
                Email_Arguments.put("Email_DeployStatus", "pass")
                echo "---------------------------------------------Finish Deploying ${Deploy_Component_Item.name}."
            } catch(e) {
                // Set email argument "Email_DeployStatus" to fail, and set "Email_FailedDeployJob" to the failed component:
                Email_Arguments.put("Email_DeployStatus", "fail")
                Email_Arguments.put("Email_FailedDeployJob", "${Deploy_Component_Item.name}")
                
                // Send email notification to developers:
                EmailNotification("${RootDir_CIScripts}", "${RootDir_JenkinsWorkspace}", Email_Arguments, Email_Configuration)
                echo "---------------------------------------------Something went wrong while deploying Software Components."
                throw(e)
            }
        }
        
    }
    stage('09 --  E2E Test') {
        echo "---------------------------------------------End To End Testing ..."
       
            
        if("${DeploymentRequest_RequestTest}" == "no") {
            echo "No E2E Test Request is detected ..."
            Email_Arguments.put("Email_E2ETestRequest", "no")
        } else if ("${DeploymentRequest_RequestTest}" == "yes") {
            echo "E2E Test Request is detected, now start End-To-End Testing ..."
            Email_Arguments.put("Email_E2ETestRequest", "yes")
            
            try {
                // Chcekout E2E Test Source Code:
                SVNCheckout("${SVN_CredentialID_SRC}", "${SRC_E2ETest_LocalURL}", "${SRC_E2ETest_RemoteURL}")
                // Run E2E test script:
                def testInput = "${DeploymentRequest_TestModality}" + "-" + "${DeploymentRequest_TestCycle}" + "/" + "${DeploymentRequest_TestPoint}"
                def script_E2ETest = "${RootDir_CIScripts}" + "\\Test\\E2ETest\\E2E_Test_Runner.ps1 -JenkinsWorkspace '${RootDir_JenkinsWorkspace}' -TestEnvironment '${TestEnvironment}' -TestInput '${testInput}'"
                PowerShell("${script_E2ETest}")
                
                // Set email arguments of E2E Test:
                Email_Arguments.put("Email_E2ETestModality", "${DeploymentRequest_TestModality}")
                Email_Arguments.put("Email_E2ETestCycle", "${DeploymentRequest_TestCycle}")
                Email_Arguments.put("Email_E2ETestPoint", "${DeploymentRequest_TestPoint}")
                Email_Arguments.put("Email_E2ETestResult", "pass")
                echo "---------------------------------------------Finish E2E Test."
            } catch(e) {
                // Set email argument of E2E Test:
                Email_Arguments.put("Email_E2ETestModality", "${DeploymentRequest_TestModality}")
                Email_Arguments.put("Email_E2ETestCycle", "${DeploymentRequest_TestCycle}")
                Email_Arguments.put("Email_E2ETestPoint", "${DeploymentRequest_TestPoint}")
                Email_Arguments.put("Email_E2ETestResult", "fail")
                
                // Send email notification to developers:
                EmailNotification("${RootDir_CIScripts}", "${RootDir_JenkinsWorkspace}", Email_Arguments, Email_Configuration)
                echo "---------------------------------------------Something went wrong while E2E Testing."
                throw e
            } finally {
                GenerateTestReport("junit", "E2ETest/e2e/src/test_result/test-results.xml")
                //junit "E2ETest/e2e/src/test_result/test-results.xml"
            }
        }
        
    }
    stage('10 -- Security Scan') {
        echo "---------------------------------------------Security Scanning ..."
        
        if ("${DeploymentRequest_RequestScan}" == "no") {
            // If there is no scan request, set email arguments:
            echo "---------------------------------------------No Security Scan Request is detected ..."
            Email_Arguments.put("Email_ScanRequest", "no")
        } else if ("${DeploymentRequest_RequestScan}" == "yes") {
            // If there is a merge request, set email argument "Email_MergeRequest" to "yes", and start the merging process:
            echo "---------------------------------------------Security Scan Request is detected, now start scanning ..."
            Email_Arguments.put("Email_ScanRequest", "yes")
            
            try {
                // Checkout Scan Script:
                SVNCheckout("${SVN_CredentialID_SRC}", "${SRC_Scan_LocalURL}", "${SRC_Scan_RemoteURL}")
                
                // Run security scan script to scan deployed code:
                def script_SecurityScan = "${RootDir_CIScripts}" + "\\SecurityScan\\SecurityScan.ps1 -JenkinsWorkspace ${RootDir_JenkinsWorkspace} -ScanEnvironment '${TestEnvironment}'"
                PowerShell("${script_SecurityScan}")
                
                // Set email arguments:
                Email_Arguments.put("Email_ScanResult", "pass")
                echo "---------------------------------------------Finish security scanning."
            } catch(e) {
                // Set email argument:
                Email_Arguments.put("Email_ScanResult", "fail")
                
                // Send email notification to developers:
                EmailNotification("${RootDir_CIScripts}", "${RootDir_JenkinsWorkspace}", Email_Arguments, Email_Configuration)
                echo "---------------------------------------------Something went wrong while security scanning."
                throw(e)
            }
            
        }
    }
    stage('11 -- Post Build') {
        // If all stages pass, send email notification to developers:
        EmailNotification("${RootDir_CIScripts}", "${RootDir_JenkinsWorkspace}", Email_Arguments, Email_Configuration)
        
        // Clean up workspace
        echo "---------------------------------------------Deleting workspace:"
        cleanWs deleteDirs: true, notFailBuild: true
        
        // Copy logs to Integration Server
        PowerShell("${Script_CopyLogs}")
    }
}




