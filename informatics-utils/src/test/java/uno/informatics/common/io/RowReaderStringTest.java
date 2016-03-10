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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

/**
 * @author Guy Davenport
 *
 */
public abstract class RowReaderStringTest extends RowReaderTest
{
	@Test
	public void testReadCellsAsString()
	{
		try
    {
			RowReader reader = createReader() ;
			
			List<List<String>> expected = getExpectedAsStringList() ;
	    
			assertTrue(reader.ready()) ;
	    
	    int i = 0 ;
	    
	    while (i < expected.size() && reader.nextRow())
	    {
		    assertEquals("row " + i + " not equal", expected.get(i), reader.getRowCellsAsString()) ;
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
	public void testReadCellsAsArrayAsString()
	{
		try
    {
			RowReader reader = createReader() ;
	    
	    assertTrue(reader.ready()) ;
	    
			String[][] expected = getExpectedAsStringArray() ;
	    
	    int i = 0 ;
	    
	    while (i < expected.length && reader.nextRow())
	    {
		    assertArrayEquals("row " + i + " not equal", expected[i], reader.getRowCellsAsStringArray()) ;
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
	

  protected abstract List<List<Object>> getExpectedList() ;

  protected abstract Object[][] getExpectedArray() ;

  protected abstract List<List<String>> getExpectedAsStringList() ;

  protected abstract String[][] getExpectedAsStringArray() ;
	
}
