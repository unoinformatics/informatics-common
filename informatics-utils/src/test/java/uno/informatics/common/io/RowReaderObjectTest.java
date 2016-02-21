/*******************************************************************************
 * Copyright 2015 Guy Davenport
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
package uno.informatics.common.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

/**
 * @author Guy Davenport
 *
 */
public abstract class RowReaderObjectTest extends RowReaderTest
{
	@Test
	public void testGetCellAsType()
	{
		try
    {
	    TableReader reader = createReader() ;
	    
	    Object[][] expected = getExpectedArray() ;
	    
	    assertTrue(reader.ready()) ;
	    
	    int rowIndex = 0 ;
	    int columnIndex = 0 ;
	    
	    while (reader.hasNextRow())
	    {
		    assertTrue("Can not get next row", reader.nextRow()) ;
		    
	    	columnIndex = 0 ;
	    	
	    	testNextInt(reader, rowIndex, columnIndex, (Integer)expected[rowIndex][columnIndex]) ;
	    	++columnIndex ;
	    	
	    	testNextDouble(reader, rowIndex, columnIndex, (Double)expected[rowIndex][columnIndex]) ;
	    	++columnIndex ;
	    	
	    	testNextString(reader, rowIndex, columnIndex, (String)expected[rowIndex][columnIndex]) ;
	    	++columnIndex ;
	    	
	    	testNextBoolean(reader, rowIndex, columnIndex, (Boolean)expected[rowIndex][columnIndex]) ;
	    	++columnIndex ;
	    	
	    	testNextObject(reader, rowIndex, columnIndex, expected[rowIndex][columnIndex]) ;
	    	++columnIndex ;
	    	
		    assertEquals("Column count not correct!", expected[rowIndex].length, columnIndex) ;
		    
		    ++rowIndex ;
	    }
	    
	    assertEquals("Row count not correct!", expected.length, rowIndex) ;

	    
	    reader.close();
	    
    }
    catch (Exception e)
    {
    	e.printStackTrace(System.err) ;
    	fail(e.getLocalizedMessage()) ;
    }
	}
	
	/* (non-Javadoc)
	 * @see uno.informatics.common.io.RowReaderTest#getExpectedList()
	 */
  @Override
  protected final List<List<Object>> getExpectedList()
  {
	  return OBJECT_TABLE_AS_LIST;
  }

	/* (non-Javadoc)
	 * @see uno.informatics.common.io.RowReaderTest#getExpectedArray()
	 */
  @Override
  protected final Object[][] getExpectedArray()
  {
	  return OBJECT_TABLE_AS_ARRAY;
  }

	/**
	 * @param reader 
	 * @param rowIndex 
	 * @param columnIndex 
	 * @throws IOException 
	 * 
	 */
  private void testNextInt(TableReader reader, int rowIndex, int columnIndex, int expected) throws IOException
  {
    if (reader.hasNextColumn())
    {
	    assertTrue("Can not get next column at row index" + rowIndex + " column index " + columnIndex, reader.nextColumn()) ;
	    
    	assertEquals("cell not equal at row index" + rowIndex + " column index " + columnIndex, expected, reader.getCell()) ;

    }
  }
  
	/**
	 * @param reader 
	 * @param rowIndex 
	 * @param columnIndex 
	 * @throws IOException 
	 * 
	 */
  private void testNextDouble(TableReader reader, int rowIndex, int columnIndex, double expected) throws IOException
  {
    if (reader.hasNextColumn())
    {
	    assertTrue("Can not get next column at row index" + rowIndex + " column index " + columnIndex, reader.nextColumn()) ;
	    
    	assertEquals("cell not equal at row index" + rowIndex + " column index " + columnIndex, expected, reader.getCell()) ;

    }
  }
  
	/**
	 * @param reader 
	 * @param rowIndex 
	 * @param columnIndex 
	 * @throws IOException 
	 * 
	 */
  private void testNextString(TableReader reader, int rowIndex, int columnIndex, String expected) throws IOException
  {
    if (reader.hasNextColumn())
    {
	    assertTrue("Can not get next column at row index" + rowIndex + " column index " + columnIndex, reader.nextColumn()) ;
	    
    	assertEquals("cell not equal at row index" + rowIndex + " column index " + columnIndex, expected, reader.getCell()) ;

    }
  }
  
	/**
	 * @param reader 
	 * @param rowIndex 
	 * @param columnIndex 
	 * @throws IOException 
	 * 
	 */
  private void testNextBoolean(TableReader reader, int rowIndex, int columnIndex, boolean expected) throws IOException
  {
    if (reader.hasNextColumn())
    {
	    assertTrue("Can not get next column at row index" + rowIndex + " column index " + columnIndex, reader.nextColumn()) ;
	    
    	assertEquals("cell not equal at row index" + rowIndex + " column index " + columnIndex, expected, reader.getCell()) ;

    }
  }
  
	/**
	 * @param reader 
	 * @param rowIndex 
	 * @param columnIndex 
	 * @throws IOException 
	 * 
	 */
  private void testNextObject(TableReader reader, int rowIndex, int columnIndex, Object expected) throws IOException
  {
    if (reader.hasNextColumn())
    {
	    assertTrue("Can not get next column at row index" + rowIndex + " column index " + columnIndex, reader.nextColumn()) ;
	    
    	assertEquals("cell not equal at row index" + rowIndex + " column index " + columnIndex, expected, reader.getCell()) ;

    }
  }
}
