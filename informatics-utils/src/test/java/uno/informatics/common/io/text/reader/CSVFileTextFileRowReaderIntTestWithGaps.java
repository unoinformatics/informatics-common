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
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import uno.informatics.common.io.RowReader;
import uno.informatics.common.io.RowReaderIntTest;
import uno.informatics.common.io.RowWriter;
import uno.informatics.common.io.TextFileHandler;
import uno.informatics.common.io.text.TextFileRowReader;
import uno.informatics.common.io.text.TextFileRowWriter;

public class CSVFileTextFileRowReaderIntTestWithGaps extends RowReaderIntTest {
    private static final String FILE = "/int_table_with_gaps.csv";

    protected RowReader createReader(Path path) throws FileNotFoundException, IOException {
        TextFileRowReader reader = new TextFileRowReader(path);
 
        reader.setDelimiterString(TextFileHandler.COMMA);

        reader.setOptions(TextFileRowReader.ROWS_SAME_SIZE_AS_FIRST);

        return reader;
    }

    @Override
    protected RowWriter createWriter(Path path) throws FileNotFoundException, IOException {
        TextFileRowWriter writer = new TextFileRowWriter(path);

        writer.setDelimiterString(TextFileHandler.COMMA);

        return writer;
    }

    @Override
    protected String getTestFilePath() {
        return FILE;
    }
    
    protected final List<List<Object>> getExpectedList() {
        return TABLE_AS_LIST_WITH_GAPS;
    }

    protected final Object[][] getExpectedArray() {
        return TABLE_AS_ARRAY_WITH_GAPS;
    }

    protected final List<List<Integer>> getExpectedAsList() {
        return TABLE_AS_LIST2_WITH_GAPS;
    }

    protected final int[][] getExpectedAsArray() {
        return TABLE_AS_ARRAY2_WITH_GAPS;
    }
}
