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


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Pattern;

import uno.informatics.common.ConversionException;
import uno.informatics.common.ConversionUtilities;
import uno.informatics.common.io.RowReader;
import uno.informatics.data.DataTypeConstants;

public class TextFileRowReader extends AbstractTextFileHandler implements RowReader
{    
  private boolean parsingEmptyStrings ;
  
  private boolean convertingValues ;

  private Map<Integer, Integer> conversionTypesMap  ;

  private int conversionTypesCount;

  private int[] conversionTypesArray;

	private int defaultConversionTypes;
  
  private Pattern pattern ;
  
  private BufferedReader bufferedReader;
  
  private boolean ignoringMultipleDelimiters ;
  
  private String[] line;
	private String[] nextLine;
	
  private static final String BUFFERREADER_NULL = "Buffer reader is undefined";
  
	private TextFileRowReader()
  {
	  conversionTypesArray = new int[0] ;
  }

  /**
   * Constructs an initialised reader using a string reference to a text file.
   * 
   * @param reference a text file name or URL
   * @throws FileNotFoundException if the file to read is not found
   * @throws IOException if an I/O error occurs
   */
  public TextFileRowReader(String reference) throws IOException, FileNotFoundException
  {
  	this() ;
  	
    if (reference == null)
      throw new FileNotFoundException("File undefined");

    setFileReference(reference) ;
    
    initialise() ;
  }

  /**
   * Constructs an initialised reader using a file.
   * 
   * @param file a text File object.
   * @throws FileNotFoundException if the file to read is not found
   * @throws IOException if an I/O error occurs
   */
  public TextFileRowReader(File file) throws IOException, FileNotFoundException
  {
  	this() ;
  	
    if (file == null)
      throw new FileNotFoundException("File undefined");

    setFile(file) ;
    
    initialise() ;
  }
  
	public TextFileRowReader(BufferedReader bufferedReader) throws IOException
  {
  	this() ;
  	
    if (bufferedReader != null)
    	this.bufferedReader = bufferedReader ;
    else
      throw new IOException("Buffered reader undefined");
    
    initialise() ;
  }

    /**
     * Check to see if the reader is ready to be used and if additional cells can still be read
     *
     * @return <code>true</code> if the reader is ready to be used and if additional cells can still be read,
     *         <code>false</code> otherwise
     */
    public final boolean ready() {
        try {
            if (bufferedReader != null) {
                return bufferedReader.ready();
            } else {
                return false;
            }
        } catch (IOException e) {
            return false;
        }
    }

	/**
	 * Close the reader, disposing of any internal resources
	 */
  public final void close() 
  {
    try
    {
      if (bufferedReader != null)
        bufferedReader.close();
    }
    catch (IOException e)
    {

    }
    
    bufferedReader = null ;
  }

  /**
   * Determines if two more more delimiters are encountered together if these should be treated as one delimiters
   * 
   * @return <code>true</code> if two more more delimiters are encountered together if these should be treated as one delimiters, <code>false</code> otherwise
   */
  public final boolean isIgnoringMultipleDelimiters()
  {
    return ignoringMultipleDelimiters;
  }

  /**
   * Sets if two more more delimiters are encountered together if these should be treated as one delimiters
   * 
   * @param ignoringMultipleDelimiters <code>true</code> if two more more delimiters are encountered together if these should be treated as one delimiters, <code>false</code> otherwise
   * @exception IOException if the read is already is use
   */
  public final void setIgnoringMultipleDelimiters(
      boolean ignoringMultipleDelimiters) throws IOException
  {
    if (ignoringMultipleDelimiters != this.ignoringMultipleDelimiters)
    {
      if (isInUse())
        throw new IOException("Paramater can not be changed while reader is in use") ;
      
      this.ignoringMultipleDelimiters = ignoringMultipleDelimiters ;
    }
  }
  
  /**
   * Determines if the reader should attempt to convert values
   * 
   * @return <code>true</code> if the reader should attempt to convert values, <code>false</code> otherwise
   */
  public final boolean isConvertingValues()
  {
    return convertingValues;
  }

  /**
   * Sets if the reader should attempt to convert values.
   * 
   * @param convertingValues <code>true</code> if the read should attempt to convert values, <code>false</code> otherwise
   * @exception IOException if the read is already is use
   */
  public final void setConvertingValues(boolean convertingValues) throws IOException
  {
    if (convertingValues != this.convertingValues)
    {
      if (isInUse())
        throw new IOException("Paramater can not be changed while reader is in use") ;
      
      this.convertingValues = convertingValues ;
    }
  }

  /**
   * Determines if the reader should parse empty strings
   * 
   * @return <code>true</code> if the reader should parse empty strings, <code>false</code> otherwise
   */
  public final boolean isParsingEmptyStrings()
  {
    return parsingEmptyStrings;
  }

  /**
   * Sets if the reader should parse empty strings.
   * 
   * @param parsingEmptyStrings <code>true</code> if the read should should parse empty strings, <code>false</code> otherwise
   * @exception IOException if the read is already is use
   */
  public final void setParsingEmptyStrings(boolean parsingEmptyStrings) throws IOException
  {
    if (this.parsingEmptyStrings != parsingEmptyStrings)
    {
      if (isInUse())
        throw new IOException("Paramater can not be changed while reader is in use") ;
      
      this.parsingEmptyStrings = parsingEmptyStrings;
      
      updatePattern() ;
    }
  }
  
  /**
   * Sets the delimiter string.
   * 
   * @param delimiter the delimiter string
   * @exception IOException if the read is already is use
   */
  public final synchronized void setDelimiterString(String delimiter) throws IOException
  {
    super.setDelimiterString(delimiter) ;
    
    updatePattern();
  }
  
	@Override
	public final Object[][] readCellsAsArray() throws IOException
	{
  	List<Object[]> rows = new LinkedList<Object[]>() ;
  	
	  while (nextRow())
	  {
	  	rows.add(getRowCellsAsArray()) ;
	  }
	  
	  Object[][] cells = new Object[rows.size()][] ;
	  
	  Iterator<Object[]> iterator = rows.iterator() ;
	  
	  int i = 0 ;
	  
	  while (iterator.hasNext())
	  {
	  	cells[i] = iterator.next() ;
	  	++i ;
	  }
  	
  	return cells;
	}
  
	@Override
	public final List<List<Object>> readCells() throws IOException
  {
  	List<List<Object>> cells = new LinkedList<List<Object>>() ;
  	
	  while (nextRow())
	  {
	  	cells.add(getRowCells()) ;
	  }
  	
  	return cells ;
  }
  
	@Override
	public final boolean hasNextRow()
  {
		if (nextLine != null)
		{
			return true ;
		}
		else
		{
	    if (ready() && this.getRowPosition() < 0) 
	    {
	    	try
        {
	        readNextLine() ;
	        
	        return true ;
        }
        catch (IOException e)
        {
	        return false ;
        }
	    	
	    }
	    else
	    {
	    	return false ;
	    }
		}
  }

	@Override
	public final boolean nextRow() throws IOException
  {
    if (hasNextRow())
    {
    	readNextLine() ;
    	incrementRowIndex(); 
    	updateRowSize(line != null ? line.length : 0) ;
    	
    	return true ;
    }
    else
    {
    	return false ;
    }
  }
	
	/* (non-Javadoc)
	 * @see uno.informatics.common.io.TableReader#hasNextColumn()
	 */
  @Override
  public boolean hasNextColumn()
  {
	  return getColumnIndex() + 1 < getRowSize();
  }

	/* (non-Javadoc)
	 * @see uno.informatics.common.io.TableReader#nextColumn()
	 */
  @Override
  public boolean nextColumn() throws IOException
  {
  	if (hasNextColumn())
  	{
    	incrementColumnIndex();
    	
    	return true ;
  	}
  	else
  	{
  		return false;
  	}
  }

	/* (non-Javadoc)
	 * @see uno.informatics.common.io.TableReader#getCell()
	 */
  @Override
  public Object getCell() throws IOException
  {
	  return parseCell(getColumnIndex()) ;
  }
	
	@Override
  public String getCellAsString() throws IOException
  {
	  return parseCellAsString(getColumnIndex()) ;
  }

	@Override
  public double getCellAsDouble() throws IOException
  {
	  return parseCellAsDouble(getColumnIndex()) ;
  }

	@Override
  public int getCellAsInt() throws IOException
  {
	  return parseCellAsInt(getColumnIndex()) ;
  }

	@Override
  public boolean getCellAsBoolean() throws IOException
  {
	  return parseCellAsBool(getColumnIndex()) ;
  }

	@Override
	public final List<Object> getRowCells() throws IOException
  {
		if (this.getRowIndex() < 0)
			throw new IOException("Reader before first row!") ;
		
		return parseRowCells(getColumnIndex(), getRowSize()) ;
  }
	
	@Override
	public final List<String> getRowCellsAsString() throws IOException
  {
		if (this.getRowIndex() < 0)
			throw new IOException("Reader before first row!") ;
		
		return parseRowCellsAsString(getColumnIndex(), getRowSize()) ;
  }
	
	@Override
	public final List<Integer> getRowCellsAsInt() throws IOException
  {
		if (this.getRowIndex() < 0)
			throw new IOException("Reader before first row!") ;
		
		return parseRowCellsAsInteger(getColumnIndex(), getRowSize()) ;
  }
	
	@Override
	public final List<Double> getRowCellsAsDouble() throws IOException
  {
		if (this.getRowIndex() < 0)
			throw new IOException("Reader before first row!") ;
		
		return parseRowCellsAsDouble(getColumnIndex(), getRowSize()) ;
  }


	@Override
	public final List<Boolean> getRowCellsAsBoolean() throws IOException
  {
		if (this.getRowIndex() < 0)
			throw new IOException("Reader before first row!") ;
		
		return parseRowCellsAsBoolean(getColumnIndex(), getRowSize()) ;
  }
	
	@Override
	public final Object[] getRowCellsAsArray() throws IOException
  {
		return parseRowCellsAsArray(getColumnIndex(), getRowSize()) ;
  }

	@Override
	public final String[] getRowCellsAsStringArray() throws IOException
  {
		return parseRowCellsAsStringArray(getColumnIndex(), getRowSize()) ;
  }
	
	@Override
	public final int[] getRowCellsAsIntArray() throws IOException
  {
		return parseRowCellsAsIntArray(getColumnIndex(), getRowSize()) ;
  }
	
	@Override
	public final double[] getRowCellsAsDoubleArray() throws IOException
  {
		return parseRowCellsAsDoubleArray(getColumnIndex(), getRowSize()) ;
  }
	
	@Override
	public final boolean[] getRowCellsAsBooleanArray() throws IOException
  {
		return parseRowCellsAsBooleanArray(getColumnIndex(), getRowSize()) ;
  }
	
  protected Object parseValue(String text, int rowIndex, int columnIndex) throws IOException 
  {
	  try
    {
	    return convertValue(text);
    }
    catch (ClassCastException e)
    {
	    throw new IOException("Can not parse cell position " + rowIndex +"," + columnIndex + " due to " + e.getLocalizedMessage(), e) ;
    }
  }
  
  protected Object parseValue(String text, int rowIndex, int columnIndex, int conversionTypes) throws IOException 
  {
  	try
    {
	    return convertValue(text, conversionTypes);
    }
    catch (Exception e)
    {
	    throw new IOException("Can not parse cell position " + rowIndex +"," + columnIndex + " due to " + e.getLocalizedMessage(), e) ;
    }
  }
  
  protected Object convertValue(String text) 
  {
    return ConversionUtilities.convertToObject(text) ;
  }
  
  protected Object convertValue(String text, int dataTypes) throws ConversionException 
  {
    return ConversionUtilities.convertToObject(text, dataTypes) ;
  }

  /**
   * Initialises the reader.
   * @throws FileNotFoundException if the file to read is not found
   * @throws IOException if an I/O error occurs
   */
  protected final void initialise() throws FileNotFoundException, IOException 
  {
  	super.initialise();
  	
    defaultConversionTypes = DataTypeConstants.DEFAULT_TYPE_IDS ;

    conversionTypesMap = new TreeMap<Integer, Integer>() ;
    
    conversionTypesCount = 0 ;
    
    conversionTypesArray = null ;
    
    updatePattern() ;

    if (getFileReference() != null)
      initialiseBufferedReader(getBufferReader(getFileReference()));
    else
      if (getFile() != null)
        initialiseBufferedReader(getBufferReader(getFile()));
      else
        if (bufferedReader != null)
          initialiseBufferedReader(bufferedReader) ;
        else
          throw new IOException("Unable to initialise reader") ;
  }
  
  /**
   * Reads the next valid line if possible into memory
   * 
   * @throws IOException
   */
  private void readNextLine() throws IOException
  {
  	line = nextLine ;
  	nextLine = null ;
    setColumnIndex(-1) ;
    
    while (bufferedReader.ready() && nextLine == null)
    {
    	nextLine = readLine() ;
      
      incrementRowPosition() ;
    }
  }

	/**
	 * @return a tokenised version of the next line, or <code>null if the next 
	 * line is a comment or an empty line when not in strict mode
	 * 
	 * @throws IOException 
	 */
  private String[] readLine() throws IOException
  {
  	String line = bufferedReader.readLine() ;
  	
    //    ignore any commented record or empty lines if not in strict mode
    if (line != null && ((line.trim().length() == 0 && isInStrictMode())
        || (getCommentString() != null && line.trim().startsWith(getCommentString()))))
    {
    	return null ;
    }
    else
    {
    	return pattern.split(line) ;
    } 
  }

	/**
   * Initialises the reader using a bufferedReader directly.
   * 
   * @param bufferedReader
   *          a buffered reader
   */
  private final void initialiseBufferedReader(BufferedReader bufferedReader)
  {
    if (bufferedReader == null)
      throw new NullPointerException(BUFFERREADER_NULL);
    
    this.bufferedReader = bufferedReader;
  }
  
  private final void updatePattern()
  {
    // TODO need to check for special characters
    if (ignoringMultipleDelimiters)
      pattern = Pattern.compile(getDelimiterString() + "+", Pattern.DOTALL) ;
    else
      pattern = Pattern.compile(getDelimiterString(), Pattern.DOTALL) ;
  }

  protected String convertToken(String string)
  {
    if (isParsingEmptyStrings())
      return string ;
    else
      if (string != null)
        if ("".equals(string.trim()))
          return null ;
        else
          return string ;
      else
        return null ;
  }

  protected int getDefaultConversionTypes()
  {
    return defaultConversionTypes ;
  }

  public final void setDefaultConversionTypes(int defaultConversionTypes)
  {
    this.defaultConversionTypes = defaultConversionTypes ;
  }
  
  public final int getConversionTypes(int index)
  {
    if (index >= 0 && conversionTypesMap.containsKey(index))
      return conversionTypesMap.get(index);
    else
      return defaultConversionTypes ;
  }
  
  public final int[] getAllConversionTypes()
  {
    if (conversionTypesArray == null)
    {
      conversionTypesArray = new int[conversionTypesCount] ;
      
      Iterator<Entry<Integer, Integer>> iterator = conversionTypesMap.entrySet().iterator() ;
      
      Entry<Integer, Integer> entry = null ;
      
      while (iterator.hasNext())
      {
        entry = iterator.next() ;
        
        conversionTypesArray[entry.getKey()] = entry.getValue() ;
      }
    }
    
    return conversionTypesArray ;
  }

  public final void setAllConversionTypes(int[] conversionTypes)
  {
    conversionTypesArray = null ;
    conversionTypesCount = conversionTypes.length ;
    for (int i = 0 ; i < conversionTypes.length ; ++i)
      conversionTypesMap.put(i, conversionTypes[i]) ;
  }

  public final void setConversionTypes(int conversionTypes, int index)
  {
    if (index >= 0)
    {
      conversionTypesMap.put(index, conversionTypes) ;
      
      if (index >= conversionTypesCount)
      {
        conversionTypesCount = index + 1 ;
        conversionTypesArray = null ;
      }
      else
      {
        conversionTypesArray[index] = conversionTypes ;
      }
    }
  }

  private Object parseCell(int index) throws IOException
  {
		if (line != null)
		{

			if (conversionTypesCount > 0)
			{
				return parseValue(convertToken(line[index]), getRowIndex(), index, getConversionTypes(index)) ;
			}
			else
			{
				return parseValue(convertToken(line[index]), getRowIndex(), index) ;
			}
		}
		else
		{
			return null ;
		}
  }
  
  private String parseCellAsString(int index) throws IOException
  {
		if (line != null)
		{
			try
			{
				return convertToken(line[index]) ;
	    }
	    catch (Exception e)
			{
		    throw new IOException("Can not parse cell position " + getRowIndex() +"," + index + " due to " + e.getLocalizedMessage(), e) ;
			}
		} 
		else
		{
			throw new IOException("Can not convert to string") ;
		}
  }
  
  private int parseCellAsInt(int index) throws IOException
  {
		if (line != null)
		{
			try
			{
				return (int)ConversionUtilities.convertToInteger(convertToken(line[index])) ;
	    }
	    catch (Exception e)
			{
		    throw new IOException("Can not parse cell position " + getRowIndex() +"," + index + " due to " + e.getLocalizedMessage(), e) ;
			}
		} 
		else
		{
			throw new IOException("Can not convert to int") ;
		}
  }
  
  private Integer parseCellAsInteger(int index) throws IOException
  {
		if (line != null)
		{
			try
			{
				return ConversionUtilities.convertToInteger(convertToken(line[index])) ;
	    }
	    catch (Exception e)
			{
		    throw new IOException("Can not parse cell position " + getRowIndex() +"," + index + " due to " + e.getLocalizedMessage(), e) ;
			}
		} 
		else
		{
			throw new IOException("Can not convert to int") ;
		}
  }
  
  private double parseCellAsDouble(int index) throws IOException
  {
		if (line != null)
		{
			try
			{
				return (double)ConversionUtilities.convertToDouble(convertToken(line[index])) ;
	    }
	    catch (Exception e)
			{
		    throw new IOException("Can not parse cell position " + getRowIndex() +"," + index + " due to " + e.getLocalizedMessage(), e) ;
			}
		} 
		else
		{
			throw new IOException("Can not convert to int") ;
		}
  }
  
  private boolean parseCellAsBool(int index) throws IOException
  {
		if (line != null)
		{
			try
			{
				return (boolean)ConversionUtilities.convertToBoolean(convertToken(line[index])) ;
	    }
	    catch (Exception e)
			{
		    throw new IOException("Can not parse cell position " + getRowIndex() +"," + index + " due to " + e.getLocalizedMessage(), e) ;
			}
		} 
		else
		{
			throw new IOException("Can not convert to int") ;
		}
  }
  
  private Boolean parseCellAsBoolean(int index) throws IOException
  {
		if (line != null)
		{
			try
			{
				return (boolean)ConversionUtilities.convertToBoolean(convertToken(line[index])) ;
	    }
	    catch (Exception e)
			{
		    throw new IOException("Can not parse cell position " + getRowIndex() +"," + index + " due to " + e.getLocalizedMessage(), e) ;
			}
		} 
		else
		{
			throw new IOException("Can not convert to int") ;
		}
  }
  
  private List<Object> parseRowCells(int firstIndex, int requestedSize) throws IOException
  {
		ArrayList<Object> row;
		
		if (line != null)
		{
		  int size = requestedSize < line.length ? requestedSize : line.length ; 
			int startIndex = firstIndex < 0 ? 0 : firstIndex >= size ? size : firstIndex ;
			
			row = new ArrayList<Object>(size - startIndex) ;
			
			if (conversionTypesCount > 0)
			{
				for (int i = startIndex ; i < size ; ++i)
					row.add(parseValue(convertToken(line[i]), getRowIndex(), i, getConversionTypes(i))) ;
			}
			else
			{
				for (int i = startIndex ; i < size ; ++i)
					row.add(parseValue(convertToken(line[i]), getRowIndex(), i)) ;
			}
		}
		else
		{
			row = new ArrayList<Object>() ;
		}
		
		return row ;
  }
  
  private List<String> parseRowCellsAsString(int firstIndex, int requestedSize) throws IOException
  {
		ArrayList<String> row;
		
		if (line != null)
		{
      int size = requestedSize < line.length ? requestedSize : line.length ; 
			int startIndex = firstIndex < 0 ? 0 : firstIndex >= size ? size : firstIndex ;
			
			row = new ArrayList<String>(size - startIndex) ;
			
			for (int i = startIndex ; i < size ; ++i)
				row.add(convertToken(line[i])) ;
		}
		else
		{
			row = new ArrayList<String>() ;
		}
		
		return row ;
  }
  
  private List<Integer> parseRowCellsAsInteger(int firstIndex, int requestedSize) throws IOException
  {
		ArrayList<Integer> row;
		
		if (line != null)
		{
      int size = requestedSize < line.length ? requestedSize : line.length ; 
			int startIndex = firstIndex < 0 ? 0 : firstIndex >= size ? size : firstIndex ;
			
			row = new ArrayList<Integer>(size - startIndex) ;
			
			for (int i = startIndex ; i < size ; ++i)
				row.add(parseCellAsInteger(i)) ;
		}
		else
		{
			row = new ArrayList<Integer>() ;
		}
		
		return row ;
  }
  
  private List<Double> parseRowCellsAsDouble(int firstIndex, int requestedSize) throws IOException
  {
		ArrayList<Double> row;
		
		if (line != null)
		{
      int size = requestedSize < line.length ? requestedSize : line.length ; 
			int startIndex = firstIndex < 0 ? 0 : firstIndex >= size ? size : firstIndex ;
			
			row = new ArrayList<Double>(size - startIndex) ;
			
			for (int i = startIndex ; i < size ; ++i)
				row.add(parseCellAsDouble(i)) ;
		}
		else
		{
			row = new ArrayList<Double>() ;
		}
		
		return row ;
  }
  
  private List<Boolean> parseRowCellsAsBoolean(int firstIndex, int requestedSize) throws IOException
  {
		ArrayList<Boolean> row;
		
		if (line != null)
		{
      int size = requestedSize < line.length ? requestedSize : line.length ; 
			int startIndex = firstIndex < 0 ? 0 : firstIndex >= size ? size : firstIndex ;
			
			row = new ArrayList<Boolean>(size - startIndex) ;
			
			for (int i = startIndex ; i < size ; ++i)
				row.add(parseCellAsBoolean(i)) ;
		}
		else
		{
			row = new ArrayList<Boolean>() ;
		}
		
		return row ;
  }
  
  
	private Object[] parseRowCellsAsArray(int firstIndex, int requestedSize) throws IOException
  {
		Object[] row;
		
		if (line != null)
		{
      int size = requestedSize < line.length ? requestedSize : line.length ; 
			int startIndex = firstIndex < 0 ? 0 : firstIndex >= size ? size : firstIndex ;
			
			row = new Object[size - startIndex] ;
			
			if (conversionTypesCount > 0)
			{
				for (int i = startIndex ; i < size ; ++i)
					row[i - startIndex] = parseValue(convertToken(line[i]), getRowIndex(), i, getConversionTypes(i)) ;
			}
			else
			{
				for (int i = startIndex ; i < size ; ++i)
					row[i - startIndex] = parseValue(convertToken(line[i]), getRowIndex(), i) ;
			}
		}
		else
		{
			row = new Object[0] ;
		}
		
		return row ;
  }
	
	private String[] parseRowCellsAsStringArray(int firstIndex, int requestedSize) throws IOException
  {
		String[] row;
		
		if (line != null)
		{
      int size = requestedSize < line.length ? requestedSize : line.length ; 
			int startIndex = firstIndex < 0 ? 0 : firstIndex >= size ? size : firstIndex ;
			
			row = new String[size - startIndex] ;
			
			for (int i = startIndex ; i < size ; ++i)
				row[i - startIndex] = convertToken(line[i]) ;
		}
		else
		{
			row = new String[0] ;
		}
		
		return row ;
  }
	
	private int[] parseRowCellsAsIntArray(int firstIndex, int requestedSize) throws IOException
  {
		int[] row;
		
		if (line != null)
		{
      int size = requestedSize < line.length ? requestedSize : line.length ; 
			int startIndex = firstIndex < 0 ? 0 : firstIndex >= size ? size : firstIndex ;
			
			row = new int[size - startIndex] ;
			
			for (int i = startIndex ; i < size ; ++i)
				row[i - startIndex] = parseCellAsInt(i) ;
		}
		else
		{
			row = new int[0] ;
		}
		
		return row ;
  }
	
	private double[] parseRowCellsAsDoubleArray(int firstIndex, int requestedSize) throws IOException
  {
		double[] row;
		
		if (line != null)
		{
      int size = requestedSize < line.length ? requestedSize : line.length ; 
			int startIndex = firstIndex < 0 ? 0 : firstIndex >= size ? size : firstIndex ;
			
			row = new double[size - startIndex] ;
			
			for (int i = startIndex ; i < size ; ++i)
				row[i - startIndex] = parseCellAsDouble(i) ;
		}
		else
		{
			row = new double[0] ;
		}
		
		return row ;
  }
	
	private boolean[] parseRowCellsAsBooleanArray(int firstIndex, int requestedSize) throws IOException
  {
		boolean[] row;
		
		if (line != null)
		{
      int size = requestedSize < line.length ? requestedSize : line.length ; 
			int startIndex = firstIndex < 0 ? 0 : firstIndex >= size ? size : firstIndex ;
			
			row = new boolean[size - startIndex] ;
			
			for (int i = startIndex ; i < size ; ++i)
				row[i - startIndex] = parseCellAsBoolean(i) ;
		}
		else
		{
			row = new boolean[0] ;
		}
		
		return row ;
  }

  /**
   * Creates a BufferedReader using the string reference to a text file.
   * 
   * @param textFileReference
   *          a text file name or URL
   * @return a bufferedReader
   * 
   * @exception FileNotFoundException
   *              if the file referenced can not be found
   * @exception IOException
   *              if the reader can not open an input stream to the file
   */
  private static final BufferedReader getBufferReader(File file)
      throws FileNotFoundException, IOException
  {
    if (file != null)
      return new BufferedReader(new FileReader(file));
    else
      throw new FileNotFoundException("File object is null");
  }

  /**
   * Creates a BufferedReader using the string reference to a text file.
   * 
   * @param textFileReference
   *          a text file name or URL
   * @return a bufferedReader
   * 
   * @exception FileNotFoundException
   *              if the file referenced can not be found
   * @exception IOException
   *              if the reader can not open an input stream to the file
   */
  private static final BufferedReader getBufferReader(String fileReference)
      throws FileNotFoundException, IOException
  {
    BufferedReader bufferedReader = null;

    try
    {
      URL refURL = new java.net.URL(fileReference);
      bufferedReader = new BufferedReader(new InputStreamReader(refURL
          .openStream()));
    }
    catch (MalformedURLException malformedURLException)
    {
      bufferedReader = new BufferedReader(new FileReader(fileReference));
    }

    return bufferedReader;
  }
}
