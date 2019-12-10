// SharedLibrary/src/mock/MockData_Fn_WebApplication.groovy
package mock;

// This class contains all mock data to test:
class MockData_Fn_WebApplication {
    public static def MockRequestModel_GroovyObj() {
        return [
            Result: [
                Project: [
                    FullName: "CICD.MockProject.SVN",
                    ShortName: "CI.Mock.S"
                ],
                Environment: [
                    Name: "DEV",
                    BuildFrequency: "Onetime"
                ],
                Source: [
                    SCMRepoList: [
                        [
                            RepoInfo: [
                                Uri: "SVN Repo 1",
                                Protocol: "Subversion",
                                Specifier: [
                                    Type: "Revision",
                                    Value: ""
                                ]
                            ],
                            FnCompList: [
                                [
                                    Selected: true,
                                    IntrinsicProp: [
                                        Name: "Web.App.One",
                                        Category: "Web_Application",
                                        DotnetImplementation: "Framework",
                                        RelativePath: ".\\svn\\Backend\\Dotnet\\Framework\\Web.Apps\\Web.App.One\\Web.App.One.csproj",
                                        CrossProjRefList: []
                                    ],
                                    OperationalProp: [
                                        BuildConfiguration: [
                                            Selected: true,
                                            PackageRepoSources: "https://api.nuget.org/v3/index.json;https://powershell.myget.org/F/powershell-core/api/v3/index.json",
                                            CustomCliList: []
                                        ],
                                        DeployConfiguration: [
                                            Selected: true,
                                            DestinationList: [
                                                [
                                                    Server: [
                                                        Hostname: "CUV-CICDINT.RESTONUAT.local",
                                                        Domain: "RESTONUAT"
                                                    ],
                                                    DeployedDirectory: "E:\\CICD\\Deployment\\Web.App.One",
                                                    IISConfiguration: [
                                                        Website: "",
                                                        ApplicationPool: ""
                                                    ]
                                                ]
                                            ]
                                        ],
                                        PublishConfiguration: [
                                            Selected: false,
                                            DestinationList: []
                                        ]
                                    ]
                                ]
                            ],
                            TestCompList: []
                        ]
                    ]
                ],
                Scan: [
                    Checkmarx: false,
                    Saint: false
                ],
                DeployInfo: [
                    Firstname: "Kevin",
                    Lastname: "Wang",
                    Email: "wkevin@acr.org",
                    SoftwareVersion: "2.0.3",
                    DeployComment: "test comment",
                    JiraBaseUrl: "",
                    JiraIssueKeyList: "",
                    JiraIssueConfirmed: false
                ],
                CheckmarxConfiguration: [
                    ProjectName: "CICD.MockProject.Subversion",
                    VulnerabilityThreshold: [
                        HighSeverity: 0,
                        MediumSeverity: 5,
                        LowSeverity: 20
                    ]
                ]
            ],
            StatusCode: 200,
            Message: "OK"
        ]
    }
}