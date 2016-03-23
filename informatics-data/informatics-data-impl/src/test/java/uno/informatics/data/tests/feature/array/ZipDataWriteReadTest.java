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

import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;

import uno.informatics.common.io.FileType;
import uno.informatics.data.dataset.DatasetException;
import uno.informatics.data.dataset.FeatureData;
import uno.informatics.data.feature.array.ArrayFeatureData;
import uno.informatics.data.feature.array.ZipFeatureDataReader;
import uno.informatics.data.feature.array.ZipFeatureDataWriter;
import uno.informatics.data.tests.TestData;

/**
 * @author Guy Davenport
 *
 */
public class ZipDataWriteReadTest extends TestData {
    private static final String FILE = "target/test.zip";
    private static final String FILE_WITH_HEADER = "target/test_with_header.zip";

    @Test
    public void testWriteReadTxt() {
        testWriteRead(FileType.TXT);
    }

    @Test
    public void testWriteReadCSV() {
        testWriteRead(FileType.CSV);
    }

    /**
     * Test method for
     * {@link uno.informatics.data.tests.feature.array.ZipFeatureDataReader#read()}
     * and
     * {@link uno.informatics.data.tests.feature.array.ZipFeatureDataWriter#write(uno.FeatureData.informatics.data.dataset.FeatureDataset)}
     * ..
     */
    public void testWriteRead(FileType fileType) {
        FeatureData originalDataset = createDataset();

        ZipFeatureDataWriter writer = new ZipFeatureDataWriter(new File(FILE));

        writer.setFileType(fileType);

        try {
            writer.write(originalDataset);

            ZipFeatureDataReader reader = new ZipFeatureDataReader(new File(FILE));

            FeatureData readDataset = (FeatureData) reader.read();

            checkCompleteData(UID, NAME, OBJECT_FEATURES, readDataset, BLANK_HEADERS, false);
        } catch (DatasetException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

        try {
            originalDataset = createDatasetWithHeaders();

            writer = new ZipFeatureDataWriter(new File(FILE_WITH_HEADER));

            writer.setFileType(fileType);

            writer.write(originalDataset);

            ZipFeatureDataReader reader = new ZipFeatureDataReader(new File(FILE_WITH_HEADER));

            FeatureData readDataset = (FeatureData) reader.read();

            checkCompleteData(UID, NAME, OBJECT_FEATURES, readDataset, ROW_HEADERS, false);
        } catch (DatasetException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    protected FeatureData createDataset() {
        return new ArrayFeatureData(UID, NAME, OBJECT_FEATURES, OBJECT_TABLE_AS_ARRAY);
    }

    protected FeatureData createDatasetWithHeaders() throws DatasetException {
        return new ArrayFeatureData(UID, NAME, OBJECT_FEATURES, OBJECT_TABLE_AS_ARRAY_WITH_HEADER, true);
    }
}
