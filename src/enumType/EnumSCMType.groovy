// SharedLibrary/src/enumType/EnumSCMType.groovy
package enumType;

/*
Set Source Code Repository as enum type which accepts following values:
Subversion, Git
*/
enum EnumSCMType {
    Subversion(0), Git(1)
    EnumSCMType(int value) {
		this.value = value
	}
	private final int value
}