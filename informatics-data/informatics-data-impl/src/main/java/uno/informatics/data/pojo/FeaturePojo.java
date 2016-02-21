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
package uno.informatics.data.pojo;

import uno.informatics.data.Feature;
import uno.informatics.data.Method;

/**
 * @author Guy Davenport
 *        
 */
public class FeaturePojo extends EntityPojo implements Feature
{
  private Method method;
  
  public FeaturePojo(String name)
  {
    super(name);
  }
  
  public FeaturePojo(String uniqueIdentifier, String name)
  {
    super(uniqueIdentifier, name);
  }
  
  public FeaturePojo(String uniqueIdentifier, String name, String description)
  {
    super(uniqueIdentifier, name, description);
  }
  
  public FeaturePojo(String name, MethodPojo method)
  {
    super(name);
    
    setMethod(method);
  }
  
  public FeaturePojo(String uniqueIdentifier, String name, MethodPojo method)
  {
    super(uniqueIdentifier, name);
    
    setMethod(method);
  }
  
  public FeaturePojo(String uniqueIdentifier, String name, String description, MethodPojo method)
  {
    super(uniqueIdentifier, name, description);
    
    setMethod(method);
  }
  
  public FeaturePojo(Feature feature)
  {
    super(feature);
    
    if (feature.getMethod() != null)
      setMethod(new MethodPojo(feature.getMethod()));
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see uno.informatics.common.model.Feature#getMethod()
   */
  @Override
  public Method getMethod()
  {
    return method;
  }
  
  public void setMethod(Method method)
  {
    this.method = method;
  }
}
