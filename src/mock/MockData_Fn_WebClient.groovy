// SharedLibrary/src/mock/MockData_Fn_WebClient.groovy
package mock;

// This class contains all mock data to test:
class MockData_Fn_WebClient {
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
                                        Name: "webclient.one",
                                        Category: "Web_Client",
                                        DotnetImplementation: "Unknown",
                                        RelativePath: ".\\svn\\Frontend\\Vue\\webclient.one\\package.json",
                                        CrossProjRefList: []
                                    ],
                                    OperationalProp: [
                                        BuildConfiguration: [
                                            Selected: true,
                                            PackageRepoSources: "",
                                            CustomCliList: [
                                                [
                                                    ExecutionOrder: 2,
                                                    RunningDirectory: ".",
                                                    Command: "npm run build",
                                                    GenerateOutput: true,
                                                    OutputDirectory: "build_output"
                                                ],
                                                [
                                                    ExecutionOrder: 1,
                                                    RunningDirectory: ".",    // Relative path to root folder of project structure
                                                    Command: "npm install",
                                                    GenerateOutput: false,
                                                    OutputDirectory: ""    // Relative path to root folder of project structure
                                                ]
                                            ]
                                        ],
                                        DeployConfiguration: [
                                            Selected: true,
                                            DestinationList: [
                                                [
                                                    Server: [
                                                        Hostname: "CUV-CICDINT.RESTONUAT.local",
                                                        Domain: "RESTONUAT"
                                                    ],
                                                    DeployedDirectory: "E:\\CICD\\Deployment\\webclient.one"
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