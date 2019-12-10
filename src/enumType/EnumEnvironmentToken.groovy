// SharedLibrary/src/enumType/EnumEnvironmentToken.groovy
package enumType;

/*
Set Environment Category as enum type which accepts following values:
DEV;  UAT;  STG;  PROD
*/
enum EnumEnvironmentToken {
    DEV(0), UAT(1), STG(2), PROD(3)
    EnumEnvironmentToken(int value) {
		this.value = value
	}
	private final int value
}