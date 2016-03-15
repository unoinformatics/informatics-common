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

public enum ScaleType
{
  NONE("None", "X", DataType.UNKNOWN),
  NOMINAL("Nominal", "N", DataType.STRING),
  ORDINAL("Ordinal", "O", DataType.INTEGER),
  INTERVAL("Interval", "I", DataType.INTEGER),
  RATIO("Ratio", "R", DataType.DOUBLE);
  
  private String   name;
  private String   abbreviation;
  private DataType defaultEncoding;
                                   
  private static final ScaleType[] allTypes = new ScaleType[] { NONE, NOMINAL, ORDINAL, INTERVAL, RATIO };
                                            
  private ScaleType(String name, String abbreviation, DataType defaultEncoding)
  {
    this.name = name;
    this.abbreviation = abbreviation;
    this.defaultEncoding = defaultEncoding;
  }
  
  private ScaleType(String name)
  {
    this.name = name;
  }
  
  public String getName()
  {
    return name;
  }
  
  public String getAbbreviation()
  {
    return abbreviation;
  }
  
  public DataType getDefaultEncoding(){
      return defaultEncoding;
  }
  
  public static final ScaleType[] getAllTypes()
  {
    return allTypes;
  }
  
  public static final ScaleType getTypeByName(String name)
  {
    int i = 0;
    ScaleType scaleType = null;
    
    while (i < allTypes.length)
    { 
      if (allTypes[i].getName().equals(name))
        scaleType = allTypes[i];
      
      ++i;
    }
    
    return scaleType;
  }
  
  public static final ScaleType getTypeByAbbreviation(String abbreviation)
  {
    int i = 0;
    ScaleType scaleType = null;
    
    while (i < allTypes.length)
    {
      if (allTypes[i].getAbbreviation().equals(abbreviation))
        scaleType = allTypes[i];
      
      ++i;
    }
    
    return scaleType;
  }
}
