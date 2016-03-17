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

    private OntologyTerm type;

    public EntityPojo(String name) {
        this(null, name, null);
    }

    public EntityPojo(String uniqueIdentifier, String name) {
        this(uniqueIdentifier, name, null);
    }

    public EntityPojo(String uniqueIdentifier, String name, String description) {
        super(uniqueIdentifier, name);

        setDescription(description);
    }

    public EntityPojo(Entity identifier) {
        super(identifier);

        setDescription(identifier.getDescription());
        setAbbreviation(identifier.getAbbreviation());
        setType(identifier.getType());
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.data.Identifier#getAbbreviation()
     */
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

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.common.model.Identifier#getAbbreviation()
     */
    @Override
    public final String getAbbreviation() {
        return abbreviation;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.data.Identifier#setAbbreviation(java.lang.String)
     */
    public final void setAbbreviation(String abbreviation) {
        String oldValue = this.abbreviation;

        this.abbreviation = abbreviation;

        getPropertyChangeSupport().firePropertyChange(ABBREVIATION_PROPERTY, oldValue, this.abbreviation);
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.data.Identifier#getType()
     */
    @Override
    public final OntologyTerm getType() {
        // TODO Auto-generated method stub
        return null;
    }

    public final void setType(OntologyTerm type) {
        OntologyTerm oldValue = this.type;

        this.type = type;

        getPropertyChangeSupport().firePropertyChange(TYPE_PROPERTY, oldValue, this.type);
    }
}
