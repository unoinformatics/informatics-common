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
package uno.informatics.common;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static uno.informatics.data.DataTypeConstants.BIG_DECIMAL_ID;
import static uno.informatics.data.DataTypeConstants.BIG_INTEGER_ID;
import static uno.informatics.data.DataTypeConstants.BOOLEAN_ID;
import static uno.informatics.data.DataTypeConstants.DATE_ID;
import static uno.informatics.data.DataTypeConstants.DOUBLE_ID;
import static uno.informatics.data.DataTypeConstants.FLOAT_ID;
import static uno.informatics.data.DataTypeConstants.INT_ID;
import static uno.informatics.data.DataTypeConstants.LONG_ID;
import static uno.informatics.data.DataTypeConstants.NUMBER_IDS;
import static uno.informatics.data.DataTypeConstants.SHORT_ID;
import static uno.informatics.data.DataTypeConstants.STRING_ID;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

/**
 * @author Guy Davenport
 *
 */
public class ConversionUtilitiesTest
{

	private static final int[] DATA_TYPES1 = new int[] {
			
			STRING_ID | NUMBER_IDS, 
			STRING_ID | DOUBLE_ID | FLOAT_ID | BIG_DECIMAL_ID, 
			STRING_ID | BOOLEAN_ID,
			STRING_ID,
			STRING_ID | DATE_ID} ;
	
	private static final String[] TEST_STRINGS1 = new String[] {
			
			"1",
			"1.1",
			"true",
			"AB",
			"12/12/2012"} ;
	
	private static final int[] DATA_TYPES2 = new int[] {
		
		STRING_ID | INT_ID | LONG_ID | FLOAT_ID | DOUBLE_ID | BIG_INTEGER_ID | BIG_DECIMAL_ID , 
		//STRING | DOUBLE | BIG_DECIMAL, // TOOD something not quite right here
		STRING_ID | FLOAT_ID | DOUBLE_ID | BIG_DECIMAL_ID, 
		STRING_ID,
		STRING_ID,
		STRING_ID} ;

	private static final String[] TEST_STRINGS2 = new String[] {
		
		new Integer(new Short(Short.MAX_VALUE).toString() + "1").toString(),
		new Double("1e-2000").toString(),
		"ABC",
		"test",
		"test2"} ;
	
	
	private static final int[] DATA_TYPES3 = new int[] {
		
		STRING_ID | LONG_ID | FLOAT_ID | DOUBLE_ID | BIG_INTEGER_ID | BIG_DECIMAL_ID, 
		//STRING | BIG_DECIMAL, // TOOD something not quite right here
		STRING_ID | FLOAT_ID | DOUBLE_ID | BIG_DECIMAL_ID, 
		STRING_ID,
		STRING_ID,
		STRING_ID} ;

	private static final String[] TEST_STRINGS3 = new String[] {
		
		new Long(new Integer(Integer.MAX_VALUE).toString() + "1").toString(),
		new BigDecimal((new Double(Double.MAX_VALUE).toString() +"1")).toString(),
		"ABC",
		"test",
		"test2"} ;
	
	private static final int[] DATA_TYPES4 = new int[] {
		
		STRING_ID | BIG_INTEGER_ID | FLOAT_ID | DOUBLE_ID | BIG_DECIMAL_ID, 
		STRING_ID, 
		STRING_ID,
		STRING_ID,
		STRING_ID} ;

	private static final String[] TEST_STRINGS4 = new String[] {
		
		new BigInteger(new Long(Long.MAX_VALUE).toString() + "1").toString(),
		"test3",
		"ABC",
		"test",
		"test2"} ;
	
	private static final int[] DATA_TYPES5 = new int[] {
		
		STRING_ID, 
		STRING_ID, 
		STRING_ID,
		STRING_ID,
		STRING_ID} ;

	private static final String[] TEST_STRINGS5 = new String[] {
		
		"test4",
		"test3",
		"ABC",
		"test",
		"test2"} ;
	
	private static final int[][] DATA_TYPES = new int[][] {
		
		DATA_TYPES1, 
		DATA_TYPES2,
		DATA_TYPES3,
		DATA_TYPES4,
		DATA_TYPES5} ;

	private static final String[][] TEST_STRINGS = new String[][] {
		
		TEST_STRINGS1, 
		TEST_STRINGS2,
		TEST_STRINGS3,
		TEST_STRINGS4,
		TEST_STRINGS5} ;
	
	/**
	 * Test method for {@link uno.informatics.common.ConversionUtilities#convertToString(java.lang.String)}.
	 */
	@Test
	public void testConvertToStringString()
	{
		assertEquals("test",ConversionUtilities.convertToString("test")) ;
	}

	/**
	 * Test method for {@link uno.informatics.common.ConversionUtilities#convertToObject(java.lang.String)}.
	 */
	@Test
	public void testConvertToObjectString()
	{
		assertEquals("test",ConversionUtilities.convertToObject("test")) ; // String conversion test
		assertEquals(1,ConversionUtilities.convertToObject("1")) ; // Integer conversion test
		assertEquals(1.1,ConversionUtilities.convertToObject("1.1")) ; // Double conversion test
		assertEquals(true,ConversionUtilities.convertToObject("true")) ; // Boolean conversion test
		assertEquals(true,ConversionUtilities.convertToObject("True")) ; // Boolean conversion test
		assertEquals(false,ConversionUtilities.convertToObject("FALSE")) ; // Boolean conversion test
	} 

	/**
	 * Test method for {@link uno.informatics.common.ConversionUtilities#convertToObject(java.lang.String, int)}.
	 */
	@Test
	public void testConvertToObjectStringInt()
	{
		try
    {
      assertEquals("test",ConversionUtilities.convertToObject("test", STRING_ID)) ; // String conversion test
      assertEquals((short)1,ConversionUtilities.convertToObject("1", SHORT_ID)) ; // Integer conversion test
      assertEquals(1,ConversionUtilities.convertToObject("1", INT_ID)) ; // Integer conversion test
      assertEquals(1l,ConversionUtilities.convertToObject("1", LONG_ID)) ; // Integer conversion test
      assertEquals((float)1.1,ConversionUtilities.convertToObject("1.1", FLOAT_ID)) ; // Double conversion test
      assertEquals(1.1,ConversionUtilities.convertToObject("1.1", DOUBLE_ID)) ; // Double conversion test
      assertEquals(new BigInteger("1"),ConversionUtilities.convertToObject("1", BIG_INTEGER_ID)) ; // BigInteger conversion test
      assertEquals(new BigDecimal("1.1"),ConversionUtilities.convertToObject("1.1", BIG_DECIMAL_ID)) ; // BigDecimal conversion test
      assertEquals(true,ConversionUtilities.convertToObject("true", BOOLEAN_ID)) ; // Boolean conversion test
      assertEquals(true,ConversionUtilities.convertToObject("True", BOOLEAN_ID)) ; // Boolean conversion test
      assertEquals(false,ConversionUtilities.convertToObject("FALSE", BOOLEAN_ID)) ; // Boolean conversion test
    }
    catch (ConversionException e)
    {
      e.printStackTrace();
      
      fail(e.getMessage()) ;
    }
	}

	/**
	 * Test method for {@link uno.informatics.common.ConversionUtilities#convertToBigDecimal(java.lang.String)}.
	 */
	@Test
	public void testConvertToBigDecimal()
	{
		try
    {
	    assertEquals(new BigDecimal("1.1"),ConversionUtilities.convertToBigDecimal("1.1")) ;
    }
    catch (ConversionException e)
    {
	    fail(e.getLocalizedMessage()) ;
    }
	}

	/**
	 * Test method for {@link uno.informatics.common.ConversionUtilities#convertToBigInteger(java.lang.String)}.
	 */
	@Test
	public void testConvertToBigInteger()
	{
		try
    {
	    assertEquals(new BigInteger("1"), ConversionUtilities.convertToBigInteger("1")) ;
    }
    catch (ConversionException e)
    {
	    fail(e.getLocalizedMessage()) ;
    }
	}

	/**
	 * Test method for {@link uno.informatics.common.ConversionUtilities#convertToDouble(java.lang.String)}.
	 */
	@Test
	public void testConvertToDoubleString()
	{
		try
    {
	    assertEquals(new Double(1.1), ConversionUtilities.convertToDouble("1.1")) ; 
    }
    catch (ConversionException e)
    {
	    fail(e.getLocalizedMessage()) ;
    }
	}

	/**
	 * Test method for {@link uno.informatics.common.ConversionUtilities#convertToFloat(java.lang.String)}.
	 */
	@Test
	public void testConvertToFloat()
	{
		try
    {
	    assertEquals(new Float("1.1"),ConversionUtilities.convertToFloat("1.1")) ; 
    }
    catch (ConversionException e)
    {
	    fail(e.getLocalizedMessage()) ;
    }
	}

	/**
	 * Test method for {@link uno.informatics.common.ConversionUtilities#convertToLong(java.lang.String)}.
	 */
	@Test
	public void testConvertToLong()
	{
		try
    {
	    assertEquals(new Long("1"),ConversionUtilities.convertToLong("1")) ;
    }
    catch (ConversionException e)
    {
	    fail(e.getLocalizedMessage()) ;
    }
	}

	/**
	 * Test method for {@link uno.informatics.common.ConversionUtilities#convertToInteger(java.lang.String)}.
	 */
	@Test
	public void testConvertToIntegerString()
	{
		try
    {
	    assertEquals(new Integer("1"),ConversionUtilities.convertToInteger("1")) ;
    }
    catch (ConversionException e)
    {
	    fail(e.getLocalizedMessage()) ;
    }
	}

	/**
	 * Test method for {@link uno.informatics.common.ConversionUtilities#convertToShort(java.lang.String)}.
	 */
	@Test
	public void testConvertToShort()
	{
		try
    {
	    assertEquals(new Short("1"),ConversionUtilities.convertToShort("1")) ;
    }
    catch (ConversionException e)
    {
	    fail(e.getLocalizedMessage()) ;
    }
	}

	/**
	 * Test method for {@link uno.informatics.common.ConversionUtilities#convertToBoolean(java.lang.String)}.
	 */
	@Test
	public void testConvertToBoolean()
	{
		try
    {
	    assertEquals(true, ConversionUtilities.convertToBoolean("true")) ;
    }
    catch (ConversionException e)
    {
	    fail(e.getLocalizedMessage()) ;
    }
	}

	/**
	 * Test method for {@link uno.informatics.common.ConversionUtilities#convertToDate(java.lang.String)}.
	 */
	@Test
	public void testConvertToDate()
	{
		try
    {
	    assertEquals(new Date("12/12/12"),ConversionUtilities.convertToDate("12/12/12")) ;
    }
    catch (ConversionException e)
    {
	    fail(e.getLocalizedMessage()) ;
    }
	}

	/**
	 * Test method for {@link uno.informatics.common.ConversionUtilities#convertToLSID(java.lang.String)}.
	 */
	@Test
	public void testConvertToLSID()
	{
		// TODO
		/*try
    {
	    assertEquals(new LSID("URN_LSID_"),ConversionUtilities.convertToLSID("1")) ;
    }
    catch (ConversionException e)
    {
	    fail(e.getLocalizedMessage()) ;
    }*/
	}

	/**
	 * Test method for {@link uno.informatics.common.ConversionUtilities#convertToString(java.lang.Object)}.
	 */
	@Test
	public void testConvertToStringObject()
	{
		assertEquals("1",ConversionUtilities.convertToString(1)) ;
	}

	/**
	 * Test method for {@link uno.informatics.common.ConversionUtilities#convertToDouble(java.lang.Object)}.
	 */
	@Test
	public void testConvertToDoubleObject()
	{
		try
    {
	    assertEquals(new Double(1),ConversionUtilities.convertToDouble("1.0")) ;
	    assertEquals(new Double(1.1),ConversionUtilities.convertToDouble(1.1)) ;
    }
    catch (ConversionException e)
    {
	    fail(e.getLocalizedMessage()) ;
    }
	}

	/**
	 * Test method for {@link uno.informatics.common.ConversionUtilities#convertToInteger(java.lang.Object)}.
	 */
	@Test
	public void testConvertToIntegerObject()
	{
		try
    {
	    assertEquals(new Integer("1"),ConversionUtilities.convertToInteger("1")) ;
	    assertEquals(new Integer("1"),ConversionUtilities.convertToInteger(1)) ;
    }
    catch (ConversionException e)
    {
	    fail(e.getLocalizedMessage()) ;
    }
	}

	/**
	 * Test method for {@link uno.informatics.common.ConversionUtilities#getDataType(java.lang.String)}.
	 */
	@Test
	public void testGetDataType()
	{
		for (int i = 0 ; i < TEST_STRINGS.length ; ++i)
			for (int j = 0 ; j < TEST_STRINGS[i].length ; ++j)
				assertEquals("test " + (i + 1) + " , " + (j + 1), DATA_TYPES[i][j] , ConversionUtilities.getDataType(TEST_STRINGS[i][j])) ;
	}

	/**
	 * Test method for {@link uno.informatics.common.ConversionUtilities#getDataTypes(java.util.List)}.
	 */
	@Test
	public void testGetDataTypesListOfString()
	{
		assertEquals(toList(DATA_TYPES1), ConversionUtilities.getDataTypes(toList(TEST_STRINGS1))) ;
	}

	/**
	 * Test method for {@link uno.informatics.common.ConversionUtilities#getDataTypes(java.lang.String[])}.
	 */
	@Test
	public void testGetDataTypesStringArray()
	{
		assertArrayEquals(DATA_TYPES1, ConversionUtilities.getDataTypes(TEST_STRINGS1)) ;
	}

	/**
	 * Test method for {@link uno.informatics.common.ConversionUtilities#getDataTypes(java.util.List, java.util.List)}.
	 */
	@Test
	public void testGetDataTypesListOfStringListOfInteger()
	{
		List<Integer> last ;
		List<Integer> next ;
		
		assertEquals(toList(DATA_TYPES[0]), last = ConversionUtilities.getDataTypes(toList(TEST_STRINGS[0]))) ;
		
		for (int i = 1 ; i < TEST_STRINGS.length ; ++i)
		{
			next = ConversionUtilities.getDataTypes(toList(TEST_STRINGS[i]), last) ;
			assertEquals("test " + i , toList(DATA_TYPES[i]) , next) ;
			last = next ;
		}
		
	}

	/**
	 * Test method for {@link uno.informatics.common.ConversionUtilities#getDataTypes(java.lang.String[], int[])}.
	 */
	@Test
	public void testGetDataTypesStringArrayIntArray()
	{
		int[] last ;
		int[] next ;
		
		assertArrayEquals(DATA_TYPES[0], last = ConversionUtilities.getDataTypes(TEST_STRINGS[0])) ;
		
		for (int i = 1 ; i < TEST_STRINGS.length ; ++i)
		{
			next = ConversionUtilities.getDataTypes(TEST_STRINGS[i], last) ;
			assertArrayEquals("test " + (i + 1) , DATA_TYPES[i] , next) ;
			last = next ;
		}
		
	}

	/**
	 * @param dataTypes
	 * @return
	 */
  private List<String> toList(String[] array)
  {
  	List<String> list = new ArrayList<String>(array.length) ;

  	for (int i = 0 ; i < array.length ; ++i)
  		list.add(array[i]) ;
  	
	  return list;
  }

	/**
	 * @param dataTypes
	 * @return
	 */
  private List<Integer> toList(int[] array)
  {
  	List<Integer> list = new ArrayList<Integer>(array.length) ;

  	for (int i = 0 ; i < array.length ; ++i)
  		list.add(array[i]) ;
  	
	  return list;
  }
}
