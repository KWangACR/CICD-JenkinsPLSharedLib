// SharedLibrary/src/enumType/EnumTestFrameworkValue.groovy
package enumType;

/*
Test Frameworks:
*/
enum EnumTestFrameworkValue {
    Unknown(0), FrontendRelated(1), MSTest(2), xUnit(3), NUnit(4)
    EnumTestFrameworkValue(int value) {
		this.value = value
	}
	private final int value
}