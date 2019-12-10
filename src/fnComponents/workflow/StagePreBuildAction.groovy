// SharedLibrary/src/fnComponents/workflow/StagePreBuildAction.groovy
package fnComponents.workflow;

import entity.EntityPipelineModel
import static utils.common.EmailNotification.*
import static utils.stage.StagePreBuildAction.*

class StagePreBuildAction {
    public static def PLPreBuildAction(pipeline_script, Integer stageIndex, pipelineSettings, EntityPipelineModel pipelineModel, Map emailParams) {
        def preBuildActionParams = pipelineSettings['StageParams']['PreBuildAction']
        
        try {
            // Run Checkmarx Scan:
            if(pipelineModel.Request.SecurityAnalysis.Checkmarx) {
                CheckmarxScan(pipeline_script, preBuildActionParams['Checkmarx']['CredentialsId'],
                                               preBuildActionParams['Checkmarx']['ServerUri'],
                                               pipelineModel.Request.CheckmarxConfiguration.ProjectName,
                                               pipelineModel.Request.CheckmarxConfiguration.VulnerabilityThreshold.HighSeverity,
                                               pipelineModel.Request.CheckmarxConfiguration.VulnerabilityThreshold.MediumSeverity,
                                               pipelineModel.Request.CheckmarxConfiguration.VulnerabilityThreshold.LowSeverity)
            }
            
            SetEmailStageInfo(emailParams, stageIndex, true)
        } catch(Exception ex) {
            SetEmailStageInfo(emailParams, stageIndex, false)
            throw ex
        }
    }
}