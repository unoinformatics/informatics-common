package uno.informatics.data.pojo;

import java.time.LocalDate;

import uno.informatics.data.Study;

public class StudyPojo extends EntityPojo implements Study {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private LocalDate startDate;
    private LocalDate endDate;

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
    public LocalDate getStartDate() {

        return startDate;
    }

    @Override
    public LocalDate getEndDate() {

        return endDate;
    }

}
