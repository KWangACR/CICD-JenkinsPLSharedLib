// SharedLibrary/src/common/Logging.groovy
package utils.common;

import java.io.*
import java.util.logging.*

// Use java.util.logging.Logger utility to log information customized by user:
class Logging {
    private String _logFileFullPath
    public Logging(String logDirPath, String logFileName) {
        def suppliedDir = new File(logDirPath)
        if(!suppliedDir.exists()) {
            new File(logDirPath).mkdirs()
        }
        _logFileFullPath = "${logDirPath}/${logFileName}"
    }
    public String GenerateInfoLog(pipelineSettings) {
        def stageInfoMap = pipelineSettings['CommonParams']['StageInfoMap']
        String logContent = "\r\nPipeline Status:  Success\r\nStage Info:\r\n"
        stageInfoMap.each { k, v -> 
            logContent += "------ Stage ${v}: Success\r\n"
        }
        logContent += "Purpose:  Info\r\n\r\n"
        return logContent
    }
    public String GenerateErrorLog(pipelineSettings, Integer stageIndexOfEx, String innerExMsg) {
        def stageInfoMap = pipelineSettings['CommonParams']['StageInfoMap']
        String logContent = "\r\nPipeline Status:  Failure\r\nStage Info:\r\n"
        stageInfoMap.each { k, v -> 
            if(k.toInteger() < stageIndexOfEx) {
                logContent += "------ Stage ${v}: Success\r\n"
            } else if(k.toInteger() == stageIndexOfEx) {
                logContent += "------ Stage ${v}: Failure\r\n"
                logContent += "Stack Trace:\r\n ${innerExMsg}\r\n"
            }
        }
        logContent += "Purpose:  Error\r\n\r\n"
        return logContent
    }
    public String GenerateRollbackInfoLog() {
        String logContent = "\r\nRollback Status:  Success\r\nStage Info:\r\n"
        return logContent
    }
    public void Log(String logContent) {
        Logger logger = Logger.getLogger("")
        FileHandler fh = new FileHandler(_logFileFullPath, true);
        fh.setFormatter(new SimpleFormatter());
        logger.addHandler(fh);
        logger.setLevel(Level.INFO);
        logger.info(logContent)
        fh.close()
    }
}