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
 * Writes to a data source in one step
 * @author Guy Davenport
 */
public interface TableWriter extends Reader
{
	/**
	 * Creates a new row in the data source
	 * @return <code>true</code> if the row was created, <code>false</code> otherwise
	 * 
	 * @throws IOException if a new row can not be created
	 */
	public boolean newRow() throws IOException ;
	
	/**
	 * Creates a new column in the data source
	 * @return <code>true</code> if the column was created, <code>false</code> otherwise
	 * 
	 * @throws IOException if a new column can not be created
	 */
	public boolean newColumn() throws IOException ;
	
	/**
	 * Writes the value in the current cell of the data source 
         * 
	 * @param cell value
	 * @throws IOException if the data can not be written
	 */
	public void writeCell(Object cell) throws IOException ;
	
	/**
	 * Write all cells to the data source in one step
	 * @param cells the cells to be written to the data source
	 * @throws IOException if the data can not be written
	 */
	public void writeCells(List<List<Object>> cells) throws IOException ;
	
	/**
	 * Write all cells to the data source in one step
	 * @param cells the cells to be written to the data source
	 * @throws IOException if the data can not be written
	 */
	public void writeCellsAsArray(Object[][] cells) throws IOException ;
}