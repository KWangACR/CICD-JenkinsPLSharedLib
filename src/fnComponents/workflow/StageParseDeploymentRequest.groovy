// SharedLibrary/src/fnComponents/workflow/StageParseDeploymentRequest.groovy
package fnComponents.workflow;

import java.text.MessageFormat
import java.nio.file.Paths
import entity.EntityPipelineModel
import enumType.EnumPipelineConfigurationMode
import enumType.EnumSourceComponentType
import enumType.EnumDotnetImplementation
import enumType.EnumTestFrameworkType
import enumType.EnumTestFrameworkValue
import static utils.common.Helpers.*
import static utils.common.EmailNotification.*
import static utils.stage.StageParseDeploymentRequest.*
import static fnComponents.validation.StageParseDeploymentRequest.*

import static mock.MockData_Fn_WebClient.*


class StageParseDeploymentRequest {
    // Set Properties in Request Model object (passed in as parameter):
    public static def PLParseDeploymentRequest(pipeline_script, Integer stageIndex, pipelineSettings, EntityPipelineModel pipelineModel, Map emailParams) {
        def commonParams = pipelineSettings['CommonParams']
        def parseRequestParams = pipelineSettings['StageParams']['ParseDeploymentRequest']
        
        try {
            // Retrieve request model from RQMService web api:
            def RequestModel_GroovyObj = null

            // If the pipeline is configured as "Debug" mode, use the mocked request model in MockData.groovy:
            switch(pipeline_script.params.ConfigurationMode) {
                case EnumPipelineConfigurationMode.Debug.name():
                    RequestModel_GroovyObj = MockRequestModel_GroovyObj()
                    break;
                case EnumPipelineConfigurationMode.Release.name():
                    def RequestModel_JsonString = RetrieveObjViaHttpRequest(pipeline_script, MessageFormat.format(parseRequestParams['GetRequestModel']['WebApiUrl'], "${pipeline_script.params.RequestID}"), parseRequestParams['GetRequestModel']['WebApiMode'])
                    RequestModel_GroovyObj = ConvertJsonStringToGroovyObj(RequestModel_JsonString)
                    break;
            }


            // // Check whether properties in retrieved request model are correct:
            // ValidateStageParseDeploymentRequest(RequestModel_GroovyObj)


            // Bind requestModel retrieved from web api to "Request" property of object of PipelineModel class:
            pipelineModel.Request = RequestModel_GroovyObj.Result

            def projectName = pipelineModel.Request.Project.Name
            def buildEnvironmentSDLCToken = pipelineModel.Request.Environment.SDLCToken
            def buildEnvironmentInstanceName = pipelineModel.Request.Environment.DeployInstance.Name
            def buildFrequency = pipelineModel.Request.Environment.BuildFrequency
            pipeline_script.currentBuild.displayName = "${pipeline_script.env.BUILD_NUMBER} [${buildEnvironmentSDLCToken}] [${buildEnvironmentInstanceName}] (${buildFrequency})"    // Change display name of current build to make it more verbose
            pipelineModel.Request.Environment.RootConfigDir = MessageFormat.format(parseRequestParams['RootConfigDir'], projectName, buildEnvironmentSDLCToken, buildEnvironmentInstanceName, buildFrequency)
            pipelineModel.Request.SourceList.collect { scmRepo ->
                // Functional Components:
                scmRepo.CompAfterSave.FnCompList.collect { fnComp ->
                    def compName = fnComp.IntrinsicProp.Name
                    fnComp.IntrinsicProp.Category = EnumSourceComponentType.values()[fnComp.IntrinsicProp.Category].name()    // Parse enum int to string
                    def compCategory = fnComp.IntrinsicProp.Category
                    def compIdentifier = GetSourceCompIdentifier(compCategory, compName)
                    def compProjFolderRelativePath = Paths.get(fnComp.IntrinsicProp.RelativePath).getParent().toString()
                    fnComp.IntrinsicProp.DotnetImplementation = EnumDotnetImplementation.values()[fnComp.IntrinsicProp.DotnetImplementation].name()
                    fnComp.IntrinsicProp.BuildConfigurationMode = GetSourceCompileMode(buildEnvironmentSDLCToken).name()
                    fnComp.IntrinsicProp.SourceConfigDir = "${pipelineModel.Request.Environment.RootConfigDir}/${compIdentifier}"
                    fnComp.IntrinsicProp.SourceBuildOutcomeDir = MessageFormat.format(parseRequestParams['SourceBuildOutcomeDir'], pipelineModel.BuildOutcomeFolderPathInWorkspace, compIdentifier)
                    fnComp.IntrinsicProp.SourceNugetPackageOutput = MessageFormat.format(parseRequestParams['SourceNugetPackageOutput'], pipelineModel.NugetPackageOutputFolderPathInWorkspace, compIdentifier)
                    fnComp.IntrinsicProp.SourceTempDir = MessageFormat.format(parseRequestParams['SourceTempDir'], pipelineModel.TempFolderPathInWorkspace, compIdentifier)
                    switch(compCategory) {
                        case EnumSourceComponentType.Database_Script.name():
                            fnComp.IntrinsicProp.SortedSqlScriptList = []; break;
                    }
                    // For each functional component, set action status for each stage to "false" as initial value.  It will be reset to "true"/"false" in PSUtility depending on action status.
                    // This value will be used to list action status for components in email notification:
                    fnComp.OperationalProp.AcquireDependencyTreeStatus = null
                    fnComp.OperationalProp.BuildStatus = null
                    fnComp.OperationalProp.DeployStatus = null
                    fnComp.OperationalProp.BuildConfiguration.CustomCliList.collect { cli ->
                        cli.ExecutionDirectory = Paths.get(scmRepo.RepoInfo.RootSourceDirForPipelineExecution, compProjFolderRelativePath, cli.ExecutionDirectory).normalize().toString()
                        cli.OutputDirectory = Paths.get(scmRepo.RepoInfo.RootSourceDirForPipelineExecution, compProjFolderRelativePath, cli.OutputDirectory).normalize().toString()
                    }
                    fnComp.OperationalProp.DeployConfiguration.DestinationList.collect { dest ->
                        switch(compCategory) {
                            case [EnumSourceComponentType.Database_Script.name(), EnumSourceComponentType.Database_MsProject.name()]:
                                def dbServer = dest.ServerIdentifier
                                def dbDatabase = dest.SqlConfiguration.DatabaseName
                                def dbUsername = dest.SqlConfiguration.Username
                                def dbPassword = dest.SqlConfiguration.Password
                                def connectionString = ConstructSqlConnectionString(dbServer, dbDatabase, dbUsername, dbPassword)
                                dest.SqlCredential = [
                                    ConnectionString: connectionString,
                                    Server: dbServer,
                                    Database: dbDatabase,
                                    Username: dbUsername,
                                    Password: dbPassword
                                ]; break;
                            case [EnumSourceComponentType.Console_Application.name(),
                                  EnumSourceComponentType.Windows_Forms.name(),
                                  EnumSourceComponentType.Windows_Service.name(),
                                  EnumSourceComponentType.Web_Service.name(),
                                  EnumSourceComponentType.Web_Application.name(),
                                  EnumSourceComponentType.Web_Site.name(),
                                  EnumSourceComponentType.Web_Client.name(),
                                  EnumSourceComponentType.Web_Client_Node.name(),
                                  EnumSourceComponentType.Web_Client_Maven.name(),
                                  EnumSourceComponentType.PSUtility.name()]:
                                def serverDomain = GetDeployDestinationDomain(buildEnvironmentSDLCToken).name()
                                dest.WinRMCredential = [
                                    Username: commonParams['WinRM'][serverDomain]['Username'],
                                    Password: DecryptPasswordEncrypted(commonParams['WinRM'][serverDomain]['EncryptedPassword'])
                                ]; break;
                        }
                        dest.DestBackupDir = MessageFormat.format(parseRequestParams['DestBackupDir'], compName)
                        dest.DestTempDir = MessageFormat.format(parseRequestParams['DestTempDir'], pipeline_script.env.JOB_NAME, pipeline_script.env.BUILD_NUMBER, pipeline_script.params.RequestID, compName)
                        dest.TaskSchedulerCredential = [
                            Username: commonParams['TaskScheduler']['Username'],
                            Password: DecryptPasswordEncrypted(commonParams['TaskScheduler']['EncryptedPassword'])
                        ]
                    }
                }
                // Test Components:
                scmRepo.CompAfterSave.TestCompList.collect { testComp ->
                    def compName = testComp.IntrinsicProp.Name
                    testComp.IntrinsicProp.Category = EnumSourceComponentType.values()[testComp.IntrinsicProp.Category].name()
                    def compCategory = testComp.IntrinsicProp.Category
                    def compIdentifier = GetSourceCompIdentifier(compCategory, compName)
                    def compProjFolderRelativePath = Paths.get(testComp.IntrinsicProp.RelativePath).getParent().toString()
                    testComp.IntrinsicProp.DotnetImplementation = EnumDotnetImplementation.values()[testComp.IntrinsicProp.DotnetImplementation].name()
                    testComp.IntrinsicProp.TestFramework.Type = EnumTestFrameworkType.values()[testComp.IntrinsicProp.TestFramework.Type].name()
                    testComp.IntrinsicProp.TestFramework.Value = EnumTestFrameworkValue.values()[testComp.IntrinsicProp.TestFramework.Value].name()
                    testComp.IntrinsicProp.BuildConfigurationMode = GetSourceCompileMode(buildEnvironmentSDLCToken).name()
                    testComp.IntrinsicProp.SourceConfigDir = "${pipelineModel.Request.Environment.RootConfigDir}/${compIdentifier}"
                    testComp.IntrinsicProp.SourceBuildOutcomeDir = MessageFormat.format(parseRequestParams['SourceBuildOutcomeDir'], pipelineModel.BuildOutcomeFolderPathInWorkspace, compIdentifier)
                    testComp.IntrinsicProp.SourceTempDir = MessageFormat.format(parseRequestParams['SourceTempDir'], pipelineModel.TempFolderPathInWorkspace, compIdentifier)
                    testComp.IntrinsicProp.SourcePreBuildTestResultDir = MessageFormat.format(parseRequestParams['SourcePreBuildTestResultDir'], pipelineModel.PreBuildTestResultFolderPathInWorkspace, compIdentifier)
                    testComp.IntrinsicProp.SourcePreDeploymentTestResultDir = MessageFormat.format(parseRequestParams['SourcePreDeploymentTestResultDir'], pipelineModel.PreDeploymentTestResultFolderPathInWorkspace, compIdentifier)
                    testComp.IntrinsicProp.SourcePostDeploymentTestResultDir = MessageFormat.format(parseRequestParams['SourcePostDeploymentTestResultDir'], pipelineModel.PostDeploymentTestResultFolderPathInWorkspace, compIdentifier)
                    // For each test component, set action status for each stage to "false" as initial value.  It will be reset to "true" in PSUtility if action is successful.
                    // This value will be used to list action status for components in email notification:
                    testComp.OperationalProp.AcquireDependencyTreeStatus = null
                    testComp.OperationalProp.BuildStatus = null
                    testComp.OperationalProp.PreDeploymentTestStatus = null
                    testComp.OperationalProp.PostDeploymentTestStatus = null
                    testComp.OperationalProp.BuildConfiguration.CustomCliList.collect { cli ->
                        cli.ExecutionDirectory = Paths.get(scmRepo.RepoInfo.RootSourceDirForPipelineExecution, compProjFolderRelativePath, cli.ExecutionDirectory).normalize().toString()
                        cli.OutputDirectory = Paths.get(scmRepo.RepoInfo.RootSourceDirForPipelineExecution, compProjFolderRelativePath, cli.OutputDirectory).normalize().toString()
                    }
                    testComp.OperationalProp.RunConfiguration.CustomCliList.collect { cli ->
                        cli.ExecutionDirectory = Paths.get(scmRepo.RepoInfo.RootSourceDirForPipelineExecution, compProjFolderRelativePath, cli.ExecutionDirectory).normalize().toString()
                        cli.OutputDirectory = Paths.get(scmRepo.RepoInfo.RootSourceDirForPipelineExecution, compProjFolderRelativePath, cli.OutputDirectory).normalize().toString()
                    }
                }
            }

            SetEmailStageInfo(emailParams, stageIndex, true)
        } catch(Exception ex) {
            SetEmailStageInfo(emailParams, stageIndex, false)
            throw ex
        }
    }
}