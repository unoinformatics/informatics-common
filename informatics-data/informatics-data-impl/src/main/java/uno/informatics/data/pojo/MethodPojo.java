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

import uno.informatics.data.Method;
import uno.informatics.data.Scale;

/**
 * @author Guy Davenport
 *
 */
public class MethodPojo extends EntityPojo implements Method
{
  private ScalePojo scale;
  
	public MethodPojo(String name, ScalePojo Scale)
  {
	  super(name);
	  
	  setScale(Scale) ;
  }
	
	public MethodPojo(String uniqueIdentifier, String name, ScalePojo Scale)
  {
	  super(uniqueIdentifier, name);
	  
	  setScale(Scale) ;
  }
	
	public MethodPojo(String uniqueIdentifier, String name, String description, ScalePojo Scale)
  {
	  super(uniqueIdentifier, name, description);
	  
	  setScale(Scale) ;
  }
	
	public MethodPojo(Method method)
  {
	  super(method);
	  
	  setScale(new ScalePojo(method.getScale())) ;
  }
	
	 /* (non-Javadoc)
   * @see uno.informatics.common.model.Feature#getScale()
   */
  @Override
  public Scale getScale()
  {
    return scale;
  }

  public void setScale(ScalePojo scale)
  {
    this.scale = scale ;
  }
}
