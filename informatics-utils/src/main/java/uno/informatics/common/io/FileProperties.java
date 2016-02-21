package uno.informatics.common.io;

import static uno.informatics.common.Constants.UNKNOWN_INDEX;

import java.beans.PropertyChangeSupport;
import java.io.File;

public class FileProperties
{
	public static final String FILE_PROPERTY = FileProperties.class.getName() + ".file" ;
	public static final String FILE_TYPE_PROPERTY = FileProperties.class.getName() + ".fileType" ;
	public static final String ROW_HEADER_POSITION_PROPERTY = FileProperties.class.getName() + ".rowHeaderPosition" ;
	public static final String COLUMN_HEADER_POSITION_PROPERTY = FileProperties.class.getName() + ".columnHeaderPosition" ;
	public static final String DATA_ROW_POSITION_PROPERTY = FileProperties.class.getName() + ".dataRowPosition" ;
	public static final String DATA_COLUMN_POSITION_PROPERTY = FileProperties.class.getName() + ".dataColumnPosition" ;

	private File file ;
	private PropertyChangeSupport propertyChangeSupport ;
	
	private static final int DEFAULT_ROW_HEADER_POSITION = 0 ;
	private static final int DEFAULT_DATA_ROW = 1;
	private static final int DEFAULT_COLUMN_HEADER_POSITION = 0 ;
	private static final int DEFAULT_DATA_COLUMN = 1;

	private FileType fileType = FileType.TXT;
	private int rowHeaderPosition = DEFAULT_ROW_HEADER_POSITION ;
	private int columnHeaderPosition = DEFAULT_COLUMN_HEADER_POSITION ;
	private int dataRowPosition = DEFAULT_DATA_ROW ;
	private int dataColumnPosition = DEFAULT_DATA_COLUMN ;
	
	public FileProperties(String file)
  {
		this(new File(file), FileType.TXT) ;
  }
	
	public FileProperties(String file, FileType fileType)
  {
		this(new File(file), fileType) ;
  }
	
	public FileProperties(File file)
  {
		this(file, FileType.TXT) ;
  }
	
	public FileProperties(File file, FileType fileType)
  {
	  this(file, 
	  		fileType,   		
	  		UNKNOWN_INDEX, 
	  		0, 
	  		UNKNOWN_INDEX,
	  		0);
  }
	
	public FileProperties(String file, boolean columnHeaders)
  {
	  this(file, 
	  		FileType.TXT,   		
	  		columnHeaders ? DEFAULT_COLUMN_HEADER_POSITION : UNKNOWN_INDEX, 
	  		columnHeaders ? DEFAULT_DATA_ROW : UNKNOWN_INDEX, 
	  	  UNKNOWN_INDEX,
	  	  0) ;
  }
	
	public FileProperties(String file, FileType fileType, boolean columnHeaders)
  {
	  this(file, 
	  		fileType,   		
	  		columnHeaders ? DEFAULT_COLUMN_HEADER_POSITION : UNKNOWN_INDEX, 
	  		columnHeaders ? DEFAULT_DATA_ROW : UNKNOWN_INDEX, 
	  	  UNKNOWN_INDEX,
	  	  0) ;
  }
	
	public FileProperties(File file, boolean columnHeaders)
  {
	  this(file, 
	  		FileType.TXT,   		
	  		columnHeaders ? DEFAULT_COLUMN_HEADER_POSITION : UNKNOWN_INDEX, 
	  		columnHeaders ? DEFAULT_DATA_ROW : UNKNOWN_INDEX, 
	  	  UNKNOWN_INDEX,
	  	  0) ;
  }
	
	public FileProperties(File file, FileType fileType, boolean columnHeaders)
  {
	  this(file, 
	  		fileType,   		
	  		columnHeaders ? DEFAULT_COLUMN_HEADER_POSITION : UNKNOWN_INDEX, 
	  		columnHeaders ? DEFAULT_DATA_ROW : UNKNOWN_INDEX, 
	  	  UNKNOWN_INDEX,
	  	  0) ;
  }
	
	public FileProperties(String file, boolean columnHeaders, boolean rowHeaders)
  {
	  this(new File(file), 
	  		FileType.TXT,   		
	  		columnHeaders ? DEFAULT_COLUMN_HEADER_POSITION : UNKNOWN_INDEX, 
	  		columnHeaders ? DEFAULT_DATA_ROW : UNKNOWN_INDEX, 
	  		rowHeaders ? DEFAULT_ROW_HEADER_POSITION : UNKNOWN_INDEX, 
	  	  rowHeaders ? DEFAULT_DATA_COLUMN : UNKNOWN_INDEX);
  }
	
	public FileProperties(String file, FileType fileType, boolean columnHeaders, boolean rowHeaders)
  {
	  this(new File(file), 
	  		fileType,   		
	  		columnHeaders ? DEFAULT_COLUMN_HEADER_POSITION : UNKNOWN_INDEX, 
	  		columnHeaders ? DEFAULT_DATA_ROW : UNKNOWN_INDEX, 
	  		rowHeaders ? DEFAULT_ROW_HEADER_POSITION : UNKNOWN_INDEX, 
	  	  rowHeaders ? DEFAULT_DATA_COLUMN : UNKNOWN_INDEX);
  }
	
	public FileProperties(File file, boolean columnHeaders, boolean rowHeaders)
  {
	  this(file, 
	  		FileType.TXT,   		
	  		columnHeaders ? DEFAULT_COLUMN_HEADER_POSITION : UNKNOWN_INDEX, 
	  		columnHeaders ? DEFAULT_DATA_ROW : UNKNOWN_INDEX, 
	  		rowHeaders ? DEFAULT_ROW_HEADER_POSITION : UNKNOWN_INDEX, 
	  	  rowHeaders ? DEFAULT_DATA_COLUMN : UNKNOWN_INDEX);
  }
	
	public FileProperties(File file, FileType fileType, boolean columnHeaders, boolean rowHeaders)
  {
	  this(file, 
	  		fileType,   		
	  		columnHeaders ? DEFAULT_COLUMN_HEADER_POSITION : UNKNOWN_INDEX, 
	  		columnHeaders ? DEFAULT_DATA_ROW : UNKNOWN_INDEX, 
	  		rowHeaders ? DEFAULT_ROW_HEADER_POSITION : UNKNOWN_INDEX, 
	  	  rowHeaders ? DEFAULT_DATA_COLUMN : UNKNOWN_INDEX);
  }
	
	public FileProperties(String file, int columnHeaderPosition, int dataRowPosition)
  {
		this(new File(file), FileType.TXT, columnHeaderPosition, dataRowPosition, UNKNOWN_INDEX, 0) ;
  }
	
	public FileProperties(String file, FileType fileType, int columnHeaderPosition, int dataRowPosition)
  {
		this(new File(file), fileType, columnHeaderPosition, dataRowPosition, UNKNOWN_INDEX, 0) ;
  }
	
	public FileProperties(File file, int columnHeaderPosition, int dataRowPosition)
  {
		this(file, FileType.TXT, columnHeaderPosition, dataRowPosition, UNKNOWN_INDEX, 0) ;
  }
	
	public FileProperties(File file, FileType fileType, int columnHeaderPosition, int dataRowPosition)
  {
		this(file, fileType, columnHeaderPosition, dataRowPosition, UNKNOWN_INDEX, 0) ;
  }
	
	public FileProperties(String file, int columnHeaderPosition, int dataRowPosition, int rowHeaderPosition, int dataColumnPosition)
  {
		this(new File(file), FileType.TXT, columnHeaderPosition, dataRowPosition, rowHeaderPosition, dataColumnPosition) ;
  }
	
	public FileProperties(String file, FileType fileType, int columnHeaderPosition, int dataRowPosition, int rowHeaderPosition, int dataColumnPosition)
  {
		this(new File(file), fileType, columnHeaderPosition, dataRowPosition, rowHeaderPosition, dataColumnPosition) ;
  }
	
	public FileProperties(File file, int columnHeaderPosition, int dataRowPosition, int rowHeaderPosition, int dataColumnPosition)
  {
		this(file, FileType.TXT, columnHeaderPosition, dataRowPosition, rowHeaderPosition, dataColumnPosition) ;
  }
	
	public FileProperties(File file, FileType fileType, int columnHeaderPosition, int dataRowPosition, int rowHeaderPosition, int dataColumnPosition)
  {
	  propertyChangeSupport = new PropertyChangeSupport(this) ;
	  this.file = file;
	  this.fileType = fileType ;

		setColumnHeaderPosition(columnHeaderPosition) ;
		setDataRowPosition(dataRowPosition) ;
		setRowHeaderPosition(rowHeaderPosition) ;
		setDataColumnPosition(dataColumnPosition) ;
  }

	public final File getFile()
  {
  	return file;
  }

	public final void setFile(File file)
  {
		if (this.file != file)
		{
			File oldValue = this.file ;
			this.file = file;
	  	
			propertyChangeSupport.firePropertyChange(FILE_PROPERTY, oldValue, this.file = file) ;
		}
  }
	
	public final FileType getFileType()
  {
  	return fileType ;
  }

	public final void setFileType(FileType fileType)
  {
		if (this.fileType != fileType)
		{
			validateFileType(fileType) ;
			FileType oldValue = this.fileType ;
			this.fileType = fileType;
			
			propertyChangeSupport.firePropertyChange(FILE_TYPE_PROPERTY, oldValue, this.fileType) ;
		}
  }
	
	public final boolean hasRowHeader()
	{
		return rowHeaderPosition >= 0 ;
	}
	
	public final int getRowHeaderPosition()
	{
		return rowHeaderPosition;
	}

	public final void setRowHeaderPosition(int rowHeaderPosition)
	{
		if (this.rowHeaderPosition != rowHeaderPosition)
		{
			int oldValue = this.rowHeaderPosition ;
			this.rowHeaderPosition = rowHeaderPosition;
			
			propertyChangeSupport.firePropertyChange(ROW_HEADER_POSITION_PROPERTY, oldValue, this.rowHeaderPosition) ;
		}
	}
	
	public final boolean hasColumnHeader()
	{
		return columnHeaderPosition >= 0 ;
	}
	
	public final int getColumnHeaderPosition()
	{
		return columnHeaderPosition;
	}

	public final void setColumnHeaderPosition(int columnHeaderPosition)
	{
		if (this.columnHeaderPosition != columnHeaderPosition)
		{
			int oldValue = this.columnHeaderPosition ;
			this.columnHeaderPosition = columnHeaderPosition;
			
			propertyChangeSupport.firePropertyChange(COLUMN_HEADER_POSITION_PROPERTY, oldValue, this.columnHeaderPosition) ;
		}
	}

	public final int getDataRowPosition()
	{
		return dataRowPosition;
	}

	public final void setDataRowPosition(int dataPosition)
	{
		if (this.dataRowPosition != dataPosition)
		{
			int oldValue = this.dataRowPosition ;
			this.dataRowPosition = dataPosition;
			
			propertyChangeSupport.firePropertyChange(DATA_ROW_POSITION_PROPERTY, oldValue, this.dataRowPosition) ;
		}
	}
	
	public final int getDataColumnPosition()
	{
		return dataColumnPosition;
	}
	
	public final void setDataColumnPosition(int dataColumnPosition)
	{
		if (this.dataColumnPosition != dataColumnPosition)
		{
			int oldValue = this.dataColumnPosition ;
			this.dataColumnPosition = dataColumnPosition;
			
			propertyChangeSupport.firePropertyChange(DATA_COLUMN_POSITION_PROPERTY, oldValue, this.dataColumnPosition) ;
		}
	}

	@Override
  public int hashCode()
  {
	  final int prime = 31;
	  int result = 1;
	  result = prime * result + ((file == null) ? 0 : file.hashCode());
	  result = prime * result + ((fileType == null) ? 0 : fileType.hashCode());
	  return result;
  }

	@Override
  public boolean equals(Object obj)
  {
	  if (this == obj)
		  return true;
	  if (obj == null)
		  return false;
	  if (getClass() != obj.getClass())
		  return false;
	  FileProperties other = (FileProperties) obj;
	  if (file == null)
	  {
		  if (other.file != null)
			  return false;
	  }
	  else
		  if (!file.equals(other.file))
			  return false;
	  if (fileType != other.fileType)
		  return false;
	  return true;
  }

	protected void validateFileType(FileType fileType) throws IllegalArgumentException
  {

  }

	protected final PropertyChangeSupport getPropertyChangeSupport()
  {
  	return propertyChangeSupport;
  }
	
}
