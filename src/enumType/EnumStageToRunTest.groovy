// SharedLibrary/src/enumType/EnumStageToRunTest.groovy
package enumType;

/*
Set When (what stage in pipeline) to run test as enum type which accepts following values:
PreBuild, PreDeployment, PostDeployment
*/
enum EnumStageToRunTest {
    Unknown(0), PreDeployment(1), PostDeployment(2)
    EnumStageToRunTest(int value) {
		this.value = value
	}
	private final int value
}