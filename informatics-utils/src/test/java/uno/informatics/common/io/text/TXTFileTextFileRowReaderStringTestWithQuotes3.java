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
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import uno.informatics.common.io.RowReader;
import uno.informatics.common.io.RowReaderStringTest;
import uno.informatics.common.io.RowWriter;
import uno.informatics.common.io.TextFileHandler;

public class TXTFileTextFileRowReaderStringTestWithQuotes3 extends RowReaderStringTest {
    private static final String FILE = "/string_table_with_quotes3.txt";
    
    private final static String STRING_CELL1_1 = "R2\"C2" ;
    private final static String STRING_CELL1_2 = "R2'C3" ;
    
    private final static String[] STRING_ROW2_ = new String[] {
        STRING_ROW2[0], STRING_CELL1_1, STRING_CELL1_2
    };
    private final static String[][] STRING_TABLE_AS_ARRAY2 = new String[][] {
        STRING_ROW1, STRING_ROW2_, STRING_ROW3
    };

    private static final List<List<String>> STRING_TABLE_AS_LIST2 = new ArrayList<List<String>>();

    static {
        STRING_TABLE_AS_LIST2.add(new ArrayList<String>(STRING_ROW1.length));

        STRING_TABLE_AS_LIST2.get(0).add(STRING_ROW1[0]);
        STRING_TABLE_AS_LIST2.get(0).add(STRING_ROW1[1]);
        STRING_TABLE_AS_LIST2.get(0).add(STRING_ROW1[2]);

        STRING_TABLE_AS_LIST2.add(new ArrayList<String>(STRING_ROW2.length));

        STRING_TABLE_AS_LIST2.get(1).add(STRING_ROW2[0]);
        STRING_TABLE_AS_LIST2.get(1).add(STRING_CELL1_1);
        STRING_TABLE_AS_LIST2.get(1).add(STRING_CELL1_2);

        STRING_TABLE_AS_LIST2.add(new ArrayList<String>(STRING_ROW2.length));

        STRING_TABLE_AS_LIST2.get(2).add(STRING_ROW3[0]);
        STRING_TABLE_AS_LIST2.get(2).add(STRING_ROW3[1]);
        STRING_TABLE_AS_LIST2.get(2).add(STRING_ROW3[2]);
    }
    
    protected static final List<List<Object>> STRING_TABLE_AS_LIST = new ArrayList<List<Object>>();

    static {
        STRING_TABLE_AS_LIST.add(new ArrayList<Object>(STRING_ROW1.length));

        STRING_TABLE_AS_LIST.get(0).add(STRING_ROW1[0]);
        STRING_TABLE_AS_LIST.get(0).add(STRING_ROW1[1]);
        STRING_TABLE_AS_LIST.get(0).add(STRING_ROW1[2]);

        STRING_TABLE_AS_LIST.add(new ArrayList<Object>(STRING_ROW2.length));

        STRING_TABLE_AS_LIST.get(1).add(STRING_ROW2[0]);
        STRING_TABLE_AS_LIST.get(1).add(STRING_CELL1_1);
        STRING_TABLE_AS_LIST.get(1).add(STRING_CELL1_2);

        STRING_TABLE_AS_LIST.add(new ArrayList<Object>(STRING_ROW3.length));

        STRING_TABLE_AS_LIST.get(2).add(STRING_ROW3[0]);
        STRING_TABLE_AS_LIST.get(2).add(STRING_ROW3[1]);
        STRING_TABLE_AS_LIST.get(2).add(STRING_ROW3[2]);
    }

    protected RowReader createReader(Path path) throws FileNotFoundException, IOException {
        TextFileRowReader reader = new TextFileRowReader(path);
        
        reader.setDelimiterString(TextFileHandler.TAB);

        reader.setOptions(TextFileRowReader.ROWS_SAME_SIZE_AS_FIRST | TextFileRowReader.REMOVE_QUOTES);

        return reader;
    }
    
    @Override
    protected RowWriter createWriter(Path path) throws FileNotFoundException, IOException {
        TextFileRowWriter writer = new TextFileRowWriter(path);

        writer.setDelimiterString(TextFileHandler.TAB);
        
        writer.setOptions(TextFileRowWriter.ADD_QUOTES);

        return writer;
    }

    @Override
    protected String getTestFilePath() {
        return FILE;
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
        return STRING_TABLE_AS_ARRAY2;
    }

    protected final List<List<String>> getExpectedAsStringList() {
        return STRING_TABLE_AS_LIST2;
    }

    protected final String[][] getExpectedAsStringArray() {
        return STRING_TABLE_AS_ARRAY2;
    }
}
