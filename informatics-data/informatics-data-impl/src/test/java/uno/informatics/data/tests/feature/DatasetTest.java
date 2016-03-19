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

package uno.informatics.data.tests.feature;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import uno.informatics.data.dataset.FeatureDataset;
import uno.informatics.data.dataset.FeatureDatasetRow;
import uno.informatics.data.tests.TestData;

/**
 * @author Guy Davenport
 *
 */
public abstract class DatasetTest extends TestData {

    /**
     * Test method for
     * {@link uno.informatics.data.tests.feature.array.ArrayFeatureDataset.ArrayDataset#getRowCount()}
     * .
     */
    @Test
    public void testGetRowCount() {
        FeatureDataset dataset = createDataset();

        assertEquals(ROWS_AS_LIST.size(), dataset.getRowCount());
    }

    /**
     * Test method for
     * {@link uno.informatics.data.tests.feature.array.ArrayFeatureDataset.ArrayDataset#getRowsAsArray()}
     * .
     */
    @Test
    public void testGetRowsAsArray() {
        FeatureDataset dataset = createDataset();

        FeatureDatasetRow[] rows = dataset.getRowsAsArray();

        for (int i = 0; i < rows.length; ++i)
            assertArrayEquals(OBJECT_TABLE_AS_ARRAY[i], rows[i].getValuesAsArray());
    }

    /**
     * Test method for
     * {@link uno.informatics.data.tests.feature.array.ArrayFeatureDataset.ArrayDataset#getRows()}
     * .
     */
    @Test
    public void testGetRows() {
        FeatureDataset dataset = createDataset();

        List<FeatureDatasetRow> rows = dataset.getRows();

        FeatureDatasetRow[] array = dataset.getRows().toArray(new FeatureDatasetRow[rows.size()]);

        for (int i = 0; i < array.length; ++i)
            assertArrayEquals(OBJECT_TABLE_AS_ARRAY[i], array[i].getValuesAsArray());
    }

    /**
     * Test method for
     * {@link uno.uno.informatics.data.feature.AbstractFeatureDataset.AbstractDataset#getFeatures()}
     * .
     */
    @Test
    public void testGetFeatures() {
        FeatureDataset dataset = createDataset();

        assertEquals(OBJECT_FEATURES, dataset.getFeatures());
    }

    protected abstract FeatureDataset createDataset();
}
