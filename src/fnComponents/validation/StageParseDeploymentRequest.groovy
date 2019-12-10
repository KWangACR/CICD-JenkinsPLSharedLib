// SharedLibrary/src/fnComponents/validation/StageParseDeploymentRequest.groovy
package fnComponents.validation;

// This class defines method to validate every stage implementation:
class StageParseDeploymentRequest {
    public static Boolean ValidateSoftwareComponentProperty(def sourceComponents) {
        def validationResult = [
            validated: true,
            componentProp: ""
        ]
        // No need to check the properties becuase for most of components, they are null:
        // sourceComponents.collect {
        //     it.each { prop, val ->
        //         if(!(prop in ["ExecutableFilePathRelativeToTargetedDeploymentLocation", "Scheduler", "DirectDependecy", "PostBuildEvent"]) && val in ["", null]) {
        //             validationResult.validated = false
        //             validationResult.componentProp = prop
        //         }
        //     }
        // }
        return validationResult
    }
    public static def ValidateStageParseDeploymentRequest(def requestModel_GroovyObj) {
        // if(requestModel_GroovyObj.Result.Project.Name in ["", null]) {
        //     throw new Exception("Project Name is null or empty")
        // } else if(requestModel_GroovyObj.Result.Environment.Name in ["", null]) {
        //     throw new Exception("Environment Name is null or empty")
        // } else if(requestModel_GroovyObj.Result.Source.SourceUrl.SourceUrl in ["", null]) {
        //     throw new Exception("Root Source Url is null or empty")
        // } else if(requestModel_GroovyObj.Result.Source.SourceComponents == null || requestModel_GroovyObj.Result.Source.SourceComponents.empty) {
        //     throw new Exception("Source Component List is null or empty")
        // } else if(!ValidateSoftwareComponentProperty(requestModel_GroovyObj.Result.Source.SourceComponents).validated) {
        //     def softwareComponentProperty = ValidateSoftwareComponentProperty(requestModel_GroovyObj.Result.Source.SourceComponents).componentProp
        //     throw new Exception("A Source Component property: ${softwareComponentProperty} is null or empty")
        // } else if(requestModel_GroovyObj.Result.DeployInfo.Firstname in ["", null]) {
        //     throw new Exception("Requester Firstname is null or empty")
        // } else if(requestModel_GroovyObj.Result.DeployInfo.Lastname in ["", null]) {
        //     throw new Exception("Requester Lastname is null or empty")
        // } else if(requestModel_GroovyObj.Result.DeployInfo.Email in ["", null]) {
        //     throw new Exception("Requester Email is null or empty")
        // }
    }
}