// SharedLibrary/src/enumType/EnumSourceComponentType.groovy
package enumType;

/*
Set Source Component Category as enum type which accepts following values:
Database Script;  Console Application (scheduled);  Windows Service;  Web Service;  Web Application 
*/
enum EnumSourceComponentType {
	Unknown(0),
	Database_Script(1),
    Database_MsProject(2),
	Class_Library(3),
	Windows_Component(4),
	Console_Application(5),
	Windows_Forms(6),
	Windows_Service(7),
	Web_Service(8),
	Web_Application(9),
	Web_Site(10),
	Web_Client(11),
	Web_Client_Node(12),
	Web_Client_Maven(13),
	PSUtility(14),
    JenkinsPipeline(15)
    EnumSourceComponentType(int value) {
		this.value = value
	}
	private final int value
}