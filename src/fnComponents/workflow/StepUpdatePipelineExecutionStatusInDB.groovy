// SharedLibrary/src/fnComponents/workflow/StepUpdatePipelineExecutionStatusInDB.groovy
package fnComponents.workflow;

import enumType.EnumPipelineExecutionStatus
import static utils.common.Helpers.*

class StepUpdatePipelineExecutionStatusInDB {
    public static def UpdatePipelineExecutionStatusInDB(pipeline_script, pipelineSettings, EnumPipelineExecutionStatus pipelineExecutionStatus) {
        try {
            // Send request to SDLCGateway.api to update current pipeline execution status:
            def updatePipelineExecutionStatusInDBParams = pipelineSettings['StepParams']['UpdatePipelineExecutionStatusInDB']
            def pipelineExecutionInfo = [
                RequestID: pipeline_script.params.RequestID,
                BuildUrl: pipeline_script.env.BUILD_URL,
                BuildNumber: pipeline_script.env.BUILD_NUMBER,
                Status: EnumPipelineExecutionStatus.Unknown.value
            ]
            switch(pipelineExecutionStatus) {
                case EnumPipelineExecutionStatus.Started:
                    pipelineExecutionInfo.Status = EnumPipelineExecutionStatus.Started.value
                    break;
                case EnumPipelineExecutionStatus.Successful:
                    pipelineExecutionInfo.Status = EnumPipelineExecutionStatus.Successful.value
                    break;
                case EnumPipelineExecutionStatus.Failed:
                    pipelineExecutionInfo.Status = EnumPipelineExecutionStatus.Failed.value
                    break;
            }
            def returnedJsonString = pipeline_script.httpRequest contentType: 'APPLICATION_JSON',
                                                                 url: updatePipelineExecutionStatusInDBParams['WebApi']['Url'],
                                                                 httpMode: updatePipelineExecutionStatusInDBParams['WebApi']['HttpMode'],
                                                                 requestBody: ConvertGroovyObjToJsonString(pipelineExecutionInfo)
            def responseGroovyObj = ConvertJsonStringToGroovyObj(returnedJsonString.content)
            if(responseGroovyObj.StatusCode != 200) {
                pipeline_script.echo responseGroovyObj.Message
                throw new Exception(responseGroovyObj.Message)
            }
        } catch(Exception ex) {
            throw ex
        }
    }
}