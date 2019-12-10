// SharedLibrary/src/mock/MockData_Fn_ConsoleApp.groovy
package mock;

// This class contains all mock data to test:
class MockData_Fn_ConsoleApp {
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
                                        Name: "Console.App.Two",
                                        Category: "Console_Application",
                                        DotnetImplementation: "Framework",
                                        RelativePath: ".\\svn\\Backend\\Dotnet\\Framework\\Console.Apps\\Console.App.Two\\Console.App.Two.csproj",
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
                                                    DeployedDirectory: "E:\\CICD\\Deployment\\Console.App.Two",
                                                    Scheduler: [
                                                        Type: "Params",    // "Param" or "Xml"
                                                        SettingList: [
                                                            [
                                                                Guid: "01bdcd9b-39f9-47df-9787-e8dc746ecdef",
                                                                ScheduleType: "WEEKLY",    // /SC
                                                                Modifier: 1,    // /MO
                                                                Day: "SAT,SUN",    // /D
                                                                StartTime: "21:08",    // /ST (e.g. 08::00)
                                                                EndTime: "",    // /ET
                                                                StartDate: "",    // /SD (e.g. 11/20/2019)
                                                                EndDate: ""    // /ED
                                                            ],
                                                            [
                                                                Guid: "cdbe9e45-c802-4e67-b7e8-fc64ed0a7395",
                                                                ScheduleType: "MINUTE",
                                                                Modifier: 45,
                                                                Day: "",
                                                                StartTime: "",
                                                                EndTime: "",
                                                                StartDate: "",
                                                                EndDate: ""
                                                            ]
                                                        ]
                                                    ]
                                                    // Scheduler: [
                                                    //     Type: "Xml",
                                                    //     SettingList: [
                                                    //         [
                                                    //             Guid: "",
                                                    //             FilePath: ""
                                                    //         ],
                                                    //         [
                                                    //             Guid: "",
                                                    //             FilePath: ""
                                                    //         ]
                                                    //     ]
                                                    // ],
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