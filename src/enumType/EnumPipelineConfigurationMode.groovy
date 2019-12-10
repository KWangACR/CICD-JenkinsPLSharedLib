// SharedLibrary/src/enumType/EnumPipelineConfigurationMode.groovy
package enumType;

/*
Set Configuration Mode of pipeline as enum type which accepts following values:
Debug, Release (set as default value)
*/
enum EnumPipelineConfigurationMode {
    Debug(0), Release(1)
    EnumPipelineConfigurationMode(int value) {
		this.value = value
	}
	private final int value
}