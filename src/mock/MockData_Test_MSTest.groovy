// SharedLibrary/src/mock/MockData_Test_MSTest.groovy
package mock;

// This class contains all mock data to test:
class MockData_Test_MSTest {
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
                            FnCompList: [],
                            TestCompList: [
                                [
                                    Selected: true,
                                    IntrinsicProp: [
                                        Name: "Console.App.One.Test.MSTest",
                                        Category: "Class_Library",
                                        DotnetImplementation: "Framework",
                                        TestFramework: "MSTest",
                                        RelativePath: ".\\svn\\Backend\\Dotnet\\Framework\\Console.Apps\\Console.App.One.Test.MSTest\\Console.App.One.Test.MSTest.csproj",
                                        CrossProjRefList: []
                                    ],
                                    OperationalProp: [
                                        BuildConfiguration: [
                                            Selected: true,
                                            PackageRepoSources: "https://api.nuget.org/v3/index.json;https://powershell.myget.org/F/powershell-core/api/v3/index.json",    // added in UI
                                            CustomCliList: []
                                        ],
                                        RunConfiguration: [
                                            Selected: true,
                                            WhenToRun: "PreDeployment",
                                            CustomCliList: []
                                        ]
                                    ]
                                ]
                            ]
                        ]
                    ]
                ],
                Scan: [
                    Checkmarx: false,
                    Saint: true
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