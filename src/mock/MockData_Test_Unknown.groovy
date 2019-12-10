// SharedLibrary/src/mock/MockData_Test_Unknown.groovy
package mock;

// This class contains all mock data to test:
class MockData_Test_Unknown {
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
                                        Name: "webclient.one",
                                        Category: "Web_Client",
                                        DotnetImplementation: "Unknown",
                                        TestFramework: "Unknown",
                                        RelativePath: ".\\svn\\Frontend\\Vue\\webclient.one\\package.json",
                                        CrossProjRefList: []
                                    ],
                                    OperationalProp: [
                                        BuildConfiguration: [
                                            Selected: true,
                                            PackageRepoSources: "",
                                            CustomCliList: [
                                                [
                                                    ExecutionOrder: 1,
                                                    RunningDirectory: ".",
                                                    Command: "npm install",
                                                    GenerateOutput: false,
                                                    OutputDirectory: ""
                                                ]
                                            ]
                                        ],
                                        RunConfiguration: [
                                            Selected: true,
                                            WhenToRun: "PreDeployment",
                                            CustomCliList: [
                                                [
                                                    ExecutionOrder: 1,
                                                    RunningDirectory: ".",
                                                    Command: "npm run test:unit",
                                                    GenerateTestReport: true,
                                                    TestReportFile: "tests/unit/report/test_output.xml"
                                                ]
                                            ]
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