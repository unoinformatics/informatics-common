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
package uno.informatics.common.io;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public abstract class RowReaderTest extends TableReaderTest
{
	@Test
	public void testReadCells()
	{
		try
    {
			RowReader reader = createReader() ;
			
			List<List<Object>> expected = getExpectedList() ;
	    
			assertTrue(reader.ready()) ;
	    
	    int i = 0 ;
	    
	    while (i < expected.size() && reader.nextRow())
	    {
		    assertEquals("row " + i + " not equal", expected.get(i), reader.getRowCells()) ;
		    ++i ;
	    }
	    
			assertFalse("Still rows to read!", reader.nextRow()) ;  
			
	    reader.close();
    }
    catch (Exception e)
    {
    	e.printStackTrace(System.err) ;
    	fail(e.getLocalizedMessage()) ;
    }
	}

	
	@Test
	public void testReadCellsAsArray()
	{
		try
    {
			RowReader reader = createReader() ;
	    
	    assertTrue(reader.ready()) ;
	    
			Object[][] expected = getExpectedArray() ;
	    
	    int i = 0 ;
	    
	    while (i < expected.length && reader.nextRow())
	    {
		    assertArrayEquals("row " + i + " not equal", expected[i], reader.getRowCellsAsArray()) ;
		    ++i ;
	    }
	    
			assertFalse(reader.nextRow()) ;  
			
	    reader.close();
    }
    catch (Exception e)
    {
    	e.printStackTrace(System.err) ;
    	fail(e.getLocalizedMessage()) ;
    }
	}
	

	@Test
	public void testReadCellsWithColumnHeader()
	{
		try
    {
			RowReader reader = createReader() ;
	    
			List<List<Object>> expected = getExpectedList() ;
			
			ArrayList<List<Object>> expected2 = new ArrayList<List<Object>>(expected) ;
			
			expected2.remove(0) ;
	    
	    assertTrue(reader.ready()) ;
	    
	    assertTrue("No next row", reader.hasNextRow()) ;
	    
		  assertTrue("Can not get next row", reader.nextRow()) ;
	    
	    List<Object> header = reader.getRowCells() ;
	    
	    assertEquals("header not equal", expected.get(0), header) ;
	    
	    assertTrue(reader.ready()) ;
	    
	    List<List<Object>> cells = reader.readCells() ;
	    
	    reader.close();
	    	
	    assertEquals("table not equal", expected2, cells) ;
    }
    catch (Exception e)
    {
    	e.printStackTrace(System.err) ;
    	fail(e.getLocalizedMessage()) ;
    }
	}

	@Test
	public void testReadCellsAsArrayWithColumnHeader()
	{
		try
    {
			RowReader reader = createReader() ;
	    
	    Object[][] expected = getExpectedArray() ;
	    
	    Object[][] expected2 = new Object[expected.length - 1][] ;
	    
	    for (int i = 1 ; i < expected.length ; ++i)
	    	expected2[i - 1] = expected[i] ;
	    
	    assertTrue(reader.ready()) ;
	    
	    assertTrue("No next row", reader.hasNextRow()) ;
	    
		  assertTrue("Can not get next row", reader.nextRow()) ;
	    
	    Object[] header = reader.getRowCellsAsArray() ;
	    
	    assertArrayEquals("header not equal", expected[0], header) ;

	    assertTrue(reader.ready()) ;
	    
	    Object[][] cells = reader.readCellsAsArray() ;
	    
	    reader.close();
	    
	    assertArrayEquals("table not equal", expected2, cells) ;
    }
    catch (Exception e)
    {
    	e.printStackTrace(System.err) ;
    	fail(e.getLocalizedMessage()) ;
    }
	}
	
	@Test
	public void testReadCellsWithRowHeaders()
	{
		try
    {
			RowReader reader = createReader() ;
			
			List<List<Object>> expected = getExpectedList() ;
			
			ArrayList<List<Object>> expected2 = new ArrayList<List<Object>>(expected.size()) ;
			
			Iterator<List<Object>> iterator = expected.iterator() ;
			
			List<Object> row ;
			
			while (iterator.hasNext())
			{
				row = new ArrayList<Object>(iterator.next()) ;
				
				row.remove(0) ;
				
				expected2.add(row) ;
			}
			  
			assertTrue(reader.ready()) ;
	    
	    int i = 0 ;
	    
	    while (i < expected.size() && reader.nextRow())
	    {
	    	reader.nextColumn() ;
	    	
		    assertEquals("header " + i + " not equal", expected.get(i).get(0), reader.getCell()) ;
		    
		    reader.nextColumn() ;
		    
		    assertEquals("row " + i + " not equal", expected2.get(i), reader.getRowCells()) ;
		    ++i ;
	    }
	    
			assertFalse("Still rows to read!", reader.nextRow()) ;  
			
	    reader.close();
    }
    catch (Exception e)
    {
    	e.printStackTrace(System.err) ;
    	fail(e.getLocalizedMessage()) ;
    }
	}
	
	@Test
	public void testReadCellsAsArrayWithRowHeaders()
	{
		try
    {
			RowReader reader = createReader() ;
			
			Object[][] expected = getExpectedArray() ;
			
			Object[][] expected2 = new Object[expected.length][] ;
			
			for (int k = 0 ; k < expected.length ; ++k)
			{
				expected2[k] = new Object[expected[k].length - 1];
				
				for (int l = 0 ; l < expected[k].length - 1 ; ++l)
				{
					expected2[k][l] = expected[k][l+1] ;
				}
			}
			  
			assertTrue(reader.ready()) ;
	    
	    int i = 0 ;
	    
	    while (i < expected.length && reader.nextRow())
	    {
	    	reader.nextColumn() ;
	    	
		    assertEquals("header " + i + " not equal", expected[i][0], reader.getCell()) ;
		    
		    reader.nextColumn() ;
		    
		    assertArrayEquals("row " + i + " not equal", expected2[i], reader.getRowCellsAsArray()) ;
		    ++i ;
	    }
	    
			assertFalse("Still rows to read!", reader.nextRow()) ;  
			
	    reader.close();
    }
    catch (Exception e)
    {
    	e.printStackTrace(System.err) ;
    	fail(e.getLocalizedMessage()) ;
    }
	}
	
	protected abstract RowReader createReader() throws FileNotFoundException, IOException ;
	
	protected abstract List<List<Object>> getExpectedList() ;
	
	protected abstract Object[][] getExpectedArray() ;
}
