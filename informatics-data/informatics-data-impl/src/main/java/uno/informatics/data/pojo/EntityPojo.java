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

import uno.informatics.data.Entity;
import uno.informatics.data.OntologyTerm;
import uno.informatics.data.SimpleEntity;

/**
 * @author Guy Davenport
 *
 */
public class EntityPojo extends SimpleEntityPojo implements Entity {
    public static final String ABBREVIATION_PROPERTY = SimpleEntity.class.getName() + ".abbreviation";

    public static final String TYPE_PROPERTY = SimpleEntity.class.getName() + ".type";

    public static final String DESCRIPTION_PROPERTY = SimpleEntity.class.getName() + ".description";

    private String description;

    private String abbreviation;

    private OntologyTermPojo type;

    public EntityPojo(String uniqueIdentifier) {
        this(uniqueIdentifier, uniqueIdentifier, null);
    }

    public EntityPojo(String uniqueIdentifier, String name) {
        this(uniqueIdentifier, name, null);
    }

    public EntityPojo(String uniqueIdentifier, String name, String description) {
        super(uniqueIdentifier, name);

        setDescription(description);
    }

    public EntityPojo(Entity entity) {
        super(entity);

        if (entity == null)
            throw new IllegalArgumentException("Entity is not optional!") ;
        
        setDescription(entity.getDescription());
        setAbbreviation(entity.getAbbreviation());
        setType(entity.getType());
    }

    @Override
    public final String getDescription() {
        return description;
    }

    /**
     * Sets the optional description of what the entity is or does. For example
     * the the description could be used by the application to provide
     * information to user to allow to know what the entity is or what is does.
     * 
     * @param description
     *            the description to be set
     */
    public final void setDescription(String description) {
        String oldValue = this.description;

        this.description = description;

        getPropertyChangeSupport().firePropertyChange(DESCRIPTION_PROPERTY, oldValue, this.description);
    }

    @Override
    public final String getAbbreviation() {
        return abbreviation;
    }

    /**
     * Sets the optional short abbreviation that can be used in place of the name.
     * 
     * @param abbreviation
     *            the abbreviation to be set
     */
    public final void setAbbreviation(String abbreviation) {
        String oldValue = this.abbreviation;

        this.abbreviation = abbreviation;

        getPropertyChangeSupport().firePropertyChange(ABBREVIATION_PROPERTY, oldValue, this.abbreviation);
    }
    
    @Override
    public final OntologyTerm getType() {
        return type;
    }

    /**
     * Sets the optional type of the entity, which is a term with an ontology
     * 
     * @param type
     *            the type of the entity
     */
    public final void setType(OntologyTerm type) {
        OntologyTermPojo oldValue = this.type;

        if (type != null)
            this.type = new OntologyTermPojo(type) ;
        else
            this.type = null ;

        getPropertyChangeSupport().firePropertyChange(TYPE_PROPERTY, oldValue, this.type);
    }
}
