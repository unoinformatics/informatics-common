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
 *******************************************************************************/

package uno.informatics.data;

public interface Dataset extends Entity {
    /**
     * Gets the study to which this dataset belongs
     * 
     * @return the study to which this dataset belongs
     */
    public Study getStudy();
    
    /**
     * Gets the size of the data, which is the size of the 
     * set that is the union all the associated data object.
     * If there is no data object associated with this dataset
     * the size is zero. 
     * For single datasets containing a single data object,
     * the size of the dataset is the same of as the size of this
     * data object.
     * 
     * @return the study to which this dataset belongs
     */
    public int getSize();

}
