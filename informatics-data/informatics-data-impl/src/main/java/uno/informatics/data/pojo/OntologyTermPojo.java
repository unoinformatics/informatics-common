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

import uno.informatics.data.Ontology;
import uno.informatics.data.OntologyTerm;

public class OntologyTermPojo extends EntityPojo implements OntologyTerm {
    private static final String ONTOLOGY_PROPERTY =  OntologyTerm.class.getName() + ".onology";

    private OntologyPojo ontology;

    public OntologyTermPojo(String name) {
        super(name);
    }

    public OntologyTermPojo(String uniqueIdentifier, String name) {
        super(uniqueIdentifier, name);
    }

    public OntologyTermPojo(String uniqueIdentifier, String name, String description) {
        super(uniqueIdentifier, name, description);
    }

    public OntologyTermPojo(OntologyTerm ontologyTerm) {
        super(ontologyTerm);
        
        setOntology(ontologyTerm.getOntology());
    }

    @Override
    public Ontology getOntology() {
        return ontology;
    }

    public final void setOntology(Ontology ontology) {
        
        if (ontology == null)
            throw new IllegalArgumentException("Ontology is not optional for ontology term!") ; 
        
        OntologyPojo oldValue = this.ontology;
        this.ontology = new OntologyPojo(ontology) ;
        
        getPropertyChangeSupport().firePropertyChange(ONTOLOGY_PROPERTY, oldValue, this.ontology);
    } 
}
