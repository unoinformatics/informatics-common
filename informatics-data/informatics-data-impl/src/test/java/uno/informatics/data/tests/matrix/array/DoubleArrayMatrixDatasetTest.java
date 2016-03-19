/*******************************************************************************
 * Copyright 2014 Guy Davenport
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

package uno.informatics.data.tests.matrix.array;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import uno.informatics.common.io.FileProperties;
import uno.informatics.common.io.FileType;
import uno.informatics.data.DataType;
import uno.informatics.data.Feature;
import uno.informatics.data.ScaleType;
import uno.informatics.data.SimpleEntity;
import uno.informatics.data.dataset.DatasetException;
import uno.informatics.data.dataset.MatrixDataset;
import uno.informatics.data.matrix.array.DoubleArrayMatrixDataset;
import uno.informatics.data.pojo.SimpleEntityPojo;
import uno.informatics.data.pojo.SimpleFeaturePojo;

/**
 * @author Guy Davenport
 *
 */
public class DoubleArrayMatrixDatasetTest {

    private static final String DATA_FILE1 = "/double_matrix.txt";
    private static final String DATA_FILE2 = "/double_matrix_col_headers.txt";
    private static final String DATA_FILE3 = "/double_matrix_row_headers.txt";
    private static final String DATA_FILE4 = "/double_matrix_col_row_headers.txt";

    private static final String UID = null;
    private static final String NAME = null;
    private static final String DESCRIPTION = null;
    private static final double DELTA = 0;
    private static final String ELEMENT_NAME = "elements";
    private static final SimpleEntity[] ROW_HEADERS = new SimpleEntity[] { new SimpleEntityPojo("R1"), new SimpleEntityPojo("R2"), new SimpleEntityPojo("R3") };
    private static final SimpleEntity[] COLUMN_HEADERS = new SimpleEntity[] {new SimpleEntityPojo( "C1"), new SimpleEntityPojo("C2"), new SimpleEntityPojo("C3"), new SimpleEntityPojo("C4"), new SimpleEntityPojo("C5") };

    /**
     * Test method for
     * {@link uno.informatics.data.matrix.array.DoubleArrayMatrixDataset#createMatrixDataset(java.lang.String, java.lang.String, java.lang.String, uno.informatics.common.model.Feature, uno.informatics.common.io.FileProperties, uno.informatics.common.model.Feature, uno.informatics.common.model.Feature)}
     * .
     */
    @Test
    public void testCreateMatrixDataset() {
        FileProperties fileProperties = new FileProperties(
                DoubleArrayMatrixDatasetTest.class.getResource(DATA_FILE1).getPath(), FileType.TXT);

        testCreateMatrixDataset(fileProperties);

        fileProperties = new FileProperties(DoubleArrayMatrixDatasetTest.class.getResource(DATA_FILE2).getPath(),
                FileType.TXT);

        fileProperties.setColumnHeaderPosition(0);
        fileProperties.setDataRowPosition(1);

        testCreateMatrixDataset(fileProperties);

        fileProperties = new FileProperties(DoubleArrayMatrixDatasetTest.class.getResource(DATA_FILE3).getPath(),
                FileType.TXT);

        fileProperties.setRowHeaderPosition(0);

        testCreateMatrixDataset(fileProperties);

        fileProperties = new FileProperties(DoubleArrayMatrixDatasetTest.class.getResource(DATA_FILE4).getPath(),
                FileType.TXT);

        fileProperties.setColumnHeaderPosition(0);
        fileProperties.setRowHeaderPosition(0);
        fileProperties.setDataRowPosition(1);

        testCreateMatrixDataset(fileProperties);
    }

    public void testCreateMatrixDataset(FileProperties fileProperties) {
        try {
            Feature valueFeature = new SimpleFeaturePojo(ELEMENT_NAME, DataType.STRING, ScaleType.NOMINAL);
            MatrixDataset<Double> matrix = DoubleArrayMatrixDataset.createMatrixDataset(UID, NAME, DESCRIPTION,
                    valueFeature, fileProperties);

            assertEquals("row count not equal!", ROW_HEADERS.length, matrix.getRowCount());
            assertEquals("column count not equal!", COLUMN_HEADERS.length, matrix.getColumnCount());

            if (matrix.hasRowHeaders())
                assertArrayEquals("row headers not equal!", ROW_HEADERS, matrix.getRowHeaders().toArray());
            else
                assertEquals("row headers not equal!", null, matrix.getRowHeaders());

            if (matrix.hasColumnHeaders())
                assertArrayEquals("column headers not equal!", COLUMN_HEADERS, matrix.getColumnHeaders().toArray());
            else
                assertEquals("row headers not equal!", null, matrix.getColumnHeaders());

            for (int x = 0; x < matrix.getRowCount(); ++x)
                for (int y = 0; y < matrix.getColumnCount(); ++y)
                    assertEquals("x=" + x + " y=" + y, new Double("" + (x + 1) + "." + (y + 1)),
                            (double) matrix.getValue(x, y), DELTA);

        } catch (DatasetException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }
    }

}
