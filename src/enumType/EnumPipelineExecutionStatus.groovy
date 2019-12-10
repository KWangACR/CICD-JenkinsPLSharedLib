// SharedLibrary/src/enumType/EnumPipelineExecutionStatus.groovy
package enumType;

/*
Set Jenkins job execution status as enum type which accepts following values: (used to update status in DB)
Unknown, Started, Successful, Failed, Aborted
*/
enum EnumPipelineExecutionStatus {
    Unknown(0), Submitted(1), Started(2), Successful(3), Failed(4), Aborted(5)
    EnumPipelineExecutionStatus(int value) {
		this.value = value
	}
	private final int value
}