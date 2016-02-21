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

import static uno.informatics.data.DataType.BIG_DECIMAL;
import static uno.informatics.data.DataType.BIG_INTEGER;
import static uno.informatics.data.DataType.BOOLEAN;
import static uno.informatics.data.DataType.DATE;
import static uno.informatics.data.DataType.DOUBLE;
import static uno.informatics.data.DataType.FLOAT;
import static uno.informatics.data.DataType.INTEGER;
import static uno.informatics.data.DataType.LONG;
import static uno.informatics.data.DataType.SHORT;
import static uno.informatics.data.DataType.STRING;
import static uno.informatics.data.DataTypeConstants.BIG_DECIMAL_ID;
import static uno.informatics.data.DataTypeConstants.BIG_INTEGER_ID;
import static uno.informatics.data.DataTypeConstants.BOOLEAN_ID;
import static uno.informatics.data.DataTypeConstants.DATE_ID;
import static uno.informatics.data.DataTypeConstants.DEFAULT_TYPE_IDS;
import static uno.informatics.data.DataTypeConstants.DOUBLE_ID;
import static uno.informatics.data.DataTypeConstants.FLOAT_ID;
import static uno.informatics.data.DataTypeConstants.INT_ID;
import static uno.informatics.data.DataTypeConstants.LONG_ID;
import static uno.informatics.data.DataTypeConstants.SHORT_ID;
import static uno.informatics.data.DataTypeConstants.STRING_ID;
import static uno.informatics.data.DataTypeConstants.UNKNOWN_ID;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ConversionUtilities
{
	private static final String TRUE = "true";
	private static final String FALSE = "false";
	
	private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmssZ");
	
  @SuppressWarnings("unused")
  private static String DEFAULT_DELIMITER    = "&" ;

  public static final String convertToString(String value)
  {
    return value;
  }

  /**
   * Converts a string to an object to an integer, double, boolean or string. Will try to convert in following order
   * Integer, Double and Boolean, or return the original String
   * 
   * @param string the string to be converted
   * @return a object representation of the string
   */
  public static final Object convertToObject(String string)
  {
    try
    {
      return convertToObject(string, DEFAULT_TYPE_IDS);
    }
    catch (ConversionException e)
    {
      return string ;
    }
  }

  /**
   * Converts a string to an object given a set of possible types to convert to. Will try to convert in following order
   * Short, Integer, Long, Float, Double, BigInteger, BigDecmal and Boolean, Date and String. An exception is throw if
   * the string can not be converted to one of the possible types.
   * 
   * @param string the string to be converted
   * @param types possible types to which to convert
   * @return a object representation of the string
   */
  public static final Object convertToObject(String string, int types) throws ConversionException
  {
    Object value = null ;
    
    if (string != null)
    {      
      if (value == null && (types & SHORT_ID) != 0)
        value = convertToShortInternal(string) ;
      
      if (value == null && (types & INT_ID) != 0)
        value = convertToIntegerInternal(string) ;
      
      if (value == null && (types & LONG_ID) != 0)
        value = convertToLongInternal(string) ;
      
      if (value == null && (types & FLOAT_ID) != 0)
        value = convertToFloatInternal(string) ;
      
      if (value == null && (types & DOUBLE_ID) != 0)
        value = convertToDoubleInternal(string) ;
      
      if (value == null && (types & BIG_INTEGER_ID) != 0)
        value = convertToBigIntegerInternal(string) ;

      if (value == null && (types & BIG_DECIMAL_ID) != 0)
        value = convertToBigDecimalInternal(string) ;
      
      if (value == null && (types & BOOLEAN_ID) != 0)
        value = convertToBooleanInternal(string) ;
      
      if (value == null && (types & DATE_ID) != 0)
        value = convertToDateInternal(string) ;
      
      if (value == null && (types & STRING_ID) != 0)
        value = string ;
      
      if (value == null)
        throw new ConversionException("Can not convert to Object using requested types " + getTypesNames(types) + "!") ;
    }
    
    return value;
  }
  
  public static List<String> getTypesNames(int types)
  {
    List<String> labels = new LinkedList<String>() ;
         
    if ((types & SHORT_ID) != 0)
      labels.add(SHORT.getName()) ;

    if ((types & INT_ID) != 0)
      labels.add(INTEGER.getName()) ;
    
    if ((types & SHORT_ID) != 0)
      labels.add(SHORT.getName()) ;

    if ((types & LONG_ID) != 0)
      labels.add(LONG.getName()) ;  
    
    if ((types & FLOAT_ID) != 0)
      labels.add(FLOAT.getName()) ;

    if ((types & DOUBLE_ID) != 0)
      labels.add(DOUBLE.getName()) ;
    
    if ((types & BIG_INTEGER_ID) != 0)
      labels.add(BIG_INTEGER.getName()) ;

    if ((types & BIG_DECIMAL_ID) != 0)
      labels.add(BIG_DECIMAL.getName()) ; 
    
    if ((types & BOOLEAN_ID) != 0)
      labels.add(BOOLEAN.getName()) ;
    
    if ((types & DATE_ID) != 0)
      labels.add(DATE.getName()) ;

    if ((types & STRING_ID) != 0)
      labels.add(STRING.getName()) ; 
    
    return labels ;
  }

  /**
   * Converts a string to an number given a set of possible types to convert to. Will try to convert in following order
   * Short, Integer, Long, Float, Double, BigInteger, BigDecmal
   * 
   * @param string the string to be converted
   * @param types types to convert
   * @return a object representation of the string
   * @throws ConversionException 
   */
  public static final Number convertToNumber(String string, int types) throws ConversionException
  {
    Object value = null ;
    
    if (string != null)
    {      
      if (value == null && (types & SHORT_ID) != 0)
        value = convertToShortInternal(string) ;
      
      if (value == null && (types & INT_ID) != 0)
        value = convertToIntegerInternal(string) ;
      
      if (value == null && (types & LONG_ID) != 0)
        value = convertToLongInternal(string) ;
      
      if (value == null && (types & FLOAT_ID) != 0)
        value = convertToFloatInternal(string) ;
      
      if (value == null && (types & DOUBLE_ID) != 0)
        value = convertToDoubleInternal(string) ;
      
      if (value == null && (types & BIG_INTEGER_ID) != 0)
        value = convertToBigIntegerInternal(string) ;

      if (value == null && (types & BIG_DECIMAL_ID) != 0)
        value = convertToBigDecimalInternal(string) ;
      
      if (value == null)
        throw new ConversionException("Can not convert to Number using requested types " + getTypesNames(types) + "!") ;
    }
    
    return (Number)value;
  }


  public static final BigDecimal convertToBigDecimal(String string) throws ConversionException
  {
    BigDecimal value = null ;
    
    if (string != null)
    {
      try
      {
        value = new BigDecimal(string) ;
      }
      catch (NumberFormatException e)
      {
        throw new ConversionException(e.getMessage(), e);
      }
    }
    
    return value ;
  }
  
  public static final BigInteger convertToBigInteger(String string) throws ConversionException
  {
    BigInteger value = null ;
    
    if (string != null)
    {
      try
      {
        value = new BigInteger(string) ;
      }
      catch (NumberFormatException e)
      {
        throw new ConversionException(e.getMessage(), e);
      }
    }
    
    return value ;
  }
  
  public static final Double convertToDouble(String string) throws ConversionException
  {
    Double value = null ;
    
    if (string != null)
    {
      try
      {
        value = Double.valueOf(string);
      }
      catch (NumberFormatException e)
      {
        throw new ConversionException(e.getMessage(), e);
      }
    }
    
    return value ;
  }

  public static final Float convertToFloat(String string) throws ConversionException
  {
    Float value = null ;
    
    if (string != null)
    {
      try
      {
        value = Float.valueOf(string);
      }
      catch (NumberFormatException e)
      {
        throw new ConversionException(e.getMessage(), e);
      }
    }
    
    return value ;
  }
  
  public static final Long convertToLong(String string) throws ConversionException
  {
    Long value = null ;
    
    if (string != null)
    {
      try
      {
        value = Long.valueOf(string);
      }
      catch (NumberFormatException e)
      {
        throw new ConversionException(e.getMessage(), e);
      }
    }
    
    return value ;
  }
  public static final Integer convertToInteger(String string) throws ConversionException
  {
    Integer value = null ;
    
    if (string != null)
    {
      try
      {
        value = Integer.valueOf(string);
      }
      catch (NumberFormatException e)
      {
        throw new ConversionException(e.getMessage(), e);
      }
    }
    
    return value ;
  }
  
  public static final Short convertToShort(String string) throws ConversionException
  {
    Short value = null ;
    
    if (string != null)
    {
      try
      {
        value = Short.valueOf(string);
      }
      catch (NumberFormatException e)
      {
        throw new ConversionException(e.getMessage(), e);
      }
    }
    
    return value ;
  }
  
  public static final Boolean convertToBoolean(String string) throws ConversionException
  {
  	Boolean value = null ;
    
    if (string != null)
    {
    	if (TRUE.equals(string.toLowerCase()))
    	{
    		return true ;
    	}
    	else
      {
      	if (FALSE.equals(string.toLowerCase()))
      	{
      		return false ;
      	}
      }
    }
    
    return value ;
  }
  
  @SuppressWarnings("deprecation")
  public static final Date convertToDate(String value) throws ConversionException
  {
    if (value != null)
    {
	    try
      {
	      return DATE_FORMAT.parse(value) ;
      }
      catch (Exception e)
      {
  	    try
        {
  	      return new Date(value) ;
        }
        catch (Exception e2)
        {
  	      throw new ConversionException(e2) ;
        }
      }
    }
    else
    {
      return null ;
    }
  }

  public static final String convertToString(Object value)
  {
    if (value != null)
    	if (value instanceof Date)
    		return DATE_FORMAT.format((Date)value) ;
    	else
    		return value.toString() ;
    else
      return null ;
  }

  public static final Double convertToDouble(Object value) throws ConversionException
  {
    if (value != null)
    {
      if (value instanceof Double)
      {
        return (Double)value ;
      }
      else
      {
        if (value instanceof Number)
        {
          return ((Number)value).doubleValue() ;
        }
        else
        {
          if (value instanceof String)
          {
            return convertToDouble((String)value) ;
          }
          else
          {
            throw new ConversionException("Can not convert value to double : " + value) ;
          }
        }
      }
    }
    else
    {
      return null ;
    }  
  }
  
  public static final Integer convertToInteger(Object value) throws ConversionException
  {
    if (value != null)
    {
      if (value instanceof Integer)
      {
        return (Integer)value ;
      }
      else
      {
        if (value instanceof Number)
        {
          return ((Number)value).intValue() ;
        }
        else
        {
          if (value instanceof String)
          {
            return convertToInteger((String)value) ;
          }
          else
          {
            throw new ConversionException("Can not convert value to integer : " + value) ;
          }
        }
      }
    }
    else
    {
      return null ;
    }  
  }
  
	/**
	 * @param values
	 * @param types
	 * @return
	 * @throws ConversionException 
	 */
  public static List<Short> convertToShortList(List<String> values) throws ConversionException
  {
  	List<Short> list = new ArrayList<Short>() ;

  	Iterator<String> iterator = values.iterator() ;
  	
  	while(iterator.hasNext())
  	{
  		list.add(convertToShort(iterator.next())) ;
  	}
  	
	  return list;
  }
  
	/**
	 * @param values
	 * @param types
	 * @return
	 * @throws ConversionException 
	 */
  public static List<Integer> convertToIntegerList(List<String> values) throws ConversionException
  {
  	List<Integer> list = new ArrayList<Integer>() ;

  	Iterator<String> iterator = values.iterator() ;
  	
  	while(iterator.hasNext())
  	{
  		list.add(convertToInteger(iterator.next())) ;
  	}
  	
	  return list;
  }
  
	/**
	 * @param values
	 * @param types
	 * @return
	 * @throws ConversionException 
	 */
  public static List<Long> convertToLongList(List<String> values) throws ConversionException
  {
  	List<Long> list = new ArrayList<Long>() ;

  	Iterator<String> iterator = values.iterator() ;
  	
  	while(iterator.hasNext())
  	{
  		list.add(convertToLong(iterator.next())) ;
  	}
  	
	  return list;
  }
  
	/**
	 * @param cells
	 * @param types
	 * @return
	 * @throws ConversionException 
	 */
  public static List<Float> convertToFloatList(List<String> values) throws ConversionException
  {
  	List<Float> list = new ArrayList<Float>() ;

  	Iterator<String> iterator = values.iterator() ;
  	
  	while(iterator.hasNext())
  	{
  		list.add(convertToFloat(iterator.next())) ;
  	}
  	
	  return list;
  }
  
	/**
	 * @param cells
	 * @param types
	 * @return
	 * @throws ConversionException 
	 */
  public static List<Double> convertToDoubleList(List<String> values) throws ConversionException
  {
  	List<Double> list = new ArrayList<Double>() ;

  	Iterator<String> iterator = values.iterator() ;
  	
  	while(iterator.hasNext())
  	{
  		list.add(convertToDouble(iterator.next())) ;
  	}
  	
	  return list;
  }
  
	/**
	 * @param values
	 * @param types
	 * @return
	 * @throws ConversionException 
	 */
  public static List<Object> convertToObjectList(List<String> values, int type) throws ConversionException
  {
  	List<Object> list = new ArrayList<Object>() ;

  	Iterator<String> iterator = values.iterator() ;
  	
  	while(iterator.hasNext())
  	{
  		list.add(convertToObject(iterator.next(), type)) ;
  	}
  	
	  return list;
  }
  
	/**
	 * @param values
	 * @param types
	 * @return
	 * @throws ConversionException 
	 */
  public static List<Object> convertToObjectList(List<String> values, int[] types) throws ConversionException
  {
  	List<Object> list = new ArrayList<Object>() ;

  	Iterator<String> iterator = values.iterator() ;
  	
  	int i = 0 ;
  	
  	while(iterator.hasNext())
  	{
  		if (i < types.length)
  			list.add(convertToObject(iterator.next(), types[i])) ;
  		else
  			list.add(convertToObject(iterator.next())) ;
  		
  		++i ;
  	}
  	
	  return list;
  }
  
  public static final int getDataType(String string)
  {
    int datatype = UNKNOWN_ID ;
    
    if (string != null)
    {   
    	datatype = STRING_ID ;
    	
      if (convertToShortInternal(string) != null)
      	datatype = datatype | SHORT_ID ;
      
      if (convertToIntegerInternal(string)!= null)
      	datatype = datatype | INT_ID ;
      
      if (convertToLongInternal(string)!= null)
      	datatype = datatype | LONG_ID ;
      
      if (convertToFloatInternal(string) != null)
      	datatype = datatype | FLOAT_ID ;
      
      if (convertToDoubleInternal(string) != null)
      	datatype = datatype | DOUBLE_ID ;
      
      if (convertToBigIntegerInternal(string) != null)
      	datatype = datatype | BIG_INTEGER_ID ;

      if (convertToBigDecimalInternal(string) != null)
      	datatype = datatype | BIG_DECIMAL_ID ;
      
      if (convertToBooleanInternal(string) != null)
      	datatype = datatype | BOOLEAN_ID ;
      
      if (convertToDateInternal(string) != null)
      	datatype = datatype | DATE_ID ;
    }
    
    return datatype ;
  }
  
  public static final List<Integer> getDataTypes(List<String> strings)
  {
  	List<Integer> datatypes ;

    if (strings != null)
    {      
    	datatypes = new ArrayList<Integer>(strings.size()) ;
    	
    	Iterator<String> iterator = strings.iterator() ;
    	
      while(iterator.hasNext())
      	datatypes.add(getDataType(iterator.next())) ;
    }
    else
    {
    	datatypes = new ArrayList<Integer>() ;
    }
    
    return datatypes ;
  }
  
  public static final int[] getDataTypes(String[] strings)
  {
  	int[] datatypes ;

    if (strings != null)
    {      
    	datatypes = new int[strings.length];
    	  	
      for(int i = 0 ; i < strings.length ; ++i)
      	datatypes[i] = getDataType(strings[i]) ;
    }
    else
    {
    	datatypes = new int[0];
    }
    
    return datatypes ;
  }
  
  public static final List<Integer> getDataTypes(List<String> strings, List<Integer> currentDatatypes)
  {
  	List<Integer> datatypes ;

    if (strings != null && currentDatatypes != null && strings.size() == currentDatatypes.size())
    {      
    	datatypes = new ArrayList<Integer>(strings.size()) ;
    	
    	Iterator<String> iterator = strings.iterator() ;
    	Iterator<Integer> iterator2 = currentDatatypes.iterator() ;
    	
      while(iterator.hasNext() && iterator2.hasNext())
      	datatypes.add(iterator2.next() & getDataType(iterator.next())) ;
    }
    else
    {
    	datatypes = new ArrayList<Integer>(currentDatatypes) ;
    }
    
    return datatypes ;
  }
  
  public static final int[] getDataTypes(String[] strings, int[] currentDatatypes)
  {
  	int[] datatypes ;

    if (strings != null && currentDatatypes != null && strings.length == currentDatatypes.length)
    {      
    	datatypes = new int[strings.length];
    	  	
      for(int i = 0 ; i < strings.length ; ++i)
      	datatypes[i] = currentDatatypes[i] & getDataType(strings[i]) ;
    }
    else
    {
    	datatypes = currentDatatypes ;
    }

    return datatypes ;
  }

  private static final BigDecimal convertToBigDecimalInternal(String string)
  {
    try
    {
      return convertToBigDecimal(string);
    }
    catch (ConversionException e)
    {
      return null ;
    }
  }

  private static final BigInteger convertToBigIntegerInternal(String string)
  {
    try
    {
      return convertToBigInteger(string);
    }
    catch (ConversionException e)
    {
      return null ;
    }
  }

  private static final Double convertToDoubleInternal(String string)
  {
    try
    {
      return convertToDouble(string);
    }
    catch (ConversionException e)
    {
      return null ;
    }
  }

  private static final Float convertToFloatInternal(String string)
  {
    try
    {
      return convertToFloat(string);
    }
    catch (ConversionException e)
    {
      return null ;
    }
  }

  private static final Long convertToLongInternal(String string)
  {
    try
    {
      return convertToLong(string);
    }
    catch (ConversionException e)
    {
      return null ;
    }
  }

  private static final Integer convertToIntegerInternal(String string)
  {
    try
    {
      return convertToInteger(string);
    }
    catch (ConversionException e)
    {
      return null ;
    }
  }

  private static final Short convertToShortInternal(String string)
  {
    try
    {
      return convertToShort(string);
    }
    catch (ConversionException e)
    {
      return null ;
    }
  }
  
  private static final Boolean convertToBooleanInternal(String string)
  {
    try
    {
      return convertToBoolean(string);
    }
    catch (ConversionException e)
    {
      return null ;
    }
  }
  
  private static final Date convertToDateInternal(String string)
  {
    try
    {
      return convertToDate(string);
    }
    catch (ConversionException e)
    {
      return null ;
    }
  }
}
