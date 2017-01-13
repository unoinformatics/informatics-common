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

package uno.informatics.common.io.text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import uno.informatics.common.io.RowReader;
import uno.informatics.common.io.RowReaderStringTest;
import uno.informatics.common.io.TextFileHandler;

public class TXTFileTextFileRowReaderStringTest extends RowReaderStringTest {
    private static final String FILE = "/string_table.txt";

    protected RowReader createReader() throws FileNotFoundException, IOException {
        TextFileRowReader reader = new TextFileRowReader(getClass().getResource(FILE).getPath());

        reader.setDelimiterString(TextFileHandler.TAB);

        return reader;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.common.io.RowReaderTest#getExpectedList()
     */
    @Override
    protected final List<List<Object>> getExpectedList() {
        return STRING_TABLE_AS_LIST;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.common.io.RowReaderTest#getExpectedArray()
     */
    @Override
    protected final Object[][] getExpectedArray() {
        return STRING_TABLE_AS_ARRAY;
    }

    protected final List<List<String>> getExpectedAsStringList() {
        return STRING_TABLE_AS_LIST2;
    }

    protected final String[][] getExpectedAsStringArray() {
        return STRING_TABLE_AS_ARRAY;
    }
}
