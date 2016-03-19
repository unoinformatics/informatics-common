
package uno.informatics.data.pojo;

import uno.informatics.data.Dataset;
import uno.informatics.data.Entity;
import uno.informatics.data.Study;

public class DatasetPojo extends EntityPojo implements Dataset {

    private Study study;

    public DatasetPojo(String name) {
        super(name);
    }

    public DatasetPojo(String uniqueIdentifier, String name) {
        super(uniqueIdentifier, name);
    }

    public DatasetPojo(String uniqueIdentifier, String name, String description) {
        super(uniqueIdentifier, name, description);
    }

    public DatasetPojo(Entity identifier) {
        super(identifier);
    }

    @Override
    public Study getStudy() {
        return study;
    }

    public final void setStudy(Study study) {
        // TODO property event
        this.study = study;
    }
}
