// SharedLibrary/src/common/StageException.groovy
package utils.common;

// Define custom exception type for all stages:
class StageException extends Exception {
    public Integer StageIndexOfEx
    public String InnerExMsg
    public String CustomExMsg
    public StageException(Integer stageIndexOfEx, Exception ex) {
        this.StageIndexOfEx = stageIndexOfEx
        // Store full stack trace information to variable InnerExMsg:
        StringWriter errors = new StringWriter()
        ex.printStackTrace(new PrintWriter(errors))
        this.InnerExMsg = errors.toString()
        
        switch(stageIndexOfEx) {
            case 0:
                this.CustomExMsg = "Something went wrong while initializing pipeline, please contact CICD team for detailed information."; break;
            case 1:
                this.CustomExMsg = "Something went wrong while parsing deployment request, please contact CICD team for detailed information."; break;
            case 2:
                this.CustomExMsg = "Something went wrong while checking out source, please contact CICD team for detailed information."; break;
            case 3:
                this.CustomExMsg = "Something went wrong while acquiring source dependency tree, please contact CICD team for detailed information."; break;
            case 4:
                this.CustomExMsg = "Something went wrong while executing pre-build tests/security analysis, please contact CICD team for detailed information."; break;
            case 5:
                this.CustomExMsg = "Something went wrong while building/integrating source code, please contact CICD team for detailed information."; break;
            case 6:
                this.CustomExMsg = "Something went wrong while executing pre-deployment tests/security analysis, please contact CICD team for detailed information."; break;
            case 7:
                this.CustomExMsg = "Something went wrong while deploying/publishing artifacts, please contact CICD team for detailed information."; break;
            case 8:
                this.CustomExMsg = "Something went wrong while executing post-deployment tests/security analysis, please contact CICD team for detailed information."; break;
            default:
                this.CustomExMsg = "Unknown error occurs, please contact CICD team for detailed information."; break;
        }
    }
}