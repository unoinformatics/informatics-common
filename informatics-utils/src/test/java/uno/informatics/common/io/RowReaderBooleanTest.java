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
	
	protected final static boolean[] ROW2_WITH_GAPS = new boolean[] {false,false,false} ;
	protected final static boolean[] ROW3_WITH_GAPS = new boolean[] {true,false,false} ;
	
	protected final static Object[] OBJ_ROW1 = new Object[] {true,false,true} ;
	protected final static Object[] OBJ_ROW2 = new Object[] {false,true,false} ;
	protected final static Object[] OBJ_ROW3 = new Object[] {true,false,false} ;
	
	protected final static Object[] OBJ_ROW2_WITH_GAPS = new Object[] {false,null,false} ;
	protected final static Object[] OBJ_ROW3_WITH_GAPS = new Object[] {true,false,null} ;
	        
	protected static final List<List<Object>> TABLE_AS_LIST = new ArrayList<List<Object>>();
	
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
	
        protected static final List<List<Object>> TABLE_AS_LIST_WITH_GAPS = new ArrayList<List<Object>>();
        
        static
        {
            TABLE_AS_LIST_WITH_GAPS.add(new ArrayList<Object>(ROW1.length)) ;
                
            TABLE_AS_LIST_WITH_GAPS.get(0).add(ROW1[0]) ;
            TABLE_AS_LIST_WITH_GAPS.get(0).add(ROW1[1]) ;
            TABLE_AS_LIST_WITH_GAPS.get(0).add(ROW1[2]) ;
                
            TABLE_AS_LIST_WITH_GAPS.add(new ArrayList<Object>(ROW2.length)) ;
                
            TABLE_AS_LIST_WITH_GAPS.get(1).add(ROW2[0]) ;
            TABLE_AS_LIST_WITH_GAPS.get(1).add(null) ;
            TABLE_AS_LIST_WITH_GAPS.get(1).add(ROW2[2]) ;
                
            TABLE_AS_LIST_WITH_GAPS.add(new ArrayList<Object>(ROW3.length)) ;
                
            TABLE_AS_LIST_WITH_GAPS.get(2).add(ROW3[0]) ;
            TABLE_AS_LIST_WITH_GAPS.get(2).add(ROW3[1]) ;
            TABLE_AS_LIST_WITH_GAPS.get(2).add(null) ;
        }
        	
	
	protected static final Object[][] TABLE_AS_ARRAY = new Object[][]
			{OBJ_ROW1, OBJ_ROW2, OBJ_ROW3};
			
	protected static final Object[][] TABLE_AS_ARRAY_WITH_GAPS = new Object[][]
	                {OBJ_ROW1, OBJ_ROW2_WITH_GAPS, OBJ_ROW3_WITH_GAPS};
	
	protected static final List<List<Boolean>> TABLE_AS_LIST2 = new ArrayList<List<Boolean>>();
	
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
	
	protected static final boolean[][] TABLE_AS_ARRAY2 = new boolean[][]
			{ROW1, ROW2, ROW3};

		        protected static final List<List<Boolean>> TABLE_AS_LIST2_WITH_GAPS = new ArrayList<List<Boolean>>();
		        
		        static
		        {
		            TABLE_AS_LIST2_WITH_GAPS.add(new ArrayList<Boolean>(ROW1.length)) ;
		                
		            TABLE_AS_LIST2_WITH_GAPS.get(0).add(ROW1[0]) ;
		            TABLE_AS_LIST2_WITH_GAPS.get(0).add(ROW1[1]) ;
		            TABLE_AS_LIST2_WITH_GAPS.get(0).add(ROW1[2]) ;
		                
		            TABLE_AS_LIST2_WITH_GAPS.add(new ArrayList<Boolean>(ROW2.length)) ;
		                
		            TABLE_AS_LIST2_WITH_GAPS.get(1).add(ROW2[0]) ;
		            TABLE_AS_LIST2_WITH_GAPS.get(1).add(null) ;
		            TABLE_AS_LIST2_WITH_GAPS.get(1).add(ROW2[2]) ;
		                
		            TABLE_AS_LIST2_WITH_GAPS.add(new ArrayList<Boolean>(ROW3.length)) ;
		                
		            TABLE_AS_LIST2_WITH_GAPS.get(2).add(ROW3[0]) ;
		            TABLE_AS_LIST2_WITH_GAPS.get(2).add(ROW3[1]) ;
		            TABLE_AS_LIST2_WITH_GAPS.get(2).add(null) ;
		        }
		        protected static final boolean[][] TABLE_AS_ARRAY2_WITH_GAPS = new boolean[][]
		                        {ROW1, ROW2_WITH_GAPS, ROW3_WITH_GAPS};
		                        
  protected abstract List<List<Object>> getExpectedList() ;

  protected abstract Object[][] getExpectedArray() ;

  protected abstract List<List<Boolean>> getExpectedAsList() ;

  protected abstract boolean[][] getExpectedAsArray() ;
	
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
	public void testReadCellsAsArrayAsBoolean()
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
