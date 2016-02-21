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
 * Writes to a data source column by column
 * @author Guy Davenport
 *
 * @param <T>
 */
public interface ColumnWriter extends TableWriter
{
	/**
	 * Writes the column cells to the data source
	 * @param cells column cells to be written to the data source
	 * @throws IOException
	 */
	public void writeColumnCells(List<Object> cells) throws IOException ;
	
	/**
	 * Writes the column cells as an array to the data source
	 * @param cells column cells to be written to the data source
	 * @throws IOException
	 */
	public void writeColumnArray(Object[] cells) throws IOException ;
}