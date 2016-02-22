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
 * Writes a table to a data source row by row
 * @author Guy Davenport
 */
public interface RowWriter extends TableWriter
{
	/**
	 * Writes the row cells to the data source
	 * @param cells row cells to be written to the data source
	 * @throws IOException if the data can not be written
	 */
	public void writeRowCells(List<Object> cells) throws IOException ;

	/**
	 * Writes the row cells to the data source
	 * @param cells row cells to be written to the data source
	 * @throws IOException if the data can not be written
	 */
        public void writeRowCellsAsArray(Object[] cells) throws IOException;	
}