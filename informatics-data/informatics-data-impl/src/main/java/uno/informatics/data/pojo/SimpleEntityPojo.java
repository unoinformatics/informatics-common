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

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import uno.informatics.data.SimpleEntity;

public class SimpleEntityPojo extends PropertyHandler implements SimpleEntity {
    public static final String UNIQUE_IDENTIFIER_PROPERTY = SimpleEntity.class.getName() + ".unuqueIdentifier";
    public static final String NAME_PROPERTY = SimpleEntity.class.getName() + ".name";

    private String uniqueIdentifier;

    private String name;

    public SimpleEntityPojo(String uniqueIdentifier) {
        this(uniqueIdentifier, uniqueIdentifier);
    }

    public SimpleEntityPojo(String uniqueIdentifier, String name) {
        super();
        initialise();

        setUniqueIdentifier(uniqueIdentifier);
        setName(name);
    }

    public SimpleEntityPojo(SimpleEntity simpleEntity) {
        super();
        initialise();

        if (simpleEntity != null) {
            setUniqueIdentifier(simpleEntity.getUniqueIdentifier());
            setName(simpleEntity.getName());
        } else {
            throw new NullPointerException("Simple Entity is not optional!");
        }
    }

    @Override
    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.common.model.Identifier#getAbbreviation()
     */
    public final String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    /**
     * Sets the unique identifier of the entity being identified.
     * <p>
     * 
     * @param uniqueIdentifier
     *            the unique identifier to be set
     */
    public final void setUniqueIdentifier(String uniqueIdentifier) {
        String oldValue = this.uniqueIdentifier;

        this.uniqueIdentifier = uniqueIdentifier;

        getPropertyChangeSupport().firePropertyChange(UNIQUE_IDENTIFIER_PROPERTY, oldValue, this.uniqueIdentifier);
    }

    @Override
    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.common.model.Entity#getName()
     */
    public final String getName() {
        return name;
    }

    /**
     * Sets a (usually more) human readable name of the entity being identified.
     * This name could be used in GUIs, etc.
     * 
     * @param name
     *            the name to be set
     */
    public final void setName(String name) {
        String oldValue = this.name;

        this.name = name;

        getPropertyChangeSupport().firePropertyChange(NAME_PROPERTY, oldValue, this.name);
    }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + Objects.hashCode(this.uniqueIdentifier);
    return result;
  }

  @Override
  public String toString()
  {
    return ToStringBuilder.reflectionToString(this) ;
  }
  
    @Override
    public boolean equals(Object object) {
        
        if (object == null) {
            return false;
        }
        
        if (object == this) {
            return true;
        }

        if (object instanceof SimpleEntity) {
            SimpleEntity se = (SimpleEntity) object;
            return getUniqueIdentifier() != null && se.getUniqueIdentifier() != null
                   && StringUtils.equals(se.getUniqueIdentifier(), this.getUniqueIdentifier());
        }
        
        return false;
        
    }
  
  protected void initialise()
  {
    uniqueIdentifier = null ;
    name = null;
  }
}
