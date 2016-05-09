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

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import uno.informatics.common.io.FileProperties;
import uno.informatics.common.io.FileType;
import uno.informatics.data.DataOption;
import uno.informatics.data.DataType;
import uno.informatics.data.Feature;
import uno.informatics.data.ScaleType;
import uno.informatics.data.SimpleEntity;
import uno.informatics.data.dataset.DatasetException;
import uno.informatics.data.dataset.MatrixData;
import uno.informatics.data.matrix.AbstractMatrixData;
import uno.informatics.data.matrix.array.DoubleArrayMatrixData;
import uno.informatics.data.pojo.SimpleEntityPojo;
import uno.informatics.data.pojo.SimpleFeaturePojo;

/**
 * @author Guy Davenport
 *
 */
public class DoubleArrayMatrixDatasetTest {

    private static final String DATA_FILE2 = "/matrix/double/double_matrix1.txt";
    private static final String DATA_FILE4 = "/matrix/double/double_matrix2.txt";

    private static final double DELTA = 0;
    private static final String ELEMENT_NAME = "elements";
    private static final SimpleEntity[] ROW_HEADERS = new SimpleEntity[] { new SimpleEntityPojo("R1"), new SimpleEntityPojo("R2"), new SimpleEntityPojo("R3") };
    private static final SimpleEntity[] COLUMN_HEADERS = new SimpleEntity[] {new SimpleEntityPojo( "C1"), new SimpleEntityPojo("C2"), new SimpleEntityPojo("C3"), new SimpleEntityPojo("C4"), new SimpleEntityPojo("C5") };

    /**
     * Test method for
     * {@link uno.informatics.data.matrix.array.DoubleArrayMatrixData#createMatrixDataset(java.lang.String, java.lang.String, java.lang.String, uno.informatics.common.model.Feature, uno.informatics.common.io.FileProperties, uno.informatics.common.model.Feature, uno.informatics.common.model.Feature)}
     * .
     */
    @Test
    public void testCreateMatrixDataset() {

        testCreateMatrixDataset(Paths.get(DoubleArrayMatrixDatasetTest.class.getResource(DATA_FILE2).getPath()), FileType.TXT);

        testCreateMatrixDataset(Paths.get(DoubleArrayMatrixDatasetTest.class.getResource(DATA_FILE4).getPath()), FileType.TXT);
    }

    public void testCreateMatrixDataset(Path filePath, FileType type) {
        try {
            Feature valueFeature = new SimpleFeaturePojo(ELEMENT_NAME, DataType.STRING, ScaleType.NOMINAL);
            
            MatrixData<Double> matrix = DoubleArrayMatrixData.readData(filePath, type, new DataOption(AbstractMatrixData.ID, valueFeature));

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

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }
    }

}
