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
import uno.informatics.data.DataOption;
import uno.informatics.data.DataType;
import uno.informatics.data.Feature;
import uno.informatics.data.ScaleType;
import uno.informatics.data.SimpleEntity;
import uno.informatics.data.dataset.DatasetException;
import uno.informatics.data.dataset.MatrixData;
import uno.informatics.data.io.FileType;
import uno.informatics.data.matrix.AbstractMatrixData;
import uno.informatics.data.matrix.array.DoubleArrayMatrixData;
import uno.informatics.data.pojo.SimpleEntityPojo;
import uno.informatics.data.pojo.SimpleFeaturePojo;

/**
 * @author Guy Davenport
 *
 */
public class DoubleArrayMatrixDatasetTest {

    private static final String IDS = "/matrix/double/ids_only.txt";
    private static final String IDS_NAMES_ON_COL = "/matrix/double/idsNamesOnCols.txt";
    private static final String IDS_NAMES_ON_ROW = "/matrix/double/idsNamesOnRows.txt";
    private static final String IDS_NAMES_ON_BOTH = "/matrix/double/idsNamesOnBoth.txt";
    
    private static final double DELTA = 0;
    private static final String ELEMENT_NAME = "elements";
    
    private static final SimpleEntity[] ROW_HEADERS = new SimpleEntity[] { 
            new SimpleEntityPojo("Row1"),
            new SimpleEntityPojo("Row2"), 
            new SimpleEntityPojo("Row3") };
    
    private static final SimpleEntity[] COLUMN_HEADERS = new SimpleEntity[] { 
            new SimpleEntityPojo("Col1"),
            new SimpleEntityPojo("Col2"), 
            new SimpleEntityPojo("Col3"), 
            new SimpleEntityPojo("Col4"),
            new SimpleEntityPojo("Col5") };
    
    private static final SimpleEntity[] ROW_HEADERS_WITH_NAMES = new SimpleEntity[] { 
            new SimpleEntityPojo("Row1", "R1"),
            new SimpleEntityPojo("Row2", "R2"), 
            new SimpleEntityPojo("Row3", "R3") };
    
    private static final SimpleEntity[] COLUMN_HEADERS_WITH_NAMES = new SimpleEntity[] { 
            new SimpleEntityPojo("Col1", "C1"),
            new SimpleEntityPojo("Col2", "C2"), 
            new SimpleEntityPojo("Col3", "C3"), 
            new SimpleEntityPojo("Col4", "C4"),
            new SimpleEntityPojo("Col5", "C5") };


    @Test
    public void testCreateMatrixDatasetIDsOnly() {

        testCreateMatrixDataset(Paths.get(DoubleArrayMatrixDatasetTest.class.getResource(IDS).getPath()), FileType.TXT, COLUMN_HEADERS, ROW_HEADERS);
    }
    
    @Test
    public void testCreateMatrixDatasetIdsNamesOnCols() {

        testCreateMatrixDataset(Paths.get(DoubleArrayMatrixDatasetTest.class.getResource(IDS_NAMES_ON_COL).getPath()), FileType.TXT, COLUMN_HEADERS_WITH_NAMES, ROW_HEADERS);
        
    }
    
    @Test
    public void testCreateMatrixDatasetIdsNamesOnRows() {

        testCreateMatrixDataset(Paths.get(DoubleArrayMatrixDatasetTest.class.getResource(IDS_NAMES_ON_ROW).getPath()), FileType.TXT, COLUMN_HEADERS, ROW_HEADERS_WITH_NAMES);
    }
    
    @Test
    public void testCreateMatrixDatasetIdsNamesOnBoth() {

        testCreateMatrixDataset(Paths.get(DoubleArrayMatrixDatasetTest.class.getResource(IDS_NAMES_ON_BOTH).getPath()), FileType.TXT, COLUMN_HEADERS_WITH_NAMES, ROW_HEADERS_WITH_NAMES);
    }

    public void testCreateMatrixDataset(Path filePath, FileType type, SimpleEntity[] columnHeaders, SimpleEntity[] rowHeaders) {
        try {
            Feature valueFeature = new SimpleFeaturePojo(ELEMENT_NAME, DataType.STRING, ScaleType.NOMINAL);
            
            MatrixData<Double> matrix = DoubleArrayMatrixData.readData(filePath, type, new DataOption(AbstractMatrixData.ID, valueFeature));

            assertEquals("row count not equal!", rowHeaders.length, matrix.getRowCount());
            assertEquals("column count not equal!", columnHeaders.length, matrix.getColumnCount());

            if (matrix.hasRowHeaders())
                assertArrayEquals("row headers not equal!", rowHeaders, matrix.getRowHeaders().toArray());
            else
                assertEquals("row headers not equal!", null, matrix.getRowHeaders());

            if (matrix.hasColumnHeaders())
                assertArrayEquals("column headers not equal!", columnHeaders, matrix.getColumnHeaders().toArray());
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
