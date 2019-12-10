// SharedLibrary/src/common/Helpers.groovy
package utils.common;

// import groovy.json.JsonSlurper
import groovy.json.JsonSlurperClassic 
import groovy.json.JsonOutput
import groovy.util.XmlSlurper
import hudson.util.Secret

class Helpers {
    // Convert json string to groovy object so that its properties can be retrieved:
    @NonCPS
    public static def ConvertJsonStringToGroovyObj(String jsonString) {
        // def jsonSlurperInstance = new JsonSlurper()
        def jsonSlurperInstance = new JsonSlurperClassic()
        def groovyObj = jsonSlurperInstance.parseText(jsonString)
        return groovyObj
    }
    // Convert groovy object to json string and pass it to powershell script:
    public static def ConvertGroovyObjToJsonString(def groovyObj) {
        return JsonOutput.toJson(groovyObj)
    }
    // Convert xml file to groovy object so that its fields can be retrieved:
    @NonCPS
    public static def ConvertXmlFileToGroovyObj(String xmlFilePath) {
        def xmlSlurperInstance = new XmlSlurper()
        def groovyObj = xmlSlurperInstance.parseText(new File(xmlFilePath).getText())
        return groovyObj
    }
    // Convert semicolon separated string to list:
    public static def ConvertDelimiterSeparatedStringToList(String originalStr, String delimiter) {
        return originalStr.split("\\s*${delimiter}\\s*")
    }
    // Decrypt password encrypted using hudson.util.Secret:
    public static def DecryptPasswordEncrypted(String encryptedPassword) {
        return Secret.fromString(encryptedPassword).getPlainText()
    }
    // Generate component identifier based on component name and component category:
    public static def GetSourceCompIdentifier(String compCategory, String compName) {
        return "${compCategory}--${compName}"
    }
}