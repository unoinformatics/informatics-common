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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author Guy Davenport
 *
 */
public abstract class RowReaderBooleanTest extends RowReaderTest
{
	protected final static boolean[] ROW1 = new boolean[] {true,false,true} ;
	protected final static boolean[] ROW2 = new boolean[] {false,true,false} ;
	protected final static boolean[] ROW3 = new boolean[] {true,false,false} ;
	
	protected final static Object[] OBJ_ROW1 = new Object[] {true,false,true} ;
	protected final static Object[] OBJ_ROW2 = new Object[] {false,true,false} ;
	protected final static Object[] OBJ_ROW3 = new Object[] {true,false,false} ;
	
	private static final List<List<Object>> TABLE_AS_LIST = new ArrayList<List<Object>>();
	
	static
	{
		TABLE_AS_LIST.add(new ArrayList<Object>(ROW1.length)) ;
		
		TABLE_AS_LIST.get(0).add(ROW1[0]) ;
		TABLE_AS_LIST.get(0).add(ROW1[1]) ;
		TABLE_AS_LIST.get(0).add(ROW1[2]) ;
		
		TABLE_AS_LIST.add(new ArrayList<Object>(ROW2.length)) ;
		
		TABLE_AS_LIST.get(1).add(ROW2[0]) ;
		TABLE_AS_LIST.get(1).add(ROW2[1]) ;
		TABLE_AS_LIST.get(1).add(ROW2[2]) ;
		
		TABLE_AS_LIST.add(new ArrayList<Object>(ROW3.length)) ;
		
		TABLE_AS_LIST.get(2).add(ROW3[0]) ;
		TABLE_AS_LIST.get(2).add(ROW3[1]) ;
		TABLE_AS_LIST.get(2).add(ROW3[2]) ;
	}
	
	private static final Object[][] TABLE_AS_ARRAY = new Object[][]
			{OBJ_ROW1, OBJ_ROW2, OBJ_ROW3};
	
	private static final List<List<Boolean>> TABLE_AS_LIST2 = new ArrayList<List<Boolean>>();
	
	static
	{
		TABLE_AS_LIST2.add(new ArrayList<Boolean>(ROW1.length)) ;
		
		TABLE_AS_LIST2.get(0).add(ROW1[0]) ;
		TABLE_AS_LIST2.get(0).add(ROW1[1]) ;
		TABLE_AS_LIST2.get(0).add(ROW1[2]) ;
		
		TABLE_AS_LIST2.add(new ArrayList<Boolean>(ROW2.length)) ;
		
		TABLE_AS_LIST2.get(1).add(ROW2[0]) ;
		TABLE_AS_LIST2.get(1).add(ROW2[1]) ;
		TABLE_AS_LIST2.get(1).add(ROW2[2]) ;
		
		TABLE_AS_LIST2.add(new ArrayList<Boolean>(ROW3.length)) ;
		
		TABLE_AS_LIST2.get(2).add(ROW3[0]) ;
		TABLE_AS_LIST2.get(2).add(ROW3[1]) ;
		TABLE_AS_LIST2.get(2).add(ROW3[2]) ;
	}
	private static final boolean[][] TABLE_AS_ARRAY2 = new boolean[][]
			{ROW1, ROW2, ROW3};
	
  protected final List<List<Object>> getExpectedList()
  {
	  return TABLE_AS_LIST;
  }

  protected final Object[][] getExpectedArray()
  {
	  return TABLE_AS_ARRAY;
  }

  protected final List<List<Boolean>> getExpectedAsList()
  {
	  return TABLE_AS_LIST2;
  }

  protected final boolean[][] getExpectedAsArray()
  {
	  return TABLE_AS_ARRAY2;
  }
	
	@Test
	public void testReadCellsAsBoolean()
	{
		try
    {
			RowReader reader = createReader() ;
			
			List<List<Boolean>> expected = getExpectedAsList() ;
	    
			assertTrue(reader.ready()) ;
	    
	    int i = 0 ;
	    
	    while (i < expected.size() && reader.nextRow())
	    {
		    assertEquals("row " + i + " not equal", expected.get(i), reader.getRowCellsAsBoolean()) ;
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
	    
			boolean[][] expected = getExpectedAsArray() ;
	    
	    int i = 0 ;
	    
	    while (i < expected.length && reader.nextRow())
	    {
		    assertArrayEquals("row " + i + " not equal", expected[i], reader.getRowCellsAsBooleanArray()) ;
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
	
	
	/**
	 * @param message
	 * @param bs
	 * @param rowCellsAsBooleanArray
	 */
  private void assertArrayEquals(String message, boolean[] expected,
      boolean[] actual)
  {
	  assertEquals("Arrays not the same length", expected.length, actual.length) ;
	  
	  for (int i = 0 ; i < expected.length ; ++i)
	  	assertEquals("Element not the same as element " + i, expected[i], actual[i]) ;
  }
}
