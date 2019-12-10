// SharedLibrary/src/enumType/EnumDotnetImplementation.groovy
package enumType;

/*
Set Server Domain as enum type which accepts following values:
RESTONUAT, DMZ
*/
enum EnumDotnetImplementation {
    Unknown(0), Framework(1), Standard(2), Core(3)
    EnumDotnetImplementation(int value) {
		this.value = value
	}
	private final int value
}