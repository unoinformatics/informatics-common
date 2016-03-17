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

import java.util.List;

/**
 * @author Guy Davenport
 *
 */
public interface FeatureDatasetRow {
    /**
     * Gets all the values in the Row as a list
     * @return all the values in the Row
     */
    public List<Object> getValues();
    
    /**
     * Gets all the values in the Row as an array
     * @return all the values in the Row
     */
    public Object[] getValuesAsArray();

    /**
     * Gets a value in the row at a specific column
     * @param columnIndex 
     * @return the value in the row at the give column
     */
    public Object getValue(int columnIndex);

    /**
     * Gets the size of the row
     * @return the number values in the row
     */
    public int getColumnCount();

    /**
     * Get the header for the row, or <code>null</code> if the row has no header
     * @return the header for the row
     */
    public SimpleEntity getHeader();
}
