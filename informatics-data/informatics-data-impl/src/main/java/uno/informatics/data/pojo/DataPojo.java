
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

import uno.informatics.data.Data;
import uno.informatics.data.Dataset;
import uno.informatics.data.SimpleEntity;

public class DataPojo extends SimpleEntityPojo implements Data {

    public static final String DATASET_PROPERTY = Data.class.getName() + ".dataset";
    
    private DatasetPojo dataset;
    
    public DataPojo(String name) {
        super(name);
    }

    public DataPojo(String uniqueIdentifier, String name) {
        super(uniqueIdentifier, name);
    }

    public DataPojo(Data data) {
        super(data);
        
        if (data != null)
            setDataset(data.getDataset());
        else
            throw new IllegalArgumentException("Data is not optional!") ;
    }

    @Override
    public Dataset getDataset() {
        return dataset;
    }

    public final void setDataset(Dataset dataset) {
        
        DatasetPojo oldValue = this.dataset;
        
        if (dataset != null)
            this.dataset = new DatasetPojo(dataset) ;
        else
            this.dataset = null ;
        
        getPropertyChangeSupport().firePropertyChange(DATASET_PROPERTY, oldValue, this.dataset);
    }

}
