// SharedLibrary/src/mock/MockData_Fn_DatabaseMsProject.groovy
package mock;

// This class contains all mock data to test:
class MockData_Fn_DatabaseMsProject {
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
                                        Name: "DB.Projects",
                                        Category: "Database_MsProject",
                                        DotnetImplementation: "Framework",
                                        RelativePath: ".\\svn\\Backend\\Sql\\DB.Projects\\DB.Projects\\DB.Projects.sqlproj",
                                        CrossProjRefList: []
                                    ],
                                    OperationalProp: [
                                        BuildConfiguration: [
                                            Selected: true,
                                            CustomCliList: []
                                        ],
                                        DeployConfiguration: [
                                            Selected: true,
                                            DestinationList: [
                                                [
                                                    SqlCredential: [
                                                        ConnectionString: "Data Source=UAT-SQL2012.RESTONUAT.local;Initial Catalog=DevOps;User ID=devops_user;Password=e93006799694c7-1F"
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