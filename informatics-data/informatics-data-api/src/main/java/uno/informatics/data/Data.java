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

import java.util.Set;

public interface Data extends SimpleEntity {
    /**
     * Gets the dataset to which this data belongs
     * 
     * @return the dataset to which this data belongs
     */
    public Dataset getDataset();
    
    /**
     * Get the header of an entry by index.
     *
     * @param index of an entry, should be within the range from 0 to n-1, where n is the number of
     *              entries as returned by {@link #getSize()}
     * @return the header of an entry by index
     */
    public SimpleEntity getHeader(int index);
               
    /**
     * Get the number of entries in the data.
     * 
     * @return data size
     */
    public int getSize();

    /**
     * Get the set of all integer IDs corresponding to an item in the data set.
     * 
     * @return set of all IDs
     */
    Set<Integer> getIDs();
}
