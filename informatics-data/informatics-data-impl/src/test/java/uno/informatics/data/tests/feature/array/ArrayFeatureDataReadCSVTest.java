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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import org.junit.Test;

import uno.informatics.data.DataOption;
import uno.informatics.data.dataset.FeatureData;
import uno.informatics.data.feature.AbstractFeatureData;
import uno.informatics.data.feature.array.ArrayFeatureData;
import uno.informatics.data.io.FileType;
import uno.informatics.data.tests.TestData;

/**
 * @author Guy Davenport
 * 
 */
public class ArrayFeatureDataReadCSVTest extends TestData {
    private static final String OBJECT_TABLE_WITH_ROW_IDS = "/feature/ids_only.csv";
    private static final String OBJECT_TABLE_WITH_ROW_IDS_NAMES = "/feature/row_names.csv";
    private static final String OBJECT_TABLE_WITH_ROW_IDS_TYPES = "/feature/row_types.csv";
    private static final String OBJECT_TABLE_WITH_ROW_IDS_NAMES_TYPES = "/feature/row_names_types.csv";
    private static final String OBJECT_TABLE_WITH_ROW_IDS_TYPES_MIN = "/feature/row_types_min.csv";
    private static final String OBJECT_TABLE_WITH_ROW_IDS_NAME_TYPES_MIN = "/feature/row_names_types_min.csv";
    private static final String OBJECT_TABLE_WITH_ROW_IDS_TYPES_MIN_MAX = "/feature/row_types_min_max.csv";
    private static final String OBJECT_TABLE_WITH_ROW_IDS_NAMES_TYPE_MIN_MAX = "/feature/row_names_types_min_max.csv";
    private static final String OBJECT_TABLE_WITH_ROW_IDS_NAMES_COL_NAMES = "/feature/row_names_col_names.csv";
    private static final String OBJECT_TABLE_WITH_ROW_IDS_TYPES_COL_NAMES = "/feature/row_types_col_names.csv";
    private static final String OBJECT_TABLE_WITH_ROW_IDS_NAMES_TYPES_COL_NAMES = "/feature/row_names_types_col_names.csv";
    private static final String OBJECT_TABLE_WITH_ROW_IDS_TYPES_MIN_COL_NAMES = "/feature/row_types_min_col_names.csv";
    private static final String OBJECT_TABLE_WITH_ROW_IDS_NAME_TYPES_MIN_COL_NAMES = "/feature/row_names_types_min_col_names.csv";
    private static final String OBJECT_TABLE_WITH_ROW_IDS_TYPES_MIN_MAX_COL_NAMES = "/feature/row_types_min_max_col_names.csv";
    private static final String OBJECT_TABLE_WITH_ROW_IDS_NAMES_TYPE_MIN_MAX_COL_NAMES = "/feature/row_names_types_min_max_col_names.csv";
    
    private static final String ERRONEOUS_FILES_DIR = "/feature/err/";

    protected FileType getFileType() {
        return FileType.CSV;
    }

    @Test
    public void testReadFeatureDatasetFromTextFileWithIDRowHeaders() {
        
        try {
            Path path = Paths.get(ArrayFeatureData.class.getResource(OBJECT_TABLE_WITH_ROW_IDS).getPath());

            FeatureData dataset = ArrayFeatureData.readData(path, getFileType());

            checkCompleteData(path.getFileName().toString(), path.getFileName().toString(), STRING_FEATURES, dataset, ROW_HEADERS, true);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testReadFeatureDatasetFromTextFileWithIDNameRowHeaders() {
        try {
            Path path = Paths.get(ArrayFeatureData.class.getResource(OBJECT_TABLE_WITH_ROW_IDS_NAMES).getPath());

            FeatureData dataset = ArrayFeatureData.readData(path, getFileType(), DATA_FORMAT_OPTION);

            checkCompleteData(path.getFileName().toString(), path.getFileName().toString(), STRING_FEATURES, dataset, ROW_HEADERS_WITH_NAME, true);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testReadFeatureDatasetFromTextFileWithIDTypeRowHeaders() {
        try {
            Path path = Paths.get(ArrayFeatureData.class.getResource(OBJECT_TABLE_WITH_ROW_IDS_TYPES).getPath());

            FeatureData dataset = ArrayFeatureData.readData(path, getFileType(), DATA_FORMAT_OPTION);

            checkCompleteData(path.getFileName().toString(), path.getFileName().toString(), OBJECT_FEATURES, dataset, ROW_HEADERS, false);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }       
    }

    @Test
    public void testReadFeatureDatasetFromTextFileWithIDNameTypeRowHeaders() {
        try {
            Path path = Paths.get(ArrayFeatureData.class.getResource(OBJECT_TABLE_WITH_ROW_IDS_NAMES_TYPES).getPath());

            FeatureData dataset = ArrayFeatureData.readData(path, getFileType(), DATA_FORMAT_OPTION);

            checkCompleteData(path.getFileName().toString(), path.getFileName().toString(), OBJECT_FEATURES, dataset, ROW_HEADERS_WITH_NAME, false);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }       
    }

    @Test
    public void testReadFeatureDatasetFromTextFileWithIDTypeMinRowHeaders() {
        try {
            Path path = Paths.get(ArrayFeatureData.class.getResource(OBJECT_TABLE_WITH_ROW_IDS_TYPES_MIN).getPath());

            FeatureData dataset = ArrayFeatureData.readData(path, getFileType(), DATA_FORMAT_OPTION);

            checkCompleteData(path.getFileName().toString(), path.getFileName().toString(), OBJECT_FEATURES_MIN, dataset, ROW_HEADERS, false);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testReadFeatureDatasetFromTextFileWithIDNameTypeMinHeaders() {
        try {
            Path path = Paths.get(ArrayFeatureData.class.getResource(OBJECT_TABLE_WITH_ROW_IDS_NAME_TYPES_MIN).getPath());

            FeatureData dataset = ArrayFeatureData.readData(path, getFileType(), DATA_FORMAT_OPTION);

            checkCompleteData(path.getFileName().toString(), path.getFileName().toString(), OBJECT_FEATURES_MIN, dataset, ROW_HEADERS_WITH_NAME, false);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testReadFeatureDatasetFromTextFileWithIDTypeMinMaxRowHeaders() {
        try {
            Path path = Paths.get(ArrayFeatureData.class.getResource(OBJECT_TABLE_WITH_ROW_IDS_TYPES_MIN_MAX).getPath());

            FeatureData dataset = ArrayFeatureData.readData(path, getFileType(), DATA_FORMAT_OPTION) ;
            checkCompleteData(path.getFileName().toString(), path.getFileName().toString(), OBJECT_FEATURES_MIN_MAX, dataset, ROW_HEADERS, false);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testReadFeatureDatasetFromTextFileWithIDRowAndColHeaders() {
        try {
            Path path = Paths.get(ArrayFeatureData.class.getResource(OBJECT_TABLE_WITH_ROW_IDS_NAMES_TYPE_MIN_MAX).getPath());

            FeatureData dataset = ArrayFeatureData.readData(path, getFileType(), DATA_FORMAT_OPTION);

            checkCompleteData(path.getFileName().toString(), path.getFileName().toString(), OBJECT_FEATURES_MIN_MAX, dataset, ROW_HEADERS_WITH_NAME, false);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }
    }
    
    @Test
    public void testReadFeatureDatasetFromTextFileWithIDNameRowAndColHeaders() {
        try {
            Path path = Paths.get(ArrayFeatureData.class.getResource(OBJECT_TABLE_WITH_ROW_IDS_NAMES_COL_NAMES).getPath());

            FeatureData dataset = ArrayFeatureData.readData(path, getFileType(), DATA_FORMAT_OPTION);

            checkCompleteData(path.getFileName().toString(), path.getFileName().toString(), STRING_FEATURES_COL, dataset, ROW_HEADERS_WITH_NAME, true);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testReadFeatureDatasetFromTextFileWithIDTypeRowAndNameColHeaders() {
        try {
            Path path = Paths.get(ArrayFeatureData.class.getResource(OBJECT_TABLE_WITH_ROW_IDS_TYPES_COL_NAMES).getPath());

            FeatureData dataset = ArrayFeatureData.readData(path, getFileType(), DATA_FORMAT_OPTION);

            checkCompleteData(path.getFileName().toString(), path.getFileName().toString(), OBJECT_FEATURES_COL, dataset, ROW_HEADERS, false);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }       
    }

    @Test
    public void testReadFeatureDatasetFromTextFileWithIDNameTypeRowAndNameColHeaders() {
        try {
            Path path = Paths.get(ArrayFeatureData.class.getResource(OBJECT_TABLE_WITH_ROW_IDS_NAMES_TYPES_COL_NAMES).getPath());

            FeatureData dataset = ArrayFeatureData.readData(path, getFileType(), DATA_FORMAT_OPTION);

            checkCompleteData(path.getFileName().toString(), path.getFileName().toString(), OBJECT_FEATURES_COL, dataset, ROW_HEADERS_WITH_NAME, false);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }       
    }

    @Test
    public void testReadFeatureDatasetFromTextFileWithIDTypeMinAndIDRowAndNameColHeaders() {
        try {
            Path path = Paths.get(ArrayFeatureData.class.getResource(OBJECT_TABLE_WITH_ROW_IDS_TYPES_MIN_COL_NAMES).getPath());

            FeatureData dataset = ArrayFeatureData.readData(path, getFileType(), DATA_FORMAT_OPTION);

            checkCompleteData(path.getFileName().toString(), path.getFileName().toString(), OBJECT_FEATURES_MIN_COL, dataset, ROW_HEADERS, false);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testReadFeatureDatasetFromTextFileWithIDNameTypeMinAndIDRowAndNameColHeaders() {
        try {
            Path path = Paths.get(ArrayFeatureData.class.getResource(OBJECT_TABLE_WITH_ROW_IDS_NAME_TYPES_MIN_COL_NAMES).getPath());

            FeatureData dataset = ArrayFeatureData.readData(path, getFileType(), DATA_FORMAT_OPTION);

            checkCompleteData(path.getFileName().toString(), path.getFileName().toString(), OBJECT_FEATURES_MIN_COL, dataset, ROW_HEADERS_WITH_NAME, false);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testReadFeatureDatasetFromTextFileWithIDTypeMinMaxAndIDRowAndNameColHeaders() {
        try {
            Path path = Paths.get(ArrayFeatureData.class.getResource(OBJECT_TABLE_WITH_ROW_IDS_TYPES_MIN_MAX_COL_NAMES).getPath());

            FeatureData dataset = ArrayFeatureData.readData(path, getFileType(), DATA_FORMAT_OPTION);

            checkCompleteData(path.getFileName().toString(), path.getFileName().toString(), OBJECT_FEATURES_MIN_MAX_COL, dataset, ROW_HEADERS, false);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testReadFeatureDatasetFromTextFileWithIDNameTypeMinMaxAndIDRowAndNameColHeaders() {
        try {
            Path path = Paths.get(ArrayFeatureData.class.getResource(OBJECT_TABLE_WITH_ROW_IDS_NAMES_TYPE_MIN_MAX_COL_NAMES).getPath());

            FeatureData dataset = ArrayFeatureData.readData(path, getFileType(), DATA_FORMAT_OPTION);

            checkCompleteData(path.getFileName().toString(), path.getFileName().toString(), OBJECT_FEATURES_MIN_MAX_COL, dataset, ROW_HEADERS_WITH_NAME, false);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }
    }
    
    @Test
    public void erroneousFiles() throws IOException {
        System.out.println(" |- Test erroneous files:");
        Path dir = Paths.get(ArrayFeatureData.class.getResource(ERRONEOUS_FILES_DIR).getPath());
        try(DirectoryStream<Path> directory = Files.newDirectoryStream(dir)){
            for(Path file : directory){
                System.out.print("  |- " + file.getFileName().toString() + ": ");
                FileType type = file.toString().endsWith(".txt") ? FileType.TXT : FileType.CSV;
                boolean thrown = false;
                try {
                    ArrayFeatureData.readData(file, type);
                } catch (IOException ex){
                    thrown = true;
                    System.out.print(ex.getMessage());
                } finally {
                    System.out.println();
                }
                assertTrue("File " + file + " should throw exception.", thrown);
            }
        }
    }
}
