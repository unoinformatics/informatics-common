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

package uno.informatics.common.io;

import java.io.IOException;
import java.util.List;

/**
 * Reads a table from a data source row by row.
 * 
 * @author Guy Davenport
 */
public interface RowReader extends TableReader {
    /**
     * Gets the current remaining unread row cell values
     * 
     * @return the remaining unread row cell values
     * @throws IOException
     *             if the data source can not be read
     */
    public List<Object> getRowCells() throws IOException;

    /**
     * Gets the current remaining unread row cell values
     * 
     * @return the remaining unread row cell values
     * @throws IOException
     *             if the data source can not be read
     */
    public List<String> getRowCellsAsString() throws IOException;

    /**
     * Gets the current remaining unread row cell values
     * 
     * @return the remaining unread row cell values
     * @throws IOException
     *             if the data source can not be read
     */
    public List<Integer> getRowCellsAsInt() throws IOException;

    /**
     * Gets the current remaining unread row cell values
     * 
     * @return the remaining unread row cell values
     * @throws IOException
     *             if the data source can not be read
     */
    public List<Double> getRowCellsAsDouble() throws IOException;

    /**
     * Gets the current remaining unread row cell values
     * 
     * @return the remaining unread row cell values
     * @throws IOException
     *             if the data source can not be read
     */
    public List<Boolean> getRowCellsAsBoolean() throws IOException;

    /**
     * Gets the current remaining unread row cell values as an array
     * 
     * @return the remaining unread row cell values as an array
     * @throws IOException
     *             if the data source can not be read
     */
    public Object[] getRowCellsAsArray() throws IOException;

    /**
     * Gets the current remaining unread row cell values as an array
     * 
     * @return the remaining unread row cell values as an array
     * @throws IOException
     *             if the data source can not be read
     */
    public String[] getRowCellsAsStringArray() throws IOException;

    /**
     * Gets the current remaining unread row cell values as an array
     * 
     * @return the remaining unread row cell values as an array
     * @throws IOException
     *             if the data source can not be read
     */
    public int[] getRowCellsAsIntArray() throws IOException;

    /**
     * Gets the current remaining unread row cell values as an array
     * 
     * @return the remaining unread row cell values as an array
     * @throws IOException
     *             if the data source can not be read
     */
    public double[] getRowCellsAsDoubleArray() throws IOException;

    /**
     * Gets the current remaining unread row cell values as an array
     * 
     * @return the remaining unread row cell values as an array
     * @throws IOException
     *             if the data source can not be read
     */
    public boolean[] getRowCellsAsBooleanArray() throws IOException;

}