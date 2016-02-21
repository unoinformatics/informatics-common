package uno.informatics.data.pojo;

import java.util.Set;

import uno.informatics.data.DataType;
import uno.informatics.data.Method;
import uno.informatics.data.ScaleType;
import uno.informatics.data.feature.SimpleFeature;

public class SimpleFeaturePojo extends EntityPojo implements SimpleFeature
{
  private Method method;

  public SimpleFeaturePojo(String name, DataType dataType, ScaleType scaleType)
  {
    super(name);
    
    method = createMethod(name, dataType, scaleType) ;
  }

  public SimpleFeaturePojo(String uniqueIdentifier, String name, DataType dataType, ScaleType scaleType)
  {
    super(uniqueIdentifier, name);
    
    method = createMethod(name, dataType, scaleType) ;
  }
  
  public SimpleFeaturePojo(String uniqueIdentifier, String name, String description, DataType dataType, ScaleType scaleType)
  {
    super(uniqueIdentifier, name, description);
    
    method = createMethod(name, dataType, scaleType) ;
  }
  
  public SimpleFeaturePojo(String name, DataType dataType, ScaleType scaleType, Number minimumValue, Number maximumValue)
  {
    super(name);
    
    method = createMethod(name, dataType, scaleType, minimumValue, maximumValue) ;
  }

  public SimpleFeaturePojo(String uniqueIdentifier, String name, DataType dataType, ScaleType scaleType, Number minimumValue, Number maximumValue)
  {
    super(uniqueIdentifier, name);
    
    method = createMethod(name, dataType, scaleType, minimumValue, maximumValue) ;
  }
  
  public SimpleFeaturePojo(String uniqueIdentifier, String name, String description, DataType dataType, ScaleType scaleType, Number minimumValue, Number maximumValue)
  {
    super(uniqueIdentifier, name, description);
    
    method = createMethod(name, dataType, scaleType, minimumValue, maximumValue) ;
  }
  
  public SimpleFeaturePojo(String name, DataType dataType, ScaleType scaleType, Set<Object> values)
  {
    super(name);
    
    method = createMethod(name, dataType, scaleType, values) ;
  }

  public SimpleFeaturePojo(String uniqueIdentifier, String name, DataType dataType, ScaleType scaleType, Set<Object> values)
  {
    super(uniqueIdentifier, name);
    
    method = createMethod(name, dataType, scaleType, values) ;
  }
  
  public SimpleFeaturePojo(String uniqueIdentifier, String name, String description, DataType dataType, ScaleType scaleType, Set<Object> values)
  {
    super(uniqueIdentifier, name, description);
    
    method = createMethod(name, dataType, scaleType, values) ;
  }

  @Override
  public Method getMethod()
  {
    return method ;
  }

  private Method createMethod(String name, DataType dataType, ScaleType scaleType)
  {
    ScalePojo scale = new ScalePojo(name, dataType, scaleType) ;
    
    MethodPojo method = new MethodPojo(name, scale) ;

    return method;
  }

  private Method createMethod(String name, DataType dataType, ScaleType scaleType, Number minimumValue, Number maximumValue)
  {
    ScalePojo scale = new ScalePojo(name, dataType, scaleType, minimumValue, maximumValue) ;
    
    MethodPojo method = new MethodPojo(name, scale) ;

    return method;
  }
  
  private Method createMethod(String name, DataType dataType, ScaleType scaleType, Set<Object> values)
  {
    ScalePojo scale = new ScalePojo(name, dataType, scaleType, values) ;
    
    MethodPojo method = new MethodPojo(name, scale) ;

    return method;
  }
  
  @Override
  public DataType getDataType()
  {
    return method.getScale().getDataType();
  }

  @Override
  public ScaleType getScaleType()
  {
    return method.getScale().getScaleType();
  }
}
