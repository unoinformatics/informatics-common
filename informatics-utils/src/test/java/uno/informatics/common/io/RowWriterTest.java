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
import java.util.List;

import org.junit.Test;

public abstract class RowWriterTest extends TableReaderTest
{

	@Test
	public void testWriteCells()
	{
		try
    {
			RowWriter writer = createWriter() ;
			
			List<List<Object>> expected = getExpectedList() ;
	    
	    int i = 0 ;
	    
	    if (i < expected.size())
	    {
		    writer.writeRowCells(expected.get(i)) ;
		    ++i ;
		    
		    while (i < expected.size() && writer.newRow())
		    {
			    writer.writeRowCells(expected.get(i)) ;
			    ++i ;
		    }
	    }
	    
	    writer.close();
	    
			RowReader reader = createReader() ;
			
			assertTrue(reader.ready()) ;
	    
	    i = 0 ;
	    
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
	public void testWriteCellsAsArray()
	{
		try
    {
			RowWriter writer = createWriter() ;
			
			Object[][] expected = getExpectedArray() ;
	    
	    int i = 0 ;
	    
	    if (i < expected.length)
	    {
		    writer.writeRowCellsAsArray(expected[i]) ;
		    ++i ;
		    
		    while (i < expected.length && writer.newRow())
		    {
			    writer.writeRowCellsAsArray(expected[i]) ;
			    ++i ;
		    }
	    }
	    
	    writer.close();
	    
			RowReader reader = createReader() ;
	    
	    assertTrue(reader.ready()) ;
	    
			i = 0 ;
	    
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
	
	protected abstract RowReader createReader() throws FileNotFoundException, IOException ;
	
	protected abstract RowWriter createWriter() throws FileNotFoundException, IOException ;
	
	protected abstract List<List<Object>> getExpectedList() ;
	
	protected abstract Object[][] getExpectedArray() ;
}
