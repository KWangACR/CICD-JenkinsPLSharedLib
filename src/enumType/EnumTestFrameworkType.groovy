// SharedLibrary/src/enumType/EnumTestFrameworkType.groovy
package enumType;

/*
Test Frameworks:
*/
enum EnumTestFrameworkType {
    None(0), Backend(1), Frontend(2)
    EnumTestFrameworkType(int value) {
		this.value = value
	}
	private final int value
}