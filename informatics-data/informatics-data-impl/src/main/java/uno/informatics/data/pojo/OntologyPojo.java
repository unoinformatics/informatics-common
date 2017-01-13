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

public class OntologyPojo extends EntityPojo implements Ontology {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public OntologyPojo(String name) {
        super(name);
    }

    public OntologyPojo(String uniqueIdentifier, String name) {
        super(uniqueIdentifier, name);
    }

    public OntologyPojo(String uniqueIdentifier, String name, String description) {
        super(uniqueIdentifier, name, description);
    }

    public OntologyPojo(Ontology identifier) {
        super(identifier);
    }
}
