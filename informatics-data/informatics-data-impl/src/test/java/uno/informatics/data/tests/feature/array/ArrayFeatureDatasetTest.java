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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uno.informatics.data.dataset.FeatureData;
import uno.informatics.data.feature.array.ArrayFeatureData;
import uno.informatics.data.tests.feature.DatasetTest;

/**
 * @author Guy Davenport
 *
 */
public class ArrayFeatureDatasetTest extends DatasetTest {

    @Test
    public void testSetName() {
        ArrayFeatureData dataset = new ArrayFeatureData(UID, NAME, OBJECT_FEATURES_AS_ARRAY,
                OBJECT_TABLE_AS_ARRAY_WITH_HEADER);

        assertEquals(NAME, dataset.getName());

        dataset.setName("asdf");

        assertEquals("asdf", dataset.getName());
    }

    @Test
    public void testArrayFeatureDatasetStringListOfDatasetFeatureObjectArrayArray() {
        ArrayFeatureData dataset = new ArrayFeatureData(NAME, OBJECT_FEATURES_AS_ARRAY, OBJECT_TABLE_AS_ARRAY_WITH_HEADER);

        checkCompleteData(null, NAME, OBJECT_FEATURES, dataset, ROW_HEADERS, false);
    }


    /**
     * Test method for
     * {@link uno.informatics.data.feature.array.ArrayFeatureData#ArrayFeatureDataset(java.lang.String, java.lang.String, java.util.List, java.util.List)}
     * .
     */
    @Test
    public void testArrayFeatureDatasetStringStringListOfDatasetFeatureListOfListOfObject() {
        ArrayFeatureData dataset = new ArrayFeatureData(UID, NAME, OBJECT_FEATURES, OBJECT_TABLE_AS_LIST_WITH_HEADER);

        checkCompleteData(UID, NAME, OBJECT_FEATURES, dataset, ROW_HEADERS, false);
    }


    @Override
    protected FeatureData createDataset() {
        return new ArrayFeatureData(NAME, OBJECT_FEATURES_AS_ARRAY, OBJECT_TABLE_AS_ARRAY_WITH_HEADER);
    }
}
