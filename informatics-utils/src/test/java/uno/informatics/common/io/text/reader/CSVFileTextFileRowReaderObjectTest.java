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

package uno.informatics.common.io.text.reader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import uno.informatics.common.io.RowReader;
import uno.informatics.common.io.RowReaderObjectTest;
import uno.informatics.common.io.TextFileHandler;
import uno.informatics.common.io.text.TextFileRowReader;

public class CSVFileTextFileRowReaderObjectTest extends RowReaderObjectTest {
    private static final String FILE = "/object_table.csv";

    protected RowReader createReader() throws FileNotFoundException, IOException {
        TextFileRowReader reader = new TextFileRowReader(getClass().getResource(FILE).getPath());

        reader.setDelimiterString(TextFileHandler.COMMA);

        return reader;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.common.io.RowReaderTest#getExpectedList()
     */
    @Override
    protected final List<List<Object>> getExpectedList() {
        return OBJECT_TABLE_AS_LIST;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.common.io.RowReaderTest#getExpectedArray()
     */
    @Override
    protected final Object[][] getExpectedArray() {
        return OBJECT_TABLE_AS_ARRAY;
    }
}
