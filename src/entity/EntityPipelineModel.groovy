// SharedLibrary/src/entity/EntityPipelineModel.groovy
package entity;

// Entity Definition for Request Model containing properties that will be consumed in pipeline:
class EntityPipelineModel {
    // Workspace Path Fields:
    public String BuildOutcomeFolderPathInWorkspace
    public String NugetPackageOutputFolderPathInWorkspace
    public String TempFolderPathInWorkspace
    public String PreBuildTestResultFolderPathInWorkspace
    public String PreDeploymentTestResultFolderPathInWorkspace
    public String PostDeploymentTestResultFolderPathInWorkspace

    // Deployment Request Fields:
    public def Request
}