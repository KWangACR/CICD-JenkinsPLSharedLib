import groovy.json.JsonSlurper
import groovy.json.JsonOutput

node('master') {
    stage('Run powershell command') {
        env.JENKINS_NODE_COOKIE = "dontKillMe"
        powershell("Start-Process -FilePath 'powershell.exe' -ArgumentList '1..20 | ForEach-Object { Add-Content C:/Temp/text.txt 111; Start-Sleep 1 }'")
    }
    stage('Next Stage') {
        echo "You will see this if powershell command is successfully executed."
    }
}