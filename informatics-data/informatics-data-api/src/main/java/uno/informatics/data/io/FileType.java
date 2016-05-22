package uno.informatics.data.io;

public enum FileType
{
	XLS ("xsl"),
	XLSX ("xslx"),
	TXT ("txt"),
	CSV ("csv") ;
	
	private String name ;

	private FileType (String name)
	{
		this.name = name  ;
	}

	public String getName()
	{
		return name;
	}
}
