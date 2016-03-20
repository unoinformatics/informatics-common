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

import uno.informatics.data.Data;
import uno.informatics.data.Feature;
import uno.informatics.data.SimpleEntity;

/**
 * API for datasets that consist of a 2-dimensional matrix of elements of a
 * single type. The efficiency of each of the methods depends highly on the
 * implementation, which may be Array based, List based, or a wrapper around
 * another type of storage. The methods {@link #getRowCount()} and
 * {@link #getColumnCount()} should be efficient and give a good indication of
 * the size of the dataset. If the row count and or column count is high use
 * {@link #getValue(int, int)} to subset the dataset.
 * 
 * @author Guy Davenport
 *
 */
public interface MatrixData<ValueType extends Object> extends Data {
    /**
     * Gets all the values in the matrix as a list of lists
     * 
     * @return all the values in the matrix as a list of lists
     */
    public List<List<ValueType>> getValues();

    /**
     * Gets all values as 2-dimension array
     * 
     * @return all values as 2-dimension array
     */
    public ValueType[][] getValuesAsArray();

    /**
     * Gets the value in the matrix at a given row and column
     * 
     * @param rowIndex
     *            the row index of the required value
     * @param columnIndex
     *            the column index of the required value
     * @return the value in the matrix at a given row index and column index
     */
    public ValueType getValue(int rowIndex, int columnIndex);

    /**
     * Gets the row dimension size of the matrix
     * 
     * @return the number of rows in the matrix
     */
    public int getRowCount();

    /**
     * Gets the column dimension size of the matrix
     * 
     * @return the number of columns in the matrix
     */
    public int getColumnCount();

    /**
     * Determines if the matrix has row headers
     * 
     * @return <code>true</code> if the matrix has row headers,
     *         <code>false</code> otherwise
     */
    public boolean hasRowHeaders();

    /**
     * Gets a list of the row headers for the matrix
     * 
     * @return a list of the row headers
     */
    public List<SimpleEntity> getRowHeaders();

    /**
     * Gets an array of the row headers for the matrix
     * 
     * @return an array of the row headers
     */
    public SimpleEntity[] getRowHeadersAsArray();

    /**
     * Gets the row header at a given row
     * 
     * @param rowIndex
     *            the row index of the required value
     * @return the row header at a given row index
     */
    public SimpleEntity getRowHeader(int rowIndex);

    /**
     * Determines if the matrix has column headers
     * 
     * @return <code>true</code> if the matrix has column headers,
     *         <code>false</code> otherwise
     */
    public boolean hasColumnHeaders();

    /**
     * Gets a list of the column headers for the matrix
     * 
     * @return a list of the column headers
     */
    public List<SimpleEntity> getColumnHeaders();

    /**
     * Gets an array of the column headers for the matrix
     * 
     * @return an array of the column headers
     */
    public SimpleEntity[] getColumnHeadersAsArray();

    /**
     * Gets the column header at a given row
     * 
     * @param columnIndex
     *            the column index of the required value
     * @return the column header at a given column index
     */
    public SimpleEntity getColumnHeader(int columnIndex);

    /**
     * Gets the feature that describes the values in the matrix
     * 
     * @return the feature that describes the values in the matrix
     */
    public Feature getValueFeature();

}
