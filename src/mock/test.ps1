# # Use module for entity definition and utility tools:
# using module .\ProjAnalyzerConstants.psm1
# using module .\ProjAnalyzerEntity.psm1
# using module .\ProjAnalyzerUtilitiy.Common_Test.psm1
# using module .\ProjAnalyzerUtilitiy.GetSrc_Test.psm1
# using module .\ProjAnalyzerUtilitiy.GetIntrinsicPropSrc.psm1
# using module .\ProjAnalyzerUtilitiy.RestoreAndCompileSrc.psm1




function Test-Me() {
    return "result from Test-Me function in analyzer script"
}


# # Test function to verify [DownloadOrUpdateSrc] function implementation:
# function DownloadOrUpdateSrc_Test() {
#     param(
#         # ACReditPlus:
#         [System.Object]$RepoInfo = @{
#             Url = "https://acrscm.svn.cloudforge.com/devops/trunk/CICD.MockProject"
#             Protocol = "Subversion"
#             Specifier = @{
#                 Type = "Revision"
#                 Value = ""
#             }
#         },
#         [string]$SrcDestFolderPath = "C:\Temp\Projects\CICD.MockProject"
#     )
#     $response = New-Object -TypeName PSResponse
#     try {
#         GetSrcFromSCMRepo -RepoUrl $RepoInfo.Url `
#                           -RepoProtocol $RepoInfo.Protocol `
#                           -RepoBranchSpecifierType $RepoInfo.Specifier.Type `
#                           -RepoBranchSpecifierValue $RepoInfo.Specifier.Value `
#                           -SrcDestFolderPath $SrcDestFolderPath

#         $response.StatusCode = 8000
#     } catch {
#         $response.StatusCode = 9001
#         $response.ExceptionMsg = $_.Exception.Message
#     }
#     return $response 
# }

# # Test function to verify intrinsic properties for a given component is correctly loaded:
# function GetIntrinsicPropForProj_Test() {
#     param(
#         [string]$ProjFilePath
#     )
#     $result = New-Object -TypeName PSResponse
#     try {
#         $projFolderPath = (Get-Item $ProjFilePath).Directory.FullName
#         Set-Location $projFolderPath
#         $projIntrinsicPropManagement = [ProjIntrinsicPropManagement]::new()
#         $projIntrinsicPropManagement.ProjFilePath = $ProjFilePath
#         $projFileInfo = $projIntrinsicPropManagement.GenerateProjFileInfo()
#         $result.StatusCode = 8000
#         $result.Result = $projFileInfo
#     }
#     catch {
#         $result.StatusCode = 9001
#         $result.ExceptionMsg = $_.Exception.Message
#     }
#     return $result
# }

# # Test function to verify intrinsic properties for all components are correctly loaded:
# function LoadFnCompListWithIntrinsicProp_Test() {
#     param(
#         [string]$SrcDestFolderPath = "C:\RQM\Proj\ACReditPlus\DEV\Onetime\Src\acredit\branches\UAT3.0.1"
#     )
#     $result = New-Object -TypeName PSResponse
#     try {
#         Set-Location $SrcDestFolderPath
#         $projIntrinsicPropManagement = [ProjIntrinsicPropManagement]::new()
#         $projFileInfoList = New-Object -TypeName System.Collections.Generic.List[ProjFileInfo]
#         Get-ChildItem $SrcDestFolderPath -Recurse | Where-Object { $_.Extension -in $Script:Const_FileExtensionForCsproj,$Script:Const_FileExtensionForSqlproj,$Script:Const_FileExtensionForSln -or $_.Name -in $Script:Const_FileNameForSqlScriptSchema,$Script:Const_FileNameForNodePackageFile } | ForEach-Object {
#             $projIntrinsicPropManagement.ProjFilePath = $_.FullName
#             $projFileInfo = $projIntrinsicPropManagement.GenerateProjFileInfo()
#             if(($projFileInfo.Category -ne [ProjType]::Unknown) -and ($projFileInfo.TestFramework.Type -ne [ProjTestOrientationType]::Backend)) {
#                 $projFileInfoList.Add($projFileInfo)
#             }
#         }
#         $result.StatusCode = 8000
#         $result.Result = $projFileInfoList
#     }
#     catch {
#         $result.StatusCode = 9001
#         $result.ExceptionMsg = $_.Exception.Message
#     }
#     return $result | ConvertTo-Json -Depth 10
# }