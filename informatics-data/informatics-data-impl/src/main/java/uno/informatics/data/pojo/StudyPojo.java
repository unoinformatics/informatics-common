package uno.informatics.data.pojo;

import org.joda.time.DateTime;

import uno.informatics.data.Study;

public class StudyPojo extends EntityPojo implements Study {

    private DateTime startDate;
    private DateTime endDate;

    public StudyPojo(String name) {
        super(name);
    }

    public StudyPojo(String uniqueIdentifier, String name) {
        super(uniqueIdentifier, name);
    }

    public StudyPojo(Study study) {
        super(study);
        
        
    }
    
    @Override
    public DateTime getStartDate() {

        return startDate;
    }

    @Override
    public DateTime getEndDate() {

        return endDate;
    }

}
