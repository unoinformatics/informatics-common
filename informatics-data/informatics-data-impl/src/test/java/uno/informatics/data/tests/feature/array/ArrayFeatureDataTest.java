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
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import uno.informatics.data.dataset.FeatureData;
import uno.informatics.data.feature.array.ArrayFeatureData;
import uno.informatics.data.io.FileType;
import uno.informatics.data.tests.feature.DatasetTest;

// TODO TXT, XLS, XLSX versions
/**
 * @author Guy Davenport
 *
 */
public class ArrayFeatureDataTest extends DatasetTest {
    

    @Test
    public void testSetName() {
        ArrayFeatureData data = new ArrayFeatureData(UID, NAME, OBJECT_FEATURES_AS_ARRAY,
                OBJECT_TABLE_AS_ARRAY_WITH_HEADER);

        assertEquals(NAME, data.getName());

        data.setName("asdf");

        assertEquals("asdf", data.getName());
    }

    @Test
    public void testArrayFeatureDatasetStringListOfDatasetFeatureObjectArrayArray() {
        ArrayFeatureData data = new ArrayFeatureData(NAME, OBJECT_FEATURES_AS_ARRAY, OBJECT_TABLE_AS_ARRAY_WITH_HEADER);

        checkCompleteData(null, NAME, OBJECT_FEATURES, data, ROW_HEADERS, false);
    }


    /**
     * Test method for
     * {@link uno.informatics.data.feature.array.ArrayFeatureData#ArrayFeatureDataset(java.lang.String, java.lang.String, java.util.List, java.util.List)}
     * .
     */
    @Test
    public void testArrayFeatureDatasetStringStringListOfDatasetFeatureListOfListOfObject() {
        ArrayFeatureData data = new ArrayFeatureData(UID, NAME, OBJECT_FEATURES, OBJECT_TABLE_AS_LIST_WITH_HEADER);

        checkCompleteData(UID, NAME, OBJECT_FEATURES, data, ROW_HEADERS, false);
    }
    
    @Test
    public void testWriteReadFeatureDataset() {
        try {
                      
            Path path = Paths.get("target","testoutput") ;
            
            Files.createDirectories(path) ;

            path = Files.createTempDirectory(path, "writeRead") ;
  
            path = Paths.get(path.toString(), "writeRead.csv") ;
            
            ArrayFeatureData expectedData = (ArrayFeatureData)createDataset() ;
            
            expectedData.setUniqueIdentifier("writeRead.csv");
            expectedData.setName("writeRead.csv");
            
            expectedData.writeData(path, FileType.CSV);
            
            FeatureData actualData = ArrayFeatureData.readData(path, FileType.CSV);

            checkCompleteData(expectedData, actualData);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }
    }

    @Override
    protected FeatureData createDataset() {
        return new ArrayFeatureData(NAME, OBJECT_FEATURES_AS_ARRAY, OBJECT_TABLE_AS_ARRAY_WITH_HEADER);
    }
}
