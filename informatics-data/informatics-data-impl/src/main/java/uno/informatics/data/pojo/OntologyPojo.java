
package uno.informatics.data.pojo;

import uno.informatics.data.Entity;
import uno.informatics.data.Ontology;

public class OntologyPojo extends EntityPojo implements Ontology {

    public OntologyPojo(String name) {
        super(name);
    }

    public OntologyPojo(String uniqueIdentifier, String name) {
        super(uniqueIdentifier, name);
    }

    public OntologyPojo(String uniqueIdentifier, String name, String description) {
        super(uniqueIdentifier, name, description);
    }

    public OntologyPojo(Entity identifier) {
        super(identifier);
    }
}
