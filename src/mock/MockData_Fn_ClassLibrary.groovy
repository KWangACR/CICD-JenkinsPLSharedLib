// SharedLibrary/src/mock/MockData_Fn_ClassLibrary.groovy
package mock;

// This class contains all mock data to test:
class MockData_Fn_ClassLibrary {
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
                                        Name: "Class.Lib.Two",
                                        Category: "Class_Library",
                                        DotnetImplementation: "Framework",
                                        RelativePath: ".\\svn\\Backend\\Dotnet\\Framework\\Class.Libs\\Class.Lib.Two\\Class.Lib.Two.csproj",
                                        CrossProjRefList: []
                                    ],
                                    OperationalProp: [
                                        BuildConfiguration: [
                                            Selected: true,
                                            CustomCliList: []
                                        ],
                                        DeployConfiguration: [
                                            Selected: false,
                                            DestinationList: []
                                        ],
                                        PublishConfiguration: [
                                            Selected: true,
                                            DestinationList: [
                                                [
                                                    PackageSourceUri: "",
                                                    ApiKey: "",
                                                    PackageVersion: "1.0.0"
                                                ]
                                            ]
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