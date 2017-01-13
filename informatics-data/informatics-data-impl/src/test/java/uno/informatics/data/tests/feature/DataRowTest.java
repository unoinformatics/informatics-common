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

import org.junit.Test;

import uno.informatics.data.dataset.FeatureDataRow;
import uno.informatics.data.tests.TestData;

/**
 * @author Guy Davenport
 *
 */
public abstract class DataRowTest extends TestData {

    @Test
    public void testGetHeader() {
        FeatureDataRow datasetRow = createDatasetRow();

        assertSimpleEntityEquals("Header no correct", ROW1_HEADER, datasetRow.getHeader());
    }

    @Test
    public void testGetValues() {
        FeatureDataRow datasetRow = createDatasetRow();

        assertEquals(OBJECT_TABLE_AS_LIST.get(0), datasetRow.getValues());
    }

    @Test
    public void testGetValuesAsArray() {
        FeatureDataRow datasetRow = createDatasetRow();

        assertArrayEquals(OBJECT_ROW1, datasetRow.getValuesAsArray());
    }

    @Test
    public void testGetValue() {
        FeatureDataRow datasetRow = createDatasetRow();

        for (int i = 0; i < datasetRow.getColumnCount(); ++i)
            assertEquals(OBJECT_ROW1[i], datasetRow.getValue(i));
    }

    /**
     * @return
     */
    protected abstract FeatureDataRow createDatasetRow();
}
