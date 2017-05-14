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
import java.text.SimpleDateFormat;

import org.junit.Test;

import uno.informatics.data.DataOption;
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
        
        data = new ArrayFeatureData(NAME, OBJECT_FEATURES_AS_ARRAY, OBJECT_TABLE_AS_ARRAY_WITH_HEADER_WITH_NAME);

        checkCompleteData(null, NAME, OBJECT_FEATURES, data, ROW_HEADERS_WITH_NAME, false);
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
        
        data = new ArrayFeatureData(UID, NAME, OBJECT_FEATURES, OBJECT_TABLE_AS_LIST_WITH_HEADER_WITH_NAME);

        checkCompleteData(UID, NAME, OBJECT_FEATURES, data, ROW_HEADERS_WITH_NAME, false);
    }
    
    @Test
    public void testWriteReadFeatureDatasetCSV() {
        try {
                      
            Path targetPath = Paths.get("target","testoutput") ;
            
            Files.createDirectories(targetPath) ;

            Path path = Files.createTempDirectory(targetPath, "writeRead") ;
  
            path = Paths.get(path.toString(), "writeRead.csv") ;
            
            ArrayFeatureData expectedData = (ArrayFeatureData)createDataset() ;
            
            expectedData.setUniqueIdentifier("writeRead.csv");
            expectedData.setName("writeRead.csv");
            
            expectedData.writeData(path, FileType.CSV);
            
            FeatureData actualData = ArrayFeatureData.readData(path, FileType.CSV);

            checkCompleteData(expectedData, actualData);
            
            path = Files.createTempDirectory(targetPath, "writeRead") ;
            
            path = Paths.get(path.toString(), "writeRead.csv") ;
            
            expectedData = (ArrayFeatureData)createDatasetWithHeaderName() ;
            
            expectedData.setUniqueIdentifier("writeRead.csv");
            expectedData.setName("writeRead.csv");
            
            expectedData.writeData(path, FileType.CSV);
            
            actualData = ArrayFeatureData.readData(path, FileType.CSV);

            checkCompleteData(expectedData, actualData);

        } catch (IOException e) {
            e.printStackTrace();

            fail(e.getLocalizedMessage());
        }
    }
    
    @Test
    public void testWriteReadFeatureDatasetTXT() {
        try {
                      
            Path targetPath = Paths.get("target","testoutput") ;
            
            Files.createDirectories(targetPath) ;

            Path path = Files.createTempDirectory(targetPath, "writeRead") ;
  
            path = Paths.get(path.toString(), "writeRead.txt") ;
            
            ArrayFeatureData expectedData = (ArrayFeatureData)createDataset() ;
            
            expectedData.setUniqueIdentifier("writeRead.txt");
            expectedData.setName("writeRead.txt");
            
            expectedData.writeData(path, FileType.TXT, new DataOption(ArrayFeatureData.DATE_FORMAT, new SimpleDateFormat("dd/MM/yyyy")));
            
            FeatureData actualData = ArrayFeatureData.readData(path, FileType.TXT, new DataOption(ArrayFeatureData.DATE_FORMAT, new SimpleDateFormat("dd/MM/yyyy")));

            checkCompleteData(expectedData, actualData);
            
            path = Files.createTempDirectory(targetPath, "writeRead") ;
            
            path = Paths.get(path.toString(), "writeRead.txt") ;
            
            expectedData = (ArrayFeatureData)createDatasetWithHeaderName() ;
            
            expectedData.setUniqueIdentifier("writeRead.txt");
            expectedData.setName("writeRead.txt");
            
            expectedData.writeData(path, FileType.TXT);
            
            actualData = ArrayFeatureData.readData(path, FileType.TXT);

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
    
    protected FeatureData createDatasetWithHeaderName() {
        return new ArrayFeatureData(NAME, OBJECT_FEATURES_AS_ARRAY, OBJECT_TABLE_AS_ARRAY_WITH_HEADER_WITH_NAME);
    }
}
