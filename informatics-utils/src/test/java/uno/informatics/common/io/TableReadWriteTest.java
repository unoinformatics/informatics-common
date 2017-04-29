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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

public abstract class TableReadWriteTest extends TestData {

    @Test
    public void testReadCells() {
        try {
            TableReader reader = createReader();

            List<List<Object>> expected = getExpectedList();

            assertTrue(reader.ready());

            List<List<Object>> cells = reader.readCells();

            reader.close();

            assertEquals("table not equal", expected, cells);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testReadCellsAsArray() {
        try {
            TableReader reader = createReader();

            Object[][] expected = getExpectedArray();

            assertTrue(reader.ready());

            Object[][] cells = reader.readCellsAsArray();

            reader.close();

            assertArrayEquals("table not equal", expected, cells);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testGetCell() {
        try {
            TableReader reader = createReader();

            Object[][] expected = getExpectedArray();

            assertTrue(reader.ready());

            int rowIndex = 0;
            int columnIndex = 0;

            while (reader.hasNextRow()) {
                assertTrue("Can not get next row", reader.nextRow());

                columnIndex = 0;

                while (reader.hasNextColumn()) {
                    assertTrue(
                        "Can not get next column at row index" + rowIndex + " column index " + columnIndex,
                        reader.nextColumn());

                    assertEquals("cell not equal at row index" + rowIndex + " column index " + columnIndex,
                        expected[rowIndex][columnIndex], reader.getCell());

                    ++columnIndex;
                }

                assertEquals("Column count not correct!", expected[rowIndex].length, columnIndex);

                ++rowIndex;
            }

            assertEquals("Row count not correct!", expected.length, rowIndex);

            reader.close();

        } catch (Exception e) {
            e.printStackTrace(System.err);
            fail(e.getLocalizedMessage());
        }
    }

    protected abstract TableReader createReader() throws FileNotFoundException, IOException;

    protected abstract List<List<Object>> getExpectedList();

    protected abstract Object[][] getExpectedArray();
}
