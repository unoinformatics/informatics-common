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
import uno.informatics.data.FeatureDataset;
import uno.informatics.data.dataset.DatasetException;
import uno.informatics.data.feature.array.ArrayFeatureDataset;
import uno.informatics.data.feature.array.ZipFeatureDatasetReader;
import uno.informatics.data.feature.array.ZipFeatureDatasetWriter;
import uno.informatics.data.tests.TestData;

/**
 * @author Guy Davenport
 *
 */
public class ZipDatasetWriteReadTest extends TestData {
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
     * {@link uno.informatics.data.tests.feature.array.ZipFeatureDatasetReader#read()}
     * and
     * {@link uno.informatics.data.tests.feature.array.ZipFeatureDatasetWriter#write(uno.informatics.data.feature.FeatureDataset)}
     * ..
     */
    public void testWriteRead(FileType fileType) {
        FeatureDataset originalDataset = createDataset();

        ZipFeatureDatasetWriter writer = new ZipFeatureDatasetWriter(new File(FILE));

        writer.setFileType(fileType);

        try {
            writer.write(originalDataset);

            ZipFeatureDatasetReader reader = new ZipFeatureDatasetReader(new File(FILE));

            FeatureDataset readDataset = (FeatureDataset) reader.read();

            checkCompleteDataset(UID, NAME, DESCRIPTION, OBJECT_FEATURES, readDataset, BLANK_HEADERS, false);
        } catch (DatasetException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

        try {
            originalDataset = createDatasetWithHeaders();

            writer = new ZipFeatureDatasetWriter(new File(FILE_WITH_HEADER));

            writer.setFileType(fileType);

            writer.write(originalDataset);

            ZipFeatureDatasetReader reader = new ZipFeatureDatasetReader(new File(FILE_WITH_HEADER));

            FeatureDataset readDataset = (FeatureDataset) reader.read();

            checkCompleteDataset(UID, NAME, DESCRIPTION, OBJECT_FEATURES, readDataset, ROW_HEADERS, false);
        } catch (DatasetException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    protected FeatureDataset createDataset() {
        return new ArrayFeatureDataset(UID, NAME, DESCRIPTION, OBJECT_FEATURES, OBJECT_TABLE_AS_ARRAY);
    }

    protected FeatureDataset createDatasetWithHeaders() throws DatasetException {
        return new ArrayFeatureDataset(UID, NAME, DESCRIPTION, OBJECT_FEATURES, OBJECT_TABLE_AS_ARRAY_WITH_HEADER, true);
    }
}
