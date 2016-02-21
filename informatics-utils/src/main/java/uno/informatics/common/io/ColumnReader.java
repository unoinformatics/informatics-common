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
 * Reads a data source column by column
 * 
 * @author Guy Davenport
 *
 * @param <T>
 */
public interface ColumnReader extends TableReader
{
	/**
	 * Gets the current column cell values
	 * 
	 * @return the column cell values
	 * @throws IOException
	 */
	public List<Object> getColumnCells() throws IOException ;
	
	/**
	 * Gets the current column cell values as an array
	 * 
	 * @return the column cell values as an array
	 * @throws IOException
	 */
	public Object[] getColumnCellsAsArray() throws IOException ;
}