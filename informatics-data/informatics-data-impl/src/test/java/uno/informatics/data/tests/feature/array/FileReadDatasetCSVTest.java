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

package uno.informatics.data.tests.feature.array;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import uno.informatics.common.io.FileType;
import uno.informatics.data.dataset.DatasetException;
import uno.informatics.data.dataset.FeatureData;
import uno.informatics.data.feature.array.ArrayFeatureData;
import uno.informatics.data.tests.TestData;

/**
 * @author Guy Davenport
 * 
 */
public class FileReadDatasetCSVTest extends TestData {
    private static final String OBJECT_TABLE = "/object_table_with_col_headers.csv";
    private static final String OBJECT_TABLE_WITH_ROW_NAMES = "/object_table_with_col_name_row_headers.csv";
    private static final String OBJECT_TABLE_WITH_ROW_NAMES_IDS = "/object_table_with_col_name_id_row_headers.csv";
    private static final String OBJECT_TABLE_WITH_TYPE_ROW_NAMES = "/object_table_with_col_type_name_row_headers.csv";
    private static final String OBJECT_TABLE_WITH_TYPE_ROW_NAMES_IDS = "/object_table_with_col_type_name_id_row_headers.csv";
    private static final String OBJECT_TABLE_WITH_TYPE_MIN_ROW_NAMES = "/object_table_with_col_type_min_name_row_headers.csv";
    private static final String OBJECT_TABLE_WITH_TYPE_MIN_ROW_NAMES_IDS = "/object_table_with_col_type_min_name_id_row_headers.csv";
    private static final String OBJECT_TABLE_WITH_TYPE_MIN_MAX_ROW_NAMES = "/object_table_with_col_type_min_max_name_row_headers.csv";
    private static final String OBJECT_TABLE_WITH_TYPE_MIN_MAX_ROW_NAMES_IDS = "/object_table_with_col_type_min_max_name_id_row_headers.csv";

    /**
     * @throws IOException 
     * Test method for
     * {@link uno.informatics.data.feature.array.ArrayFeatureData#readData(java.io.File, uno.informatics.common.io.FileType)}
     * .
     * @throws  
     */
    @Test
    public void testReadFeatureDatasetFromTextFile()  {
        try {
            Path path = Paths.get(ArrayFeatureData.class.getResource(OBJECT_TABLE).getPath());

            FeatureData dataset = ArrayFeatureData.readData(path, getFileType());

            checkCompleteData(path.getFileName().toString(), path.getFileName().toString(), STRING_FEATURES, dataset, BLANK_HEADERS, true);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }
    }

    protected FileType getFileType() {
        return FileType.CSV;
    }

    /**
     * Test method for
     * {@link uno.informatics.data.feature.array.ArrayFeatureData#readData(java.io.File, uno.informatics.common.io.FileType)}
     * .
     */
    @Test
    public void testReadFeatureDatasetFromTextFileWithNameRowHeaders() {
        
        try {
            Path path = Paths.get(ArrayFeatureData.class.getResource(OBJECT_TABLE_WITH_ROW_NAMES).getPath());

            FeatureData dataset = ArrayFeatureData.readData(path, getFileType());

            checkCompleteData(path.getFileName().toString(), path.getFileName().toString(), STRING_FEATURES, dataset, ROW_HEADERS, true);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }
    }

    /**
     * Test method for
     * {@link uno.informatics.data.feature.array.ArrayFeatureData#readData(java.io.File, uno.informatics.common.io.FileType)}
     * .
     */
    @Test
    public void testReadFeatureDatasetFromTextFileWithNameAndIDRowHeaders() {
        try {
            Path path = Paths.get(ArrayFeatureData.class.getResource(OBJECT_TABLE_WITH_ROW_NAMES_IDS).getPath());

            FeatureData dataset = ArrayFeatureData.readData(path, getFileType());

            checkCompleteData(path.getFileName().toString(), path.getFileName().toString(), STRING_FEATURES, dataset, ROW_HEADERS_WITH_ID, true);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }
    }

    /**
     * Test method for
     * {@link uno.informatics.data.feature.array.ArrayFeatureData#readData(java.io.File, uno.informatics.common.io.FileType)}
     * .
     */
    @Test
    public void testReadFeatureDatasetFromTextFileWithTypeNameRowHeaders() {
        try {
            Path path = Paths.get(ArrayFeatureData.class.getResource(OBJECT_TABLE_WITH_TYPE_ROW_NAMES).getPath());

            FeatureData dataset = ArrayFeatureData.readData(path, getFileType());

            checkCompleteData(path.getFileName().toString(), path.getFileName().toString(), OBJECT_FEATURES, dataset, ROW_HEADERS, false);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }       
    }

    /**
     * Test method for
     * {@link uno.informatics.data.feature.array.ArrayFeatureData#readData(java.io.File, uno.informatics.common.io.FileType)}
     * .
     */
    @Test
    public void testReadFeatureDatasetFromTextFileWithTypeNameAndIDRowHeaders() {
        try {
            Path path = Paths.get(ArrayFeatureData.class.getResource(OBJECT_TABLE_WITH_TYPE_ROW_NAMES_IDS).getPath());

            FeatureData dataset = ArrayFeatureData.readData(path, getFileType());

            checkCompleteData(path.getFileName().toString(), path.getFileName().toString(), OBJECT_FEATURES, dataset, ROW_HEADERS_WITH_ID, false);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }       
    }

    /**
     * Test method for
     * {@link uno.informatics.data.feature.array.ArrayFeatureData#readData(java.io.File, uno.informatics.common.io.FileType)}
     * .
     */
    @Test
    public void testReadFeatureDatasetFromTextFileWithTypeMinNameRowHeaders() {
        try {
            Path path = Paths.get(ArrayFeatureData.class.getResource(OBJECT_TABLE_WITH_TYPE_MIN_ROW_NAMES).getPath());

            FeatureData dataset = ArrayFeatureData.readData(path, getFileType());

            checkCompleteData(path.getFileName().toString(), path.getFileName().toString(), OBJECT_FEATURES_MIN, dataset, ROW_HEADERS, false);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }
    }

    /**
     * Test method for
     * {@link uno.informatics.data.feature.array.ArrayFeatureData#readData(java.io.File, uno.informatics.common.io.FileType)}
     * .
     */
    @Test
    public void testReadFeatureDatasetFromTextFileWithTypeMinNameAndIDRowHeaders() {
        try {
            Path path = Paths.get(ArrayFeatureData.class.getResource(OBJECT_TABLE_WITH_TYPE_MIN_ROW_NAMES_IDS).getPath());

            FeatureData dataset = ArrayFeatureData.readData(path, getFileType());

            checkCompleteData(path.getFileName().toString(), path.getFileName().toString(), OBJECT_FEATURES_MIN, dataset, ROW_HEADERS_WITH_ID, false);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }
    }

    /**
     * Test method for
     * {@link uno.informatics.data.feature.array.ArrayFeatureData#readData(java.io.File, uno.informatics.common.io.FileType)}
     * .
     */
    @Test
    public void testReadFeatureDatasetFromTextFileWithTypeMinMaxNameRowHeaders() {
        try {
            Path path = Paths.get(ArrayFeatureData.class.getResource(OBJECT_TABLE_WITH_TYPE_MIN_MAX_ROW_NAMES).getPath());

            FeatureData dataset = ArrayFeatureData.readData(path, getFileType());

            checkCompleteData(path.getFileName().toString(), path.getFileName().toString(), OBJECT_FEATURES_MIN_MAX, dataset, ROW_HEADERS, false);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }
    }

    /**
     * Test method for
     * {@link uno.informatics.data.feature.array.ArrayFeatureData#readData(java.io.File, uno.informatics.common.io.FileType)}
     * .
     */
    @Test
    public void testReadFeatureDatasetFromTextFileWithTypeMinMaxNameAndIDRowHeaders() {
        try {
            Path path = Paths.get(ArrayFeatureData.class.getResource(OBJECT_TABLE_WITH_TYPE_MIN_MAX_ROW_NAMES_IDS).getPath());

            FeatureData dataset = ArrayFeatureData.readData(path, getFileType());

            checkCompleteData(path.getFileName().toString(), path.getFileName().toString(), OBJECT_FEATURES_MIN_MAX, dataset, ROW_HEADERS_WITH_ID, false);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }
    }
}
