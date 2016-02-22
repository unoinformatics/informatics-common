/*******************************************************************************
 * Copyright 2016 Guy Davenport
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package uno.informatics.common.io.text;


import static uno.informatics.common.Constants.UNKNOWN_COUNT;
import static uno.informatics.common.Constants.UNKNOWN_INDEX;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import uno.informatics.common.Constants;
import uno.informatics.common.io.TextFileHandler;

public abstract class AbstractTextFileHandler implements TextFileHandler
{
  private String fileReference;

  private File file;
  
  private boolean isInStrictMode;
  
  private String comment;
  
  private int rowSize = UNKNOWN_COUNT;
  
  // the actual position (including comments, empty lines etc) of the read/write
  private int rowPosition = -1 ;
  
  // the index of the current row being written or read, -1 if not on any row yet
  private int rowIndex = UNKNOWN_INDEX ;
  
  // the index of the current column being written or read, -1 if not on any cell yet
  private int columnIndex = UNKNOWN_INDEX ;
  
	private boolean rowSizeSetExternally = false ;
	
  private String delimiter;
	
  protected AbstractTextFileHandler()  
  {
  	
  }

  /**
   * Constructs an initialised reader using a string reference to a text file.
   * 
   * @param reference a text file name or URL
   * @throws FileNotFoundException if the file to read/write is not found
   * @throws IOException if an I/O error occurs
   */
  public AbstractTextFileHandler(String reference) throws IOException, FileNotFoundException
  {
    if (reference == null)
      throw new FileNotFoundException("File undefined");

    setFileReference(reference) ;
  }

  /**
   * Constructs an initialised reader using a file.
   * 
   * @param file a text File object.
   * @throws FileNotFoundException if the file to read/write is not found
   * @throws IOException if an I/O error occurs
   */
  public AbstractTextFileHandler(File file) throws IOException, FileNotFoundException
  {  	
    if (file == null)
      throw new FileNotFoundException("File undefined");

    setFile(file) ;
  }
  
    /**
     * @return <code>true</code>
     */
    public boolean ready() {
        return true;
    }

  public final int getRowCount()
  {
  	if (!ready())
  		return Constants.UNKNOWN_COUNT ;
  	else
  		return getRowIndex() ;
  }

	public final int getColumnCount()
  {
    if (getRowSize() > -1)
      return getRowSize() ;
    else
      return Constants.UNKNOWN_COUNT ;
  }
  
  /**
   * Gets the string which indicates at comment line that should be ignored by the reader.
   * 
   * @return the comment string
   */
  public final String getCommentString()
  {
    return comment;
  }

  public final String getFileReference()
  {
    return fileReference;
  }

  public final void setFileReference(String fileReference) throws IOException
  {
    if (this.fileReference != fileReference)
    {
      if (isInUse())
        throw new IOException("File can not be changed while reader/writer is in use") ;
      
      this.fileReference = fileReference;
      file = null;
    }
  }

  public final File getFile()
  {
    return file;
  }

  public final void setFile(File file) throws IOException
  {
    if (this.file != file)
    {
      if (isInUse())
        throw new IOException("File can not be changed while reader/writer is in use") ;
      
      fileReference = null;
      this.file = file ;
    }
  }
  
  public final boolean isInStrictMode()
  {
    return isInStrictMode;
  }

  public final void setInStrictMode(boolean isInStrictMode) throws IOException
  {
    if (this.isInStrictMode != isInStrictMode)
    {
      if (isInUse())
        throw new IOException("Mode can not be changed while reader/writer is in use") ;
      
      this.isInStrictMode = isInStrictMode;
    }
  }
  
  /**
   * Sets the string which indicates a comment line that should be ignored by the reader.
   * Set to <code>null</code> if no comments are allowed
   * 
   * @param comment the comment string
   * @throws IOException if the reader/writer is in use
   */
  public final synchronized void setCommentString(String comment) throws IOException
  {
    if (comment == null || comment.equals(""))
      comment = Constants.DEFAULT_COMMENT;
    
    if (!comment.equals(this.comment))
    {
      if (isInUse())
        throw new IOException("Comment string can not be set while reader/writer is in use") ;
      
      this.comment = comment;
    }
  }
  
  /**
   * Gets the string which indicates a new field in a record.
   * 
   * @return the delimiter string
   */
  public final String getDelimiterString()
  {
    return delimiter;
  }

  /**
   * Sets the string which indicates a new field in a record.
   * 
   * @param delimiter the delimiter string
   * @throws IOException if the reader is currently in use
   */
  public synchronized void setDelimiterString(String delimiter) throws IOException
  {
    if (delimiter == null || delimiter.equals(""))
      delimiter = Constants.DEFAULT_DELIMITER;
    
    if (!delimiter.equals(this.delimiter))
    {
      if (isInUse())
        throw new IOException("Delimiter string can not be set while reader/writer is in use") ;
      
      this.delimiter = delimiter;
    }
  }

  /**
   * Initialises the reader.
   * @throws FileNotFoundException if the file to read/write is not found
   * @throws IOException if an I/O error occurs
   */
  protected void initialise() throws FileNotFoundException, IOException 
  {
    isInStrictMode = false ;
    
    rowPosition = Constants.UNKNOWN_INDEX  ;
    rowIndex = Constants.UNKNOWN_INDEX ;
    columnIndex = Constants.UNKNOWN_INDEX ;
		
    comment = Constants.DEFAULT_COMMENT ;
    
    if (delimiter == null || delimiter.equals(""))
      delimiter = Constants.DEFAULT_DELIMITER;
  }

  /**
   * Add addition cells to row to ensure it is the same size as other rows
   * @param row row to complete
   * @throws IOException if the row can not be completed
   */
  protected final void updateRowFromSize(List<Object> row) throws IOException
  {
  	if (row != null)
  	{  
  		if (row.size() < getRowSize())
  			for (int i = 0 ; i < row.size() - getRowSize() ; ++i)
  				row.add(null) ;
  	}
  }

  public final int getRowSize()
  {
    return rowSize;
  }
  
  public final void setRowSize(int rowSize) throws IOException
  {
  	if (this.rowSize != rowSize)
  		if (isInUse())
  			throw new IOException("Row size can not be set while reader/writer is in use") ;
  		else
  			setRowSizeInternal(rowSize) ;
  	
  	rowSizeSetExternally  = rowSize >= 0 ;
  }
  
  protected final void setRowSizeInternal(int rowSize)
  {
    this.rowSize = rowSize ;
  }
  
  protected final void updateRowSize(int size) throws IOException
  {
  	if (rowSizeSetExternally && size > rowSize)
  		throw new IOException("Row Size must not be greater than : " + rowSize);     
  	
    if (getRowSize() < 0)
    {
    	setRowSizeInternal(size) ;
    }
    else
    {
      if (isRowSizesEqual())
      {
        if (getRowSize() != size)
        {
          String message = "Row Size Equal ON: All Rows must contain " ;
            
          if (getRowSize() == 1)
            message = message + " 1 element : at row " + getRowPosition() ;
          else
            message = message + getRowSize() + " elements : at row " + getRowPosition()  ;
          
          if (size == 1)
            message = message + " there is 1 element" ;
          else
            message = message + " there are " + size + " elements" ;
          
          throw new IOException(message);          
        }
      }
      else
      {
        if (getRowSize() < size)
        {
        	setRowSizeInternal(size) ;
        }
      }
    }
  }
  
  protected final boolean isRowSizesEqual()
  {
	  return false;
  }

	protected final int getRowIndex() 
  {
    return rowIndex ;
  }
  
  protected final void setRowIndex(int rowIndex)
  {
    this.rowIndex = rowIndex ;
  }
  
  protected final void incrementRowIndex()
  {
    ++rowIndex ;
  }
  
  protected final int getRowPosition() 
  {
    return rowPosition ;
  }
  
  protected final void setRowPosition(int rowPosition)
  {
    this.rowPosition = rowPosition ;
  }
  
  protected final void incrementRowPosition()
  {
    ++rowPosition ;
  }
  
  protected final boolean isInUse()
  {
    return rowIndex > -1 ;
  }
  
  protected final int getColumnIndex() 
  {
    return columnIndex ;
  }
  
  protected final void setColumnIndex(int columnIndex)
  {
    this.columnIndex = columnIndex ;
  }
  
  protected final void incrementColumnIndex()
  {
    ++columnIndex ;
  }
  
  protected final void resetCellIndex()
  {
    columnIndex = Constants.UNKNOWN_INDEX ;
  }

  protected final boolean isRowSizeSetExternally()
	{
		return rowSizeSetExternally;
	}
}
