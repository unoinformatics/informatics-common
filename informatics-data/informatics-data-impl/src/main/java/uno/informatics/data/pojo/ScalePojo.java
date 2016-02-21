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
package uno.informatics.data.pojo ;

import java.util.Set;
import java.util.TreeSet;

import uno.informatics.data.DataType;
import uno.informatics.data.Scale;
import uno.informatics.data.ScaleType;

/**
 * @author Guy Davenport
 *
 */
public class ScalePojo extends EntityPojo implements Scale
{	
  
	private static final String DATA_TYPE_PROPERTY = Scale.class.getName() + ".dataType" ;
	private static final String SCALE_TYPE_PROPERTY = Scale.class.getName() + ".scaleType" ;
	private static final String MAXIMUM_VALUE_PROPERTY = Scale.class.getName() + ".maximumValue";
	private static final String MINIMUM_VALUE_PROPERTY = Scale.class.getName() + ".minimumValue";
	private static final String VALUES_PROPERTY = Scale.class.getName() + ".values" ;
  
  private Set<Object> values = null ;

	private DataType dataType ;
	private ScaleType scaleType ;
	private Number              maximumValue;
	private Number              minimumValue;
	  

	public ScalePojo(String name)
  {
	  this(name, DataType.STRING, ScaleType.NOMINAL);
  }	
	
	public ScalePojo(String uniqueIdentifier, String name)
  {
	  this(uniqueIdentifier, name, DataType.STRING, ScaleType.NOMINAL);
  }
	
	public ScalePojo(String uniqueIdentifier, String name, String description)
  {
	  this(uniqueIdentifier, name, description, DataType.STRING, ScaleType.NOMINAL);
  }

	public ScalePojo(String name, DataType dataType, ScaleType scaleType)
  {
	  super(name);
	  
	  setDataType(dataType) ;
	  setScaleType(scaleType) ;
  }
	
	public ScalePojo(String uniqueIdentifier, String name, DataType dataType, ScaleType scaleType)
  {
	  super(uniqueIdentifier, name);
	  
	  setDataType(dataType) ;
	  setScaleType(scaleType) ;
  }
	
	public ScalePojo(String uniqueIdentifier, String name, String description, DataType dataType, ScaleType scaleType)
  {
	  super(uniqueIdentifier, name, description);
	  
	  setDataType(dataType) ;
	  setScaleType(scaleType) ;
  }
	
  public ScalePojo(String name, DataType dataType, ScaleType scaleType, Number minimum, Number maximum)
  {
    super(name);
    
    setDataType(dataType) ;
    setScaleType(scaleType) ;
    setMinimumValue(minimum);
    setMaximumValue(maximum);
  }
  
  public ScalePojo(String uniqueIdentifier, String name, DataType dataType, ScaleType scaleType,
      Number minimum, Number maximum)
  {
    super(uniqueIdentifier, name);
    
    setDataType(dataType) ;
    setScaleType(scaleType) ;
    setMinimumValue(minimum);
    setMaximumValue(maximum);
  }
  
  public ScalePojo(String uniqueIdentifier, String name, String description, DataType dataType,
      ScaleType scaleType, Number minimum, Number maximum)
  {
    super(uniqueIdentifier, name, description);
    
    setDataType(dataType) ;
    setScaleType(scaleType) ;
    setMinimumValue(minimum);
    setMaximumValue(maximum);
  }
  
  public ScalePojo(String uniqueIdentifier, DataType dataType,
      ScaleType scaleType, Set<Object> values)
  {
    super(uniqueIdentifier);
    
    setDataType(dataType) ;
    setScaleType(scaleType) ;
    setValues(values) ;
  }
  
  public ScalePojo(String uniqueIdentifier, String name, DataType dataType,
      ScaleType scaleType, Set<Object> values)
  {
    super(uniqueIdentifier, name);
    
    setDataType(dataType) ;
    setScaleType(scaleType) ;
    setValues(values) ;
  }
  
  public ScalePojo(String uniqueIdentifier, String name, String description, DataType dataType,
      ScaleType scaleType, Set<Object> values)
  {
    super(uniqueIdentifier, name, description);
    
    setDataType(dataType) ;
    setScaleType(scaleType) ;
    setValues(values) ;
  }
  
  public ScalePojo(String uniqueIdentifier, DataType dataType,
      ScaleType scaleType, Object[] values)
  {
    super(uniqueIdentifier);
    
    setDataType(dataType) ;
    setScaleType(scaleType) ;
    setValues(values) ;
  }
  
  public ScalePojo(String uniqueIdentifier, String name, DataType dataType,
      ScaleType scaleType, Object[] values)
  {
    super(uniqueIdentifier, name);
    
    setDataType(dataType) ;
    setScaleType(scaleType) ;
    setValues(values) ;
  }
  
  public ScalePojo(String uniqueIdentifier, String name, String description, DataType dataType,
      ScaleType scaleType, Object[] values)
  {
    super(uniqueIdentifier, name, description);
    
    setDataType(dataType) ;
    setScaleType(scaleType) ;
    setValues(values) ;
  }
	
  public ScalePojo(String uniqueIdentifier, DataType dataType,
      ScaleType scaleType, Number minimum, Number maximum, Set<Object> values)
  {
    super(uniqueIdentifier);
    
    setDataType(dataType) ;
    setScaleType(scaleType) ;
    setValues(values) ;
  }
  
  public ScalePojo(String uniqueIdentifier, String name, DataType dataType,
      ScaleType scaleType, Number minimum, Number maximum, Set<Object> values)
  {
    super(uniqueIdentifier, name);
    
    setDataType(dataType) ;
    setScaleType(scaleType) ;
    setMinimumValue(minimum);
    setMaximumValue(maximum);
    setValues(values) ;
  }
  
  public ScalePojo(String uniqueIdentifier, String name, String description, DataType dataType,
      ScaleType scaleType, Number minimum, Number maximum, Set<Object> values)
  {
    super(uniqueIdentifier, name, description);
    
    setDataType(dataType) ;
    setScaleType(scaleType) ;
    setMinimumValue(minimum);
    setMaximumValue(maximum);
    setValues(values) ;
  }
  
  public ScalePojo(String uniqueIdentifier, DataType dataType,
      ScaleType scaleType, Number minimum, Number maximum, Object[] values)
  {
    super(uniqueIdentifier);
    
    setDataType(dataType) ;
    setScaleType(scaleType) ;
    setMinimumValue(minimum);
    setMaximumValue(maximum);
    setValues(values) ;
  }
  
  public ScalePojo(String uniqueIdentifier, String name, DataType dataType,
      ScaleType scaleType, Number minimum, Number maximum, Object[] values)
  {
    super(uniqueIdentifier, name);
    
    setDataType(dataType) ;
    setScaleType(scaleType) ;
    setMinimumValue(minimum);
    setMaximumValue(maximum);
    setValues(values) ;
  }
  
  public ScalePojo(String uniqueIdentifier, String name, String description, DataType dataType,
      ScaleType scaleType, Number minimum, Number maximum, Object[] values)
  {
    super(uniqueIdentifier, name, description);
    
    setDataType(dataType) ;
    setScaleType(scaleType) ;
    setMinimumValue(minimum);
    setMaximumValue(maximum);
    setValues(values) ;
  }
  
	public ScalePojo(Scale scale)
  {
	  super(scale);
	  
	  setDataType(scale.getDataType()) ;
	  setScaleType(scale.getScaleType()) ;
    setMinimumValue(scale.getMinimumValue());
    setMaximumValue(scale.getMaximumValue());
    setValues(scale.getValues()) ;
  }

  /* (non-Javadoc)
	 * @see uno.informatics.common.model.Scale#getDataType()
	 */
  @Override
  public DataType getDataType()
  {
	  return dataType;
  }

  public void setDataType(DataType dataType)
  {
    if (dataType != null)
    {
      DataType oldValue = this.dataType ;
    	
      this.dataType = dataType;
      
      getPropertyChangeSupport().firePropertyChange(
      		DATA_TYPE_PROPERTY, oldValue, this.dataType) ;
    }
  }

	/* (non-Javadoc)
	 * @see uno.informatics.common.model.Scale#getScaleType()
	 */
  @Override
  public ScaleType getScaleType()
  {
	  return scaleType;
  }

  public void setScaleType(ScaleType scaleType)
  {
    if (scaleType != null)
    {
    	ScaleType oldValue = this.scaleType ;
    	
      this.scaleType = scaleType;
      
      getPropertyChangeSupport().firePropertyChange(
      		SCALE_TYPE_PROPERTY, oldValue, this.scaleType) ;
    }
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see uno.informatics.common.model.ContinuousScale#getMaximumValue()
   */
  @Override
  public final Number getMaximumValue()
  {
    return maximumValue;
  }
  
  public final void setMaximumValue(Number maximumValue)
  {
    if (maximumValue != null && scaleType == ScaleType.NOMINAL)
      throw new IllegalArgumentException("Maximum Value can not be set if the scale type is Nominal") ;
    
    Number oldValue = this.maximumValue;
    
    this.maximumValue = maximumValue;
    
    getPropertyChangeSupport().firePropertyChange(MAXIMUM_VALUE_PROPERTY, oldValue, this.maximumValue);
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see uno.informatics.common.model.ContinuousScale#getMinimumValue()
   */
  @Override
  public final Number getMinimumValue()
  {
    return minimumValue;
  }
  
  public final void setMinimumValue(Number minimumValue)
  {
    if (minimumValue != null && scaleType == ScaleType.NOMINAL)
      throw new IllegalArgumentException("Minimum Value can not be set if the scale type is Nominal") ;
    
    Number oldValue = this.minimumValue;
    
    this.minimumValue = minimumValue;
    
    getPropertyChangeSupport().firePropertyChange(MINIMUM_VALUE_PROPERTY, oldValue, this.minimumValue);
  }
  
  /* (non-Javadoc)
   * @see uno.informatics.common.model.DiscreteScale#getValues()
   */
  @Override
  public final Set<Object> getValues()
  {
    return values;
  }
  
  public final void setValues(Set<Object> values)
  {
    if (values != null && scaleType == ScaleType.RATIO)
      throw new IllegalArgumentException("Values can not be set if the scale type is Ratio") ;
   
    Set<Object> oldValue = this.values ;
    
    this.values = createSet(values) ;
    
    getPropertyChangeSupport().firePropertyChange(
        VALUES_PROPERTY, oldValue, this.values) ;
  }
  
  public final void setValues(Object[] values)
  {
    if (values != null && scaleType == ScaleType.RATIO)
      throw new IllegalArgumentException("Values can not be set if the scale type is Ratio") ;
   
    Set<Object> oldValue = this.values ;
    
    this.values = createSet(values) ;
    
    getPropertyChangeSupport().firePropertyChange(
        VALUES_PROPERTY, oldValue, this.values) ;
  }
  
  public void addValue(Object value)
  {
    if (values == null)
      values = createSet() ;
    
    values.add(value) ;
  }
  
  protected Set<Object> createSet(Set<Object> values)
  {
    if (values != null)
      return new TreeSet<Object>(values) ;
    else
      return new TreeSet<Object>() ;
  }
  
  protected Set<Object> createSet(Object[] values)
  {
    Set<Object> set = new TreeSet<Object>() ;
    
    if (values != null)
      for (int i = 0 ; i < values.length ; ++i)
        set.add(values[i]) ;
    
    return set ;
  }
  
  protected Set<Object> createSet()
  {
    return new TreeSet<Object>() ;
  }
}
