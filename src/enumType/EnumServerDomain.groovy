// SharedLibrary/src/enumType/EnumServerDomain.groovy
package enumType;

/*
Set Server Domain as enum type which accepts following values:
RESTONUAT, DMZ
*/
enum EnumServerDomain {
    RESTONUAT(0), DMZ(1)
    EnumServerDomain(int value) {
		this.value = value
	}
	private final int value
}