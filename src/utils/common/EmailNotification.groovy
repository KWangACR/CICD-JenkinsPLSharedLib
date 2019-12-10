// SharedLibrary/src/common/EmailNotification.groovy
package utils.common;

import entity.EntityPipelineModel
import enumType.EnumSCMType
import enumType.EnumStageToRunTest

/* 
Plugins used:  https://wiki.jenkins.io/display/JENKINS/Email-ext+plugin

This class does 4 things:
1. Tell recipients information of current job
2. Tell recipients information of request
3. Create markup containing Email content
4. Execute Email sending
*/
class EmailNotification {
    // Initialize job information in "emailParams" object to be displayed in email report:
    public static def InitializeEmailJobInfo(pipeline_script, Map emailParams) {
        emailParams.put("RequestID", pipeline_script.params.RequestID)
        emailParams.put("BuildUrl", pipeline_script.env.BUILD_URL)
        emailParams.put("Requester", "")
        emailParams.put("RequesterEmail", "")
        emailParams.put("SoftwareVersion", "")
        emailParams.put("BuildEnvironmentSDLCToken", "")
        emailParams.put("BuildEnvironmentInstanceName", "")
        emailParams.put("BuildFrequency", "")
        emailParams.put("SourceUrl", "")
        emailParams.put("SourceSpecifier", "")
        emailParams.put("DeployComment", "")
        emailParams.put("DateOfBuild", pipeline_script.env.BUILD_TIMESTAMP)
        emailParams.put("CustomExMsg", "")
    }

    // Initialize pipeline stage information in "emailParams" object to be displayed in email report:
    public static def InitializeEmailStageInfo(Map emailParams) {
        emailParams.put("StageInitialization", [Output: "--", Style: ""])
        emailParams.put("StageParseDeploymentRequest", [Output: "--", Style: ""])
        emailParams.put("StageCheckoutSource", [Output: "--", Style: ""])
        emailParams.put("StageAcquireDependencyTree", [Output: "--", Style: ""])
        emailParams.put("StagePreBuildAction", [Output: "--", Style: ""])
        emailParams.put("StageBuildSource", [Output: "--", Style: ""])
        emailParams.put("StagePreDeploymentAction", [Output: "--", Style: ""])
        emailParams.put("StageDeployArtifacts", [Output: "--", Style: ""])
        emailParams.put("StagePostDeploymentAction", [Output: "--", Style: ""])
    }

    // Set pipeline stage information in "emailParams" object to be displayed in email report after each stage has been executed:
    public static def SetEmailStageInfo(Map emailParams, Integer stageIndex, boolean isStageSucceed) {
        def stageInfo = isStageSucceed ? [Output: "OK", Style: "color: green"] : [Output: "Fail", Style: "color: red"]
        switch(stageIndex) {
            case 0:
                emailParams.put("StageInitialization", stageInfo); break;
            case 1:
                emailParams.put("StageParseDeploymentRequest", stageInfo); break;
            case 2:
                emailParams.put("StageCheckoutSource", stageInfo); break;
            case 3:
                emailParams.put("StageAcquireDependencyTree", stageInfo); break;
            case 4:
                emailParams.put("StagePreBuildAction", stageInfo); break;
            case 5:
                emailParams.put("StageBuildSource", stageInfo); break;
            case 6:
                emailParams.put("StagePreDeploymentAction", stageInfo); break;
            case 7:
                emailParams.put("StageDeployArtifacts", stageInfo); break;
            case 8:
                emailParams.put("StagePostDeploymentAction", stageInfo); break;
            default:
                throw new Exception("There is no stage ${stageIndex}.");
        }
    }

    // Set request information in "emailParams" object to be displayed in email report:
    public static def SetEmailRequestInfo(pipeline_script, EntityPipelineModel pipelineModel, Map emailParams) {
        emailParams.put("Requester", pipelineModel.Request.DeployInfo.BasicInfo.RequesterFullname)
        emailParams.put("RequesterEmail", pipelineModel.Request.DeployInfo.BasicInfo.RequesterEmailAddress)
        emailParams.put("SoftwareVersion", pipelineModel.Request.DeployInfo.BasicInfo.SoftwareVersion)
        emailParams.put("BuildEnvironmentSDLCToken", pipelineModel.Request.Environment.SDLCToken)
        emailParams.put("BuildEnvironmentInstanceName", pipelineModel.Request.Environment.DeployInstance.Name)
        emailParams.put("BuildFrequency", pipelineModel.Request.Environment.BuildFrequency)
        emailParams.put("SourceList", pipelineModel.Request.SourceList)
        def sourceRepoListAsString = ""
        pipelineModel.Request.SourceList.collect { sourceRepo ->
            switch(sourceRepo.RepoInfo.Protocol) {
                case EnumSCMType.Subversion.name():
                    sourceRepoListAsString += "${sourceRepo.RepoInfo.Url} (Revision: ${sourceRepo.RepoInfo.Specifier.Value}); "
                    break;
                case EnumSCMType.Git.name():
                    sourceRepoListAsString += "${sourceRepo.RepoInfo.Url}; "
                    break;
            }
        }
        emailParams.put("SourceRepoListAsString", sourceRepoListAsString)
        emailParams.put("DeployComment", pipelineModel.Request.DeployInfo.BasicInfo.DeployComment)
    }

    @NonCPS     // MarkupBuilder doesn't work right in a CPS context(require methods use objects that must be able to be serializable).  It needs to be invoked within a method annotated with @NonCPS
    public static def GenerateEmailMarkup(pipelineSettings, Map emailParams, boolean isPipelineSucceed) {
        def writer = new StringWriter()     // html is written here by markup builder
        def markup = new groovy.xml.MarkupBuilder(writer)       // actual html markup

        def stageInfoMap = pipelineSettings['CommonParams']['StageInfoMap']
        def titleTdStyle = "height: 28px; border: 1px solid black; font-weight: bold; font-size: 15px"
        def contentTdStyle = "border: 1px solid black; height: 22px"
        def componentTdStyle = "font-size: 12px; font-weight: bold"


        markup.html {
            delegate.body {
                // Header table to contain job info and request info:
                delegate.table(style: 'font-size: 15px') {
                    delegate.tr {
                        delegate.td()
                        delegate.td {
                            delegate.b(style: 'font-size: 28px; font-family: Tahoma', "BUILD REPORT")
                        }
                    }
                    delegate.tr(style: 'height: 6px')
                    delegate.tr {
                        delegate.td(style: 'width: 160px', "Request ID:")
                        delegate.td("${emailParams.RequestID}")
                    }
                    delegate.tr {
                        delegate.td("Build Url:")
                        delegate.td("${emailParams.BuildUrl}")
                    }
                    delegate.tr {
                        delegate.td("Requested By:")
                        delegate.td("${emailParams.Requester}  (${emailParams.RequesterEmail})")
                    }
                    delegate.tr {
                        delegate.td("Software Version:")
                        delegate.td("${emailParams.SoftwareVersion}")
                    }
                    delegate.tr {
                        delegate.td("Deploy Environment:")
                        delegate.td("${emailParams.BuildEnvironmentSDLCToken}--${emailParams.BuildEnvironmentInstanceName}  (${emailParams.BuildFrequency})")
                    }
                    delegate.tr {
                        delegate.td("Source Repo:")
                        delegate.td("${emailParams.SourceRepoListAsString}")
                    }
                    delegate.tr {
                        delegate.td("Deploy Comment:")
                        delegate.td("${emailParams.DeployComment}")
                    }
                    delegate.tr {
                        delegate.td("Date of Build:")
                        delegate.td("${emailParams.DateOfBuild}")
                    }
                    if(!isPipelineSucceed) {
                        delegate.tr {
                            delegate.td("Error Message:")
                            delegate.td("${emailParams.CustomExMsg}")
                        }
                    }
                    delegate.tr(style: 'height: 6px')
                }
                // Table to contain pipeline stage info:
                delegate.table(style: "border: 1px solid black; font-size: 14px; font-family: Verdana, Geneva, sans-serif; border-collapse: collapse") {
                    delegate.tr {
                        delegate.td(style: "width: 280px; ${titleTdStyle}", "Stage")
                        delegate.td(style: "width: 360px; ${titleTdStyle}", "Component")
                        delegate.td(style: "width: 80px; ${titleTdStyle}", "Status")
                    }
                    delegate.tr {
                        delegate.td(style: "${contentTdStyle}", stageInfoMap['00'])
                        delegate.td(style: "${contentTdStyle}", "")
                        delegate.td(style: "${emailParams.StageInitialization.Style}; ${contentTdStyle}", "${emailParams.StageInitialization.Output}")
                    }
                    delegate.tr {
                        delegate.td(style: "${contentTdStyle}", stageInfoMap['01'])
                        delegate.td(style: "${contentTdStyle}", "")
                        delegate.td(style: "${emailParams.StageParseDeploymentRequest.Style}; ${contentTdStyle}", "${emailParams.StageParseDeploymentRequest.Output}")
                    }
                    delegate.tr {
                        delegate.td(style: "${contentTdStyle}", stageInfoMap['02'])
                        delegate.td(style: "${contentTdStyle}", "")
                        delegate.td(style: "${emailParams.StageCheckoutSource.Style}; ${contentTdStyle}", "${emailParams.StageCheckoutSource.Output}")
                    }
                    delegate.tr {
                        delegate.td(style: "${contentTdStyle}", stageInfoMap['03'])
                        delegate.td(style: "${contentTdStyle}", "")
                        delegate.td(style: "${emailParams.StageAcquireDependencyTree.Style}; ${contentTdStyle}", "${emailParams.StageAcquireDependencyTree.Output}")
                    }
                    for(sourceRepo in emailParams.SourceList) {
                        def compList = sourceRepo.CompAfterSave.FnCompList + sourceRepo.CompAfterSave.TestCompList
                        for(comp in compList) {
                            if(comp.Selected && comp.OperationalProp.BuildConfiguration.Selected) {
                                delegate.tr {
                                    delegate.td(style: "height: 24px", "")
                                    delegate.td(style: "${contentTdStyle}; ${componentTdStyle}; color: #3498DB", "${comp.IntrinsicProp.Name}")
                                    if(comp.OperationalProp.AcquireDependencyTreeStatus == null) {
                                        delegate.td(style: "${contentTdStyle}; ${componentTdStyle}", "--")
                                    } else if(comp.OperationalProp.AcquireDependencyTreeStatus == true) {
                                        delegate.td(style: "color: green; ${contentTdStyle}; ${componentTdStyle}", "OK")
                                    } else if(comp.OperationalProp.AcquireDependencyTreeStatus == false) {
                                        delegate.td(style: "color: red; ${contentTdStyle}; ${componentTdStyle}", "Fail")
                                    }
                                }
                            }
                        }
                    }
                    delegate.tr {
                        delegate.td(style: "${contentTdStyle}", stageInfoMap['04'])
                        delegate.td(style: "${contentTdStyle}", "")
                        delegate.td(style: "${emailParams.StagePreBuildAction.Style}; ${contentTdStyle}", "${emailParams.StagePreBuildAction.Output}")
                    }
                    delegate.tr {
                        delegate.td(style: "${contentTdStyle}", stageInfoMap['05'])
                        delegate.td(style: "${contentTdStyle}", "")
                        delegate.td(style: "${emailParams.StageBuildSource.Style}; ${contentTdStyle}", "${emailParams.StageBuildSource.Output}")
                    }
                    for(sourceRepo in emailParams.SourceList) {
                        def compList = sourceRepo.CompAfterSave.FnCompList + sourceRepo.CompAfterSave.TestCompList
                        for(comp in compList) {
                            if(comp.Selected && comp.OperationalProp.BuildConfiguration.Selected) {
                                delegate.tr {
                                    delegate.td(style: "height: 24px", "")
                                    delegate.td(style: "${contentTdStyle}; ${componentTdStyle}; color: #3498DB", "${comp.IntrinsicProp.Name}")
                                    if(comp.OperationalProp.BuildStatus == null) {
                                        delegate.td(style: "${contentTdStyle}; ${componentTdStyle}", "--")
                                    } else if(comp.OperationalProp.BuildStatus == true) {
                                        delegate.td(style: "color: green; ${contentTdStyle}; ${componentTdStyle}", "OK")
                                    } else if(comp.OperationalProp.BuildStatus == false) {
                                        delegate.td(style: "color: red; ${contentTdStyle}; ${componentTdStyle}", "Fail")
                                    }
                                }
                            }
                        }
                    }
                    delegate.tr {
                        delegate.td(style: "${contentTdStyle}", stageInfoMap['06'])
                        delegate.td(style: "${contentTdStyle}", "")
                        delegate.td(style: "${emailParams.StagePreDeploymentAction.Style}; ${contentTdStyle}", "${emailParams.StagePreDeploymentAction.Output}")
                    }
                    for(sourceRepo in emailParams.SourceList) {
                        for(testComp in sourceRepo.CompAfterSave.TestCompList) {
                            if(testComp.Selected && testComp.OperationalProp.RunConfiguration.Selected && (testComp.OperationalProp.RunConfiguration.WhenToRun == EnumStageToRunTest.PreDeployment.name())) {
                                delegate.tr {
                                    delegate.td(style: "height: 24px", "")
                                    delegate.td(style: "${contentTdStyle}; ${componentTdStyle}; color: #3498DB", "${testComp.IntrinsicProp.Name}")
                                    if(testComp.OperationalProp.PreDeploymentTestStatus == null) {
                                        delegate.td(style: "${contentTdStyle}; ${componentTdStyle}", "--")
                                    } else if(testComp.OperationalProp.PreDeploymentTestStatus == true) {
                                        delegate.td(style: "color: green; ${contentTdStyle}; ${componentTdStyle}", "OK")
                                    } else if(testComp.OperationalProp.PreDeploymentTestStatus == false) {
                                        delegate.td(style: "color: red; ${contentTdStyle}; ${componentTdStyle}", "Fail")
                                    }
                                }
                            }
                        }
                    }
                    delegate.tr {
                        delegate.td(style: "${contentTdStyle}", stageInfoMap['07'])
                        delegate.td(style: "${contentTdStyle}", "")
                        delegate.td(style: "${emailParams.StageDeployArtifacts.Style}; ${contentTdStyle}", "${emailParams.StageDeployArtifacts.Output}")
                    }
                    for(sourceRepo in emailParams.SourceList) {
                        for(fnComp in sourceRepo.CompAfterSave.FnCompList) {
                            if(fnComp.Selected && fnComp.OperationalProp.DeployConfiguration.Selected) {
                                delegate.tr {
                                    delegate.td(style: "height: 24px", "")
                                    delegate.td(style: "${contentTdStyle}; ${componentTdStyle}; color: #3498DB", "${fnComp.IntrinsicProp.Name}")
                                    if(fnComp.OperationalProp.DeployStatus == null) {
                                        delegate.td(style: "${contentTdStyle}; ${componentTdStyle}", "--")
                                    } else if(fnComp.OperationalProp.DeployStatus == true) {
                                        delegate.td(style: "color: green; ${contentTdStyle}; ${componentTdStyle}", "OK")
                                    } else if(fnComp.OperationalProp.DeployStatus == false) {
                                        delegate.td(style: "color: red; ${contentTdStyle}; ${componentTdStyle}", "Fail")
                                    }
                                }
                            }
                        }
                    }
                    delegate.tr {
                        delegate.td(style: "${contentTdStyle}", stageInfoMap['08'])
                        delegate.td(style: "${contentTdStyle}", "")
                        delegate.td(style: "${emailParams.StagePostDeploymentAction.Style}; ${contentTdStyle}", "${emailParams.StagePostDeploymentAction.Output}")
                    }
                    for(sourceRepo in emailParams.SourceList) {
                        for(testComp in sourceRepo.CompAfterSave.TestCompList) {
                            if(testComp.Selected && testComp.OperationalProp.RunConfiguration.Selected && (testComp.OperationalProp.RunConfiguration.WhenToRun == EnumStageToRunTest.PostDeployment.name())) {
                                delegate.tr {
                                    delegate.td(style: "height: 24px", "")
                                    delegate.td(style: "${contentTdStyle}; ${componentTdStyle}; color: #3498DB", "${testComp.IntrinsicProp.Name}")
                                    if(testComp.OperationalProp.PostDeploymentTestStatus == null) {
                                        delegate.td(style: "${contentTdStyle}; ${componentTdStyle}", "--")
                                    } else if(testComp.OperationalProp.PostDeploymentTestStatus == true) {
                                        delegate.td(style: "color: green; ${contentTdStyle}; ${componentTdStyle}", "OK")
                                    } else if(testComp.OperationalProp.PostDeploymentTestStatus == false) {
                                        delegate.td(style: "color: red; ${contentTdStyle}; ${componentTdStyle}", "Fail")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        return writer.toString()
    }

    // Execute Email Sending
    public static def SendEmail(pipeline_script, pipelineSettings, Map emailParams, boolean isPipelineSucceed, String customExMsg) {
        String buildStatus = isPipelineSucceed ? "Success" : "Failure"
        if(!isPipelineSucceed) emailParams.put("CustomExMsg", customExMsg)
        pipeline_script.emailext (
            mimeType: 'text/html',
            subject: "${pipeline_script.env.JOB_NAME} (${emailParams.BuildEnvironmentSDLCToken} -- ${emailParams.BuildEnvironmentInstanceName}) -- [${buildStatus}]",
            body: this.GenerateEmailMarkup(pipelineSettings, emailParams,  isPipelineSucceed),
            attachmentsPattern: 'TestResult/**',    // Attach any test report files inside TestResult folder, including PreBuildAction, PreDeploymentAction, and PostDeploymentAction.
            to: pipelineSettings['CommonParams']['Email']['RecipientList']["${pipeline_script.env.JOB_NAME}"]
        )
    }
}