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

/**
 * Reads a table from a data source
 */
import java.io.IOException;
import java.util.List;

public interface TableReader extends Reader {
    /**
     * Determines if there is another row in the current data source
     * 
     * @return <code>true</code> if this is not the last cell in the row,
     *         <code>false</code> if it is the last
     */
    public boolean hasNextRow();

    /**
     * Moves the reader on to the next row in the data source. If row by row and
     * there are any unread cells in the current row these will be unread
     * 
     * @return <code>true</code> if this is not the last cell in the row,
     *         <code>false</code> if it is the last
     * @throws IOException
     *             if the data source can not be read
     */
    public boolean nextRow() throws IOException;

    /**
     * Determines if there another cell in the current column
     * 
     * @return <code>true</code> if this is not the last cell in the column,
     *         <code>false</code> if it is the last
     */
    public boolean hasNextColumn();

    /**
     * Moves the reader on to the next cell in the column. If reading column by
     * column and there are any unread cells in the current column these will be
     * unread
     * 
     * @return <code>true</code> if this is not the last cell in the column,
     *         <code>false</code> if it is the last
     * @throws IOException
     *             if the data source can not be read
     */
    public boolean nextColumn() throws IOException;

    /**
     * Gets the value of the current cell
     * 
     * @return the value of the current cell
     * @throws IOException
     *             if the data source can not be read
     */
    public Object getCell() throws IOException;

    /**
     * Gets the value of the current cell as a string
     * 
     * @return the value of the current cell
     * @throws IOException
     *             if the data source can not be read or can not be converted to
     *             String
     */
    public String getCellAsString() throws IOException;

    /**
     * Gets the value of the current cell as a double
     * 
     * @return the value of the current cell
     * @throws IOException
     *             if the data source can not be read or can not be converted to
     *             double
     */
    public double getCellAsDouble() throws IOException;

    /**
     * Gets the value of the current cell as an int
     * 
     * @return the value of the current cell
     * @throws IOException
     *             if the data source can not be read or can not be converted to
     *             an int
     */
    public int getCellAsInt() throws IOException;

    /**
     * Gets the value of the current cell as an boolean
     * 
     * @return the value of the current cell
     * @throws IOException
     *             if the data source can not be read or can not be converted to
     *             an boolean
     */
    public boolean getCellAsBoolean() throws IOException;

    /**
     * Read all cells from the data source at one time
     * 
     * @return all cells from the data source at one time
     * @throws IOException
     *             if the data source can not be read
     */
    public List<List<Object>> readCells() throws IOException;

    /**
     * Read all cells from the data source at one time
     * 
     * @return all cells from the data source at one time
     * @throws IOException
     *             if the data source can not be read
     */
    public Object[][] readCellsAsArray() throws IOException;

    public void setAllConversionTypes(int[] conversionTypes);
}