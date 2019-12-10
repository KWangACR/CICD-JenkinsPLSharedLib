// SharedLibrary/src/utils/stage/StageParseDeploymentRequest.groovy
package utils.stage;

import java.nio.file.Paths
import enumType.EnumEnvironmentToken
import enumType.EnumSourceCompileMode
import enumType.EnumServerDomain
import static utils.common.Helpers.*

// Define utility methods that will be used in stage "Parse Deployment Request":
class StageParseDeploymentRequest {
    // Retrieve returned object from web api via http request:
    public static def RetrieveObjViaHttpRequest(pipeline_script, String webApiUrl, String httpMode) {
        def returnedObj = pipeline_script.httpRequest httpMode: httpMode, url: webApiUrl
        return returnedObj.content
    }
    // Get source code compile mode based on build environment selected:
    public static def GetSourceCompileMode(String buildEnvironment) {
        EnumSourceCompileMode compileMode = null
        switch(buildEnvironment) {
            case [EnumEnvironmentToken.DEV.name()]:
                compileMode = EnumSourceCompileMode.Debug; break;
            case [EnumEnvironmentToken.UAT.name(), EnumEnvironmentToken.STG.name(), EnumEnvironmentToken.PROD.name()]:
                compileMode = EnumSourceCompileMode.Release; break;
        }
        return compileMode
    }
    // Get deploy destination domain based on build environment selected:
    public static def GetDeployDestinationDomain(String buildEnvironment) {
        EnumServerDomain serverDomain = null
        switch(buildEnvironment) {
            case [EnumEnvironmentToken.DEV.name(), EnumEnvironmentToken.UAT.name(), EnumEnvironmentToken.STG.name()]:
                serverDomain = EnumServerDomain.RESTONUAT; break;
            case [EnumEnvironmentToken.PROD.name()]:
                serverDomain = EnumServerDomain.DMZ; break;
        }
        return serverDomain
    }
    // Construct Sql connection string from each field:
    public static def ConstructSqlConnectionString(String dbServer, String dbDatabase, String dbUsername, String dbPassword) {
        return "Server=${dbServer};Database=${dbDatabase};User Id=${dbUsername};Password=${dbPassword}";
    }
}