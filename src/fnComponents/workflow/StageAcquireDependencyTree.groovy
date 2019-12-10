// SharedLibrary/src/fnComponents/workflow/StageAcquireDependencyTree.groovy
package fnComponents.workflow;

import java.text.MessageFormat
import entity.EntityPipelineModel
import static utils.common.EmailNotification.*
import static utils.common.Helpers.*

class StageAcquireDependencyTree {
    public static def PLAcquireDependencyTree(pipeline_script, Integer stageIndex, pipelineSettings, EntityPipelineModel pipelineModel, Map emailParams) {
        def acquireDependencyTreeParams = pipelineSettings['StageParams']['AcquireDependencyTree']

        try {
            def requestSourceList = ConvertGroovyObjToJsonString(pipelineModel.Request.SourceList)

            // Acquire dependency tree for funtional and test components:
            def acquireDependencyTreePSScriptPath = acquireDependencyTreeParams['PSScriptPath']
            def acquireDependencyTreePSCmdToExecute = ". '${acquireDependencyTreePSScriptPath}'; AcquireCompDependencyTree -RequestSourceList '${requestSourceList}'"
            def psResponseJsonString = pipeline_script.powershell(returnStdout: true, script: acquireDependencyTreePSCmdToExecute)
            def psResponseGroovyObj = ConvertJsonStringToGroovyObj(psResponseJsonString)
            pipelineModel.Request.SourceList = psResponseGroovyObj.Result
            emailParams.put("SourceList", psResponseGroovyObj.Result)    // Bind "AcquireDependencyTreeStatus" to SourceList
            if(psResponseGroovyObj.ExitCode == 0) {
                SetEmailStageInfo(emailParams, stageIndex, true)
            } else {
                throw new Exception(psResponseGroovyObj.ExceptionMsg)
            }
        } catch(Exception ex) {
            SetEmailStageInfo(emailParams, stageIndex, false)
            throw ex
        }
    }
}