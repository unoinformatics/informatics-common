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
 * Extends a reader to provide for multiple sheets
 * 
 * @author Guy Davenport
 *
 */
public interface ExcelReader extends Reader, ExcelHandler {
    /**
     * Gets the total number of spreadsheets available
     * 
     * @return the total number of spreadsheets available
     * @throws IOException
     *             if the reader can not access the excel file
     */
    public int getSpreadSheetCount() throws IOException;

    /**
     * Gets all the spread sheets names in order as a list
     * 
     * @return all the spread sheets names in order
     * @throws IOException
     *             if the reader can not access the excel file
     */
    public List<String> getAllSpreadSheetNames() throws IOException;

    /**
     * Gets all the spread sheets names in order in a array
     * 
     * @return all the spread sheets names in order
     * @throws IOException
     *             if the reader can not access the excel file
     */
    public String[] getAllSpreadSheetNamesAsArray() throws IOException;
}