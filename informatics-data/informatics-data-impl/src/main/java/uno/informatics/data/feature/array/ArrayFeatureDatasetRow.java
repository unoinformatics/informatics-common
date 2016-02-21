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
 */
package uno.informatics.data.feature.array;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import uno.informatics.data.FeatureDatasetRow;
import uno.informatics.data.SimpleEntity;
import uno.informatics.data.pojo.PropertyHandler;

/**
 * @author Guy Davenport
 *
 */
public class ArrayFeatureDatasetRow extends PropertyHandler implements FeatureDatasetRow
{
	private Object header ;
	private Object[] values ;
  private String name;

	/**
	 * Creates a new ArrayFeatureDatasetRow using the given values. The values array is not
	 * copied, but used as is. 
	 * 
	 * @param name the name of the row
	 * @param values values for the new row
	 */
	public ArrayFeatureDatasetRow(Object[] values)
  {
	  if (values != null)
	  	this.values = values;
	  else
	  	this.values = new Object[0];
  }
	
	/**
	 * Creates a new ArrayFeatureDatasetRow using the given values. The values array is not
	 * copied, but used as is. The name of the row is extracted from the header by
	 * using {@link Namable#getName()} if the object implements {@link Namable} or
	 * using {@link Object#toString()} otherwise
	 * 
	 * @param header the header of the row
	 * @param values values for the new row
	 */
	public ArrayFeatureDatasetRow(Object header, Object[] values)
  {
	  super();
	  
	  setHeader(header) ;
	  
	  if (values != null)
	  	this.values = values;
	  else
	  	this.values = new Object[0];
  }
	
	 /**
   * Creates a new ArrayFeatureDatasetRow using the given values. The values array is not
   * copied, but used as is. The name of the row is extracted from the header by
   * using {@link Namable#getName()} if the object implements {@link Namable} or
   * using {@link Object#toString()} otherwise
   * 
   * @param header the header of the row
   * @param values values for the new row
   */
  public ArrayFeatureDatasetRow(Object header, String name, Object[] values)
  {
    super();
    
    setHeader(header) ;
    setName(name) ;
    
    if (values != null)
      this.values = values;
    else
      this.values = new Object[0];
  }

  /**
	 * Creates a new ArrayFeatureDatasetRow using the given values. If the header argument is
	 * <code>true</code> the name of the row is extracted from the header by
	 * using {@link Namable#getName()} if the object implements {@link Namable} or
	 * using {@link Object#toString()} otherwise. If the header argument is
	 * <code>false</code> the header and name are set to null. 
	 * 
	 * @param hasHeader <code>true</code> if the first element in the values is the header
	 * @param values values for the new row
	 */
	private ArrayFeatureDatasetRow(boolean hasHeader, Object[] values)
  {
	  super();
	  
	  if (values != null && values.length > 0)
	  {
		  if (hasHeader)
		  {
		  	this.values = new Object[values.length - 1];
		  	
			  setHeader(values[0]) ;
		  	
		  	for (int i = 0 ; i < values.length - 1 ; ++i)
		  		this.values[i] = values[i + 1] ;
		  }
		  else
		  {
		  	this.values = new Object[values.length];
		  	
		  	for (int i = 0 ; i < values.length ; ++i)
		  		this.values[i] = values[i] ;
		  }
	  }
	  else
	  {
	  	this.values = new Object[0];
	  }
  }

	
	/**
	 * Creates a new ArrayFeatureDatasetRow using the given values. 
	 * 
	 * @param values values for the new row
	 */
	public ArrayFeatureDatasetRow(List<Object> values)
  {
	  this(null, values);
  }
	
	/**
	 * Creates a new ArrayFeatureDatasetRow using the given values. The values array is not
	 * copied, but used as is. The name of the row is extracted from the header by
	 * using {@link Namable#getName()} if the object implements {@link Namable} or
	 * using {@link Object#toString()} otherwise
	 * 
	 * @param header the header of the row
	 * @param values values for the new row
	 */
	public ArrayFeatureDatasetRow(Object header, List<Object> values)
  {
	  setHeader(header) ;
	  
	  if (values != null)
	  	this.values = values.toArray();
	  else
	  	this.values = new Object[0];
  }
	
	 /**
   * Creates a new ArrayFeatureDatasetRow using the given values. The values array is not
   * copied, but used as is. The name of the row is extracted from the header by
   * using {@link Namable#getName()} if the object implements {@link Namable} or
   * using {@link Object#toString()} otherwise
   * 
   * @param header the header of the row
   * @param values values for the new row
   */
  public ArrayFeatureDatasetRow(Object header, String name, List<Object> values)
  {
    setHeader(header) ;
    setName(name) ;
    
    if (values != null)
      this.values = values.toArray();
    else
      this.values = new Object[0];
  }
	
	/**
	 * Creates a new ArrayFeatureDatasetRow using the given values. If the header argument is
	 * <code>true</code> the name of the row is extracted from the header by
	 * using {@link SimpleEntity#getName()} if the object implements {@link SimpleEntity} or
	 * using {@link Object#toString()} otherwise. If the header argument is
	 * <code>false</code> the header and name are set to null.
	 * 
	 * @param hasHeader <code>true</code> if the first element in the values is the header
	 * @param values values for the new row
	 */
	private ArrayFeatureDatasetRow(boolean hasHeader, List<Object> values)
  {
	  if (values != null && !values.isEmpty())
	  {
		  if (hasHeader)
		  {
		  	Iterator<Object> iterator = values.iterator() ;
		  	
		  	this.values = new Object[values.size() - 1];
		  	
		  	Object header = iterator.next() ;
		  	
			  setHeader(header) ;
		  	
		  	int i = 0 ;
		  	
		  	while (iterator.hasNext())
		  	{
		  		this.values[i] = iterator.next() ;
		  		++i ;
		  	}
		  	
		  }
		  else
		  {
			  this.values = values.toArray();
		  }
	  }
	  else
	  {
	  	this.values = new Object[0];
	  }
  }

	/* (non-Javadoc)
	 * @see uno.informatics.common.Namable#getHeader()
	 */
	@Override
	public final Object getHeader()
	{
		return header;
	}
	
	public final void setHeader(Object header)
	{
		Object oldValue = this.header ;
  	
    this.header = header;
    
    getPropertyChangeSupport().firePropertyChange(
    		HEADER_PROPERTY, oldValue, this.header) ;
    
    setName(createName(header));
	}

	/* (non-Javadoc)
	 * @see uno.informatics.data.tests.feature.array.array.DatasetRow#getValues()
	 */
	@Override
  public final List<Object> getValues()
  {
	  return toList(values);
  }

	/* (non-Javadoc)
	 * @see uno.informatics.data.tests.feature.array.array.DatasetRow#getValuesAsArray()
	 */
	@Override
  public final Object[] getValuesAsArray()
  {
	  return values;
  }
  
	/* (non-Javadoc)
	 * @see uno.informatics.data.tests.feature.array.array.DatasetRow#getValue()
	 */
	@Override
  public final Object getValue(int columnIndex)
  {
	  return values[columnIndex];
  }
	
	/* (non-Javadoc)
	 * @see uno.informatics.data.tests.feature.array.array.DatasetRow#getColumnCount()
	 */
	@Override
  public int getColumnCount()
  {
	  // TODO Auto-generated method stub
	  return values.length ;
  }
	
  @Override
  public String getName()
  {
    return name;
  }

	/**
	 * @param dataTypes
	 * @return
	 */
  private List<Object> toList(Object[] array)
  {
  	List<Object> list = new ArrayList<Object>(array.length) ;

  	for (int i = 0 ; i < array.length ; ++i)
  		list.add(array[i]) ;
  	
	  return list;
  }
  
  /**
	 * Creates new ArrayFeatureDatasetRow 
   * @param header <code>true</code> if the first value the header, <code>false</code> otherwise
	 * @param values for the row
   * @return
   */
  public static FeatureDatasetRow createRow(boolean header, List<Object> values)
  {
  	if (header)
  		return new ArrayFeatureDatasetRow(header, values) ;
  	else
  		return new ArrayFeatureDatasetRow(values) ;
  }
  
  /**
	 * Creates new ArrayFeatureDatasetRow 
   * @param header <code>true</code> if the first value the header, <code>false</code> otherwise
	 * @param values for the row
   * @return
   */
  public static FeatureDatasetRow createRow(boolean header, Object[] values)
  {
  	if (header)
  		return new ArrayFeatureDatasetRow(header, values) ;
  	else
  		return new ArrayFeatureDatasetRow(null, values) ;
  }
  
  /**
   * Creates new ArrayFeatureDatasetRow 
   * @param header the header value
   * @param values for the row
   * @return
   */
  public static FeatureDatasetRow createRow(Object header, List<Object> values)
  {
    return new ArrayFeatureDatasetRow(header, values) ;
  }
  
  /**
   * Creates new ArrayFeatureDatasetRow 
   * @param header the header value
   * @param values for the row
   * @return
   */
  public static FeatureDatasetRow createRow(Object header, Object[] values)
  {
    return new ArrayFeatureDatasetRow(header, values) ;
  }
  
  /**
   * Creates new ArrayFeatureDatasetRow 
   * @param header the header value
   * @param name the name for the row
   * @param values for the row
   * @return
   */
  public static FeatureDatasetRow createRow(Object header, String name, List<Object> values)
  {
    return new ArrayFeatureDatasetRow(header, name, values) ;
  }
  
  /**
   * Creates new ArrayFeatureDatasetRow 
   * @param header <code>true</code> if the first value the header, <code>false</code> otherwise
   * @param values for the row
   * @return
   */
  public static FeatureDatasetRow createRow(String rowId, String rowName, Object[] values)
  {
    return new ArrayFeatureDatasetRow(rowId, rowName, values) ;
  }
  
	/**
	 * @param header
	 * @return
	 */
  private String createName(Object value)
  {
  	if (value != null)
  		if (value instanceof SimpleEntity)
  			return ((SimpleEntity)value).getName();
  		else
  			return value.toString() ;
  	else
  		return null ;
  }
  
  private void setName(String name)
  {
    this.name = name ;
  }
}
