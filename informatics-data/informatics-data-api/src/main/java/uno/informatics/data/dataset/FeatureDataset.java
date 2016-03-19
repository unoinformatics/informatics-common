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

package uno.informatics.data.dataset;

import java.util.List;

import uno.informatics.data.Dataset;
import uno.informatics.data.Feature;

/**
 * A dataset that consists of columns recorded for a specific feature, with each
 * row providing one value for each feature. The efficiency of each of the
 * methods depends highly on the implementation, which may be Array based, List
 * based, or a wrapper around another type of storage. The method
 * {@link #getRowCount()} should be efficient and give a good indication of the
 * size of the dataset. If the row count is high use {@link #getRow(int)} to
 * subset the dataset.
 * 
 * @author Guy Davenport
 *
 */
public interface FeatureDataset extends Dataset {
    /**
     * Gets the features in the dataset as a list
     * @return a list of features in the database
     */
    public List<Feature> getFeatures();

    /**
     * Gets the features in the dataset as an array
     * @return an array of features in the database
     */
    public Feature[] getFeaturesAsArray();

    /**
     * Gets all the values in the database as a list of lists
     * @return a list of list of the values in the database
     */
    public List<List<Object>> getValues();

    /**
     * Gets all the values in the database as a 2-dimensional array
     * @return a 2-dimensional array of the values in the database
     */
    public Object[][] getValuesAsArray();

    /**
     * Gets the number of rows in the dataset
     * @return the number of rows in the dataset
     */
    public int getRowCount();
    
    /**
     * Determines if the dataset has row headers
     * @return <code>true</code> if the dataset has row headers, <code>false</code> otherwise 
     */
    public boolean hasRowHeaders();

    /**
     * Gets the row at a specific position
     * @param rowIndex the index of the row required
     * @return the row at the given position
     */
    public FeatureDatasetRow getRow(int rowIndex);

    /**
     * Gets all the rows in the dataset as a list
     * @return a list of all the rows in the dataset
     */
    public List<FeatureDatasetRow> getRows();
    
    /**
     * Gets all the rows in the dataset as an array
     * @return an array of all the rows in the dataset
     */
    public FeatureDatasetRow[] getRowsAsArray();

}
