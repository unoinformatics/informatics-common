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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uno.informatics.data.dataset.FeatureDataRow;
import uno.informatics.data.feature.array.ArrayFeatureDataRow;
import uno.informatics.data.tests.feature.DataRowTest;

/**
 * @author Guy Davenport
 *
 */
public class ArrayFeatureDataRowTest extends DataRowTest {
    /**
     * Test method for
     * {@link uno.informatics.data.feature.array.ArrayFeatureDataRow#ArrayFeatureDatasetRow(java.lang.Object[])}
     * .
     */
    @Test
    public void testArrayFeatureDatasetRowObjectArray() {
        FeatureDataRow datasetRow = new ArrayFeatureDataRow(OBJECT_ROW1);

        assertArrayEquals(OBJECT_ROW1, datasetRow.getValuesAsArray());
        assertSimpleEntityEquals("Header not correct!", null, datasetRow.getHeader());
        assertEquals("Column count not correct!", OBJECT_ROW1.length, datasetRow.getColumnCount());

        assertEquals("Values not correct!", OBJECT_TABLE_AS_LIST.get(0), datasetRow.getValues());
        assertArrayEquals("Values not correct!", OBJECT_ROW1, datasetRow.getValuesAsArray());

        for (int i = 0; i < datasetRow.getColumnCount(); ++i)
            assertEquals("Value at " + i + " not correct!", OBJECT_ROW1[i], datasetRow.getValue(i));
    }

    /**
     * Test method for
     * {@link uno.informatics.data.feature.array.ArrayFeatureDataRow#ArrayFeatureDatasetRow(java.lang.String, java.lang.Object[])}
     * .
     */
    @Test
    public void testArrayFeatureDatasetRowStringObjectArray() {
        FeatureDataRow datasetRow = new ArrayFeatureDataRow(OBJECT_ROW1);

        assertArrayEquals(OBJECT_ROW1, datasetRow.getValuesAsArray());
        assertSimpleEntityEquals("Header not correct!", null, datasetRow.getHeader());
        assertEquals("Column count not correct!", OBJECT_ROW1.length, datasetRow.getColumnCount());

        assertEquals("Values not correct!", OBJECT_TABLE_AS_LIST.get(0), datasetRow.getValues());
        assertArrayEquals("Values not correct!", OBJECT_ROW1, datasetRow.getValuesAsArray());

        for (int i = 0; i < datasetRow.getColumnCount(); ++i)
            assertEquals("Value at " + i + " not correct!", OBJECT_ROW1[i], datasetRow.getValue(i));
    }

    /**
     * Test method for
     * {@link uno.informatics.data.feature.array.ArrayFeatureDataRow#ArrayFeatureDatasetRow(java.lang.Object, java.lang.Object[])}
     * .
     */
    @Test
    public void testArrayFeatureDatasetRowObjectObjectArray() {
        FeatureDataRow datasetRow = new ArrayFeatureDataRow(ROW1_HEADER, OBJECT_ROW1);

        assertArrayEquals(OBJECT_ROW1, datasetRow.getValuesAsArray());
        assertSimpleEntityEquals("Header not correct!", ROW1_HEADER, datasetRow.getHeader());
        assertEquals("Column count not correct!", OBJECT_ROW1.length, datasetRow.getColumnCount());

        assertEquals("Values not correct!", OBJECT_TABLE_AS_LIST.get(0), datasetRow.getValues());
        assertArrayEquals("Values not correct!", OBJECT_ROW1, datasetRow.getValuesAsArray());

        for (int i = 0; i < datasetRow.getColumnCount(); ++i)
            assertEquals("Value at " + i + " not correct!", OBJECT_ROW1[i], datasetRow.getValue(i));
    }

    /**
     * Test method for
     * {@link uno.informatics.data.feature.array.ArrayFeatureDataRow#ArrayFeatureDatasetRow(java.util.List)}
     * .
     */
    @Test
    public void testArrayFeatureDatasetRowListOfObject() {
        FeatureDataRow datasetRow = new ArrayFeatureDataRow(OBJECT_TABLE_AS_LIST.get(0));

        assertArrayEquals(OBJECT_ROW1, datasetRow.getValuesAsArray());
        assertSimpleEntityEquals("Header not correct!", null, datasetRow.getHeader());
        assertEquals("Column count not correct!", OBJECT_ROW1.length, datasetRow.getColumnCount());

        assertEquals("Values not correct!", OBJECT_TABLE_AS_LIST.get(0), datasetRow.getValues());
        assertArrayEquals("Values not correct!", OBJECT_ROW1, datasetRow.getValuesAsArray());

        for (int i = 0; i < datasetRow.getColumnCount(); ++i)
            assertEquals("Value at " + i + " not correct!", OBJECT_ROW1[i], datasetRow.getValue(i));
    }

    /**
     * Test method for
     * {@link uno.informatics.data.feature.array.ArrayFeatureDataRow#ArrayFeatureDatasetRow(java.lang.String, java.util.List)}
     * .
     */
    @Test
    public void testArrayFeatureDatasetRowStringListOfObject() {
        FeatureDataRow datasetRow = new ArrayFeatureDataRow(ROW1_HEADER, OBJECT_TABLE_AS_LIST.get(0));

        assertArrayEquals(OBJECT_ROW1, datasetRow.getValuesAsArray());
        assertSimpleEntityEquals("Header not correct!", ROW1_HEADER, datasetRow.getHeader());
        assertEquals("Column count not correct!", OBJECT_ROW1.length, datasetRow.getColumnCount());

        assertEquals("Values not correct!", OBJECT_TABLE_AS_LIST.get(0), datasetRow.getValues());
        assertArrayEquals("Values not correct!", OBJECT_ROW1, datasetRow.getValuesAsArray());

        for (int i = 0; i < datasetRow.getColumnCount(); ++i)
            assertEquals("Value at " + i + " not correct!", OBJECT_ROW1[i], datasetRow.getValue(i));
    }

    /**
     * Test method for
     * {@link uno.informatics.data.feature.array.ArrayFeatureDataRow#ArrayFeatureDatasetRow(java.lang.Object, java.util.List)}
     * .
     */
    @Test
    public void testArrayFeatureDatasetRowObjectListOfObject() {
        FeatureDataRow datasetRow = new ArrayFeatureDataRow(ROW_HEADER, OBJECT_TABLE_AS_LIST.get(0));

        assertArrayEquals(OBJECT_ROW1, datasetRow.getValuesAsArray());
        assertSimpleEntityEquals("Header not correct!", ROW_HEADER, datasetRow.getHeader());
        assertEquals("Column count not correct!", OBJECT_ROW1.length, datasetRow.getColumnCount());

        assertEquals("Values not correct!", OBJECT_TABLE_AS_LIST.get(0), datasetRow.getValues());
        assertArrayEquals("Values not correct!", OBJECT_ROW1, datasetRow.getValuesAsArray());

        for (int i = 0; i < datasetRow.getColumnCount(); ++i)
            assertEquals("Value at " + i + " not correct!", OBJECT_ROW1[i], datasetRow.getValue(i));
    }

    /**
     * Test method for
     * {@link uno.informatics.data.feature.array.ArrayFeatureDataRow#createRow(boolean, java.util.List)}
     * .
     */
    @Test
    public void testCreateRowBooleanListOfObject() {
        FeatureDataRow datasetRow = ArrayFeatureDataRow.createRow(true, OBJECT_TABLE_AS_LIST_WITH_HEADER.get(0));

        assertArrayEquals(OBJECT_ROW1, datasetRow.getValuesAsArray());
        assertSimpleEntityEquals("Header not correct!", ROW1_HEADER, datasetRow.getHeader());
        assertEquals("Column count not correct!", OBJECT_ROW1.length, datasetRow.getColumnCount());

        assertEquals("Values not correct!", OBJECT_TABLE_AS_LIST.get(0), datasetRow.getValues());
        assertArrayEquals("Values not correct!", OBJECT_ROW1, datasetRow.getValuesAsArray());

        for (int i = 0; i < datasetRow.getColumnCount(); ++i)
            assertEquals("Value at " + i + " not correct!", OBJECT_ROW1[i], datasetRow.getValue(i));

        datasetRow = ArrayFeatureDataRow.createRow(false, OBJECT_TABLE_AS_LIST.get(0));

        assertArrayEquals(OBJECT_ROW1, datasetRow.getValuesAsArray());
        assertSimpleEntityEquals("Header not correct!", null, datasetRow.getHeader());
        assertEquals("Column count not correct!", OBJECT_ROW1.length, datasetRow.getColumnCount());

        assertEquals("Values not correct!", OBJECT_TABLE_AS_LIST.get(0), datasetRow.getValues());
        assertArrayEquals("Values not correct!", OBJECT_ROW1, datasetRow.getValuesAsArray());

        for (int i = 0; i < datasetRow.getColumnCount(); ++i)
            assertEquals("Value at " + i + " not correct!", OBJECT_ROW1[i], datasetRow.getValue(i));
    }

    /**
     * Test method for
     * {@link uno.informatics.data.feature.array.ArrayFeatureDataRow#createRow(boolean, java.lang.Object[])}
     * .
     */
    @Test
    public void testCreateRowBooleanObjectArray() {
        FeatureDataRow datasetRow = ArrayFeatureDataRow.createRow(true, OBJECT_ROW1_WITH_HEADER);

        assertArrayEquals(OBJECT_ROW1, datasetRow.getValuesAsArray());
        assertSimpleEntityEquals("Header not correct!", ROW1_HEADER, datasetRow.getHeader());
        assertEquals("Column count not correct!", OBJECT_ROW1.length, datasetRow.getColumnCount());

        assertEquals("Values not correct!", OBJECT_TABLE_AS_LIST.get(0), datasetRow.getValues());
        assertArrayEquals("Values not correct!", OBJECT_ROW1, datasetRow.getValuesAsArray());

        for (int i = 0; i < datasetRow.getColumnCount(); ++i)
            assertEquals("Value at " + i + " not correct!", OBJECT_ROW1[i], datasetRow.getValue(i));

        datasetRow = ArrayFeatureDataRow.createRow(false, OBJECT_ROW1);

        assertArrayEquals(OBJECT_ROW1, datasetRow.getValuesAsArray());
        assertSimpleEntityEquals("Header not correct!", null, datasetRow.getHeader());
        assertEquals("Column count not correct!", OBJECT_ROW1.length, datasetRow.getColumnCount());

        assertEquals("Values not correct!", OBJECT_TABLE_AS_LIST.get(0), datasetRow.getValues());
        assertArrayEquals("Values not correct!", OBJECT_ROW1, datasetRow.getValuesAsArray());

        for (int i = 0; i < datasetRow.getColumnCount(); ++i)
            assertEquals("Value at " + i + " not correct!", OBJECT_ROW1[i], datasetRow.getValue(i));
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.data.tests.feature.array.array.DatasetRowTest#
     * createDatasetRow()
     */
    @Override
    protected FeatureDataRow createDatasetRow() {
        return new ArrayFeatureDataRow(ROW1_HEADER, OBJECT_ROW1);
    }

}
