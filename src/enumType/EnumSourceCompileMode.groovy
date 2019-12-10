// SharedLibrary/src/enumType/EnumSourceCompileMode.groovy
package enumType;

/*
Set Configuration Mode of pipeline as enum type which accepts following values:
Debug, Release (set as default value)
*/
enum EnumSourceCompileMode {
    Debug(0), Release(1)
    EnumSourceCompileMode(int value) {
		this.value = value
	}
	private final int value
}