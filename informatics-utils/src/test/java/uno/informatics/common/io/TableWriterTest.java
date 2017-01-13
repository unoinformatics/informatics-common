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

public abstract class TableWriterTest extends TestData {

    @Test
    public void testWriteCells() {
        try {
            TableWriter writer = createWriter();

            List<List<Object>> expected = getExpectedList();

            writer.writeCells(expected);

            writer.close();

            TableReader reader = createReader();

            assertTrue(reader.ready());

            List<List<Object>> cells = reader.readCells();

            assertEquals("table not equal", expected, cells);

            reader.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            fail(e.getLocalizedMessage());
        }
    }

    @Test
    public void testWriteCellsAsArray() {
        try {
            TableWriter writer = createWriter();

            Object[][] expected = getExpectedArray();

            writer.writeCellsAsArray(expected);

            writer.close();

            TableReader reader = createReader();

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
    public void testWriteCell() {
        try {
            TableWriter writer = createWriter();

            Object[][] expected = getExpectedArray();

            for (int rowIndex = 0; rowIndex < expected.length; ++rowIndex) {
                for (int columnIndex = 0; columnIndex < expected[rowIndex].length; ++columnIndex) {
                    writer.writeCell(expected[rowIndex][columnIndex]);

                    assertTrue("Can not create column at row " + rowIndex + " after index " + columnIndex,
                        writer.newColumn());
                }

                assertTrue("Can not create row after index " + rowIndex, writer.newRow());
            }

            writer.close();

            TableReader reader = createReader();

            assertTrue(reader.ready());

            Object[][] cells = reader.readCellsAsArray();

            reader.close();

            assertArrayEquals("table not equal", expected, cells);
        } catch (Exception e) {
            e.printStackTrace(System.err);
            fail(e.getLocalizedMessage());
        }
    }

    protected abstract TableReader createReader() throws FileNotFoundException, IOException;

    protected abstract TableWriter createWriter() throws FileNotFoundException, IOException;

    protected abstract List<List<Object>> getExpectedList();

    protected abstract Object[][] getExpectedArray();
}
