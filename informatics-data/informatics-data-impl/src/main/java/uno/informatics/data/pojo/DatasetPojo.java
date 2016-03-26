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

import uno.informatics.data.Dataset;
import uno.informatics.data.Study;

public class DatasetPojo extends EntityPojo implements Dataset {

    public static final String STUDY_PROPERTY = Dataset.class.getName() + ".study";
    
    private StudyPojo study;

    public DatasetPojo(String name) {
        super(name);
    }

    public DatasetPojo(String uniqueIdentifier, String name) {
        super(uniqueIdentifier, name);
    }

    public DatasetPojo(String uniqueIdentifier, String name, String description) {
        super(uniqueIdentifier, name, description);
    }

    public DatasetPojo(Dataset dataset) {
        super(dataset);
        
        if (dataset != null)
            setStudy(dataset.getStudy());
        else
            throw new IllegalArgumentException("Data is not optional!") ;
    }

    @Override
    public Study getStudy() {
        return study;
    }

    /**
     * Sets the optional study to which this dataset belongs
     * 
     * @param study
     */
    public final void setStudy(Study study) {

        StudyPojo oldValue = this.study;
        
        if (study != null)
            this.study = new StudyPojo(study) ;
        else
            this.study = null ;
        
        getPropertyChangeSupport().firePropertyChange(STUDY_PROPERTY, oldValue, this.study);
    }
}
