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

import uno.informatics.data.dataset.FeatureData;
import uno.informatics.data.dataset.FeatureDataRow;
import uno.informatics.data.tests.TestData;

/**
 * @author Guy Davenport
 *
 */
public abstract class DatasetTest extends TestData {

    @Test
    public void testGetRowCount() {
        FeatureData dataset = createDataset();

        assertEquals(ROWS_AS_LIST.size(), dataset.getRowCount());
    }

    @Test
    public void testGetRowsAsArray() {
        FeatureData dataset = createDataset();

        FeatureDataRow[] rows = dataset.getRowsAsArray();

        for (int i = 0; i < rows.length; ++i)
            assertArrayEquals(OBJECT_TABLE_AS_ARRAY[i], rows[i].getValuesAsArray());
    }

    @Test
    public void testGetRows() {
        FeatureData dataset = createDataset();

        List<FeatureDataRow> rows = dataset.getRows();

        FeatureDataRow[] array = dataset.getRows().toArray(new FeatureDataRow[rows.size()]);

        for (int i = 0; i < array.length; ++i)
            assertArrayEquals(OBJECT_TABLE_AS_ARRAY[i], array[i].getValuesAsArray());
    }

    @Test
    public void testGetFeatures() {
        FeatureData dataset = createDataset();

        assertArrayEquals(OBJECT_FEATURES_AS_ARRAY, dataset.getFeaturesAsArray());
    }

    protected abstract FeatureData createDataset();
}
