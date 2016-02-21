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
package uno.informatics.data;

public interface Entity extends SimpleEntity
{  

  public String getDescription();
  
  /**
   * Gets a short abbreviation that can be used in place of the name. 
   * @return a short abbreviation that can be used in place of the name, 
   * or <code>null</code> if no abbreviation is available
   */
  public String getAbbreviation();

  /**
   * Gets the type of this entity as defined in a specific ontology. 
   *  
   * @return the type of this entity, null if the type has no defined, 
   * or <code>null</code> if the type is not defined is available
   */
  public OntologyTerm getType();


}
