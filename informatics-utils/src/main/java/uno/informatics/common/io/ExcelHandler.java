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

public interface ExcelHandler extends Reader
{
	/**
	 * Gets the index of the current spreadsheet
	 * 
	 * @return the index of the current spreadsheet
	 */
	public int getSpreadSheetIndex();

	/**
	 * Sets current spreadsheet by index. The first spreadsheet is index 0 and the last
	 * spreadsheet is {@link #getSpreadSheetCount()} - 1.
	 * 
	 * @param index of the current spreadsheet
	 */
	public void setSpreadSheetIndex(int index)
	    throws IOException;

	/**
	 * Gets the name of the current spreadsheet
	 * @return the name of the current spreadsheet
	 */
	public String getSpreadSheetName() throws IOException;

	/**
	 * Sets current spreadsheet by name.
	 * @param spreadSheetName the name spreadsheet
	 */
	public void setSpreadSheetName(String spreadSheetName)
	    throws IOException, IllegalArgumentException;
}