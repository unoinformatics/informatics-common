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
 */

package uno.informatics.data.feature.array;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import com.thoughtworks.xstream.XStream;

import uno.informatics.common.io.FileType;
import uno.informatics.common.io.IOUtilities;
import uno.informatics.common.io.TableReader;
import uno.informatics.data.Data;
import uno.informatics.data.Dataset;
import uno.informatics.data.Entity;
import uno.informatics.data.SimpleEntity;
import uno.informatics.data.dataset.DatasetException;
import uno.informatics.data.dataset.FeatureData;
import uno.informatics.data.io.DataReader;
import uno.informatics.data.pojo.FeaturePojo;
import uno.informatics.data.utils.DatasetUtils;

/**
 * @author Guy Davenport
 */
public class ZipFeatureDataReader extends ZipFeatureDataFileHandler implements DataReader {
    @SuppressWarnings("unused")
    private static final String SPREADSHEET_NAME = "values";

    public ZipFeatureDataReader(File file) {
        super(file);
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.data.io.DatasetWriter#read()
     */
    @Override
    public Data read() throws DatasetException {
        FeatureData dataset = null;

        try {
            ZipFile zipFile = new ZipFile(getFile());

            XStream xstream = createXStream();

            ZipEntry zipEntry = zipFile.getEntry(IDENTIFICATION_ENTRY);

            SimpleEntity identification = (SimpleEntity) xstream.fromXML(zipFile.getInputStream(zipEntry));

            zipEntry = zipFile.getEntry(FEATURES_ENTRY);

            @SuppressWarnings("unchecked")
            List<FeaturePojo> features = (List<FeaturePojo>) xstream.fromXML(zipFile.getInputStream(zipEntry));

            zipEntry = zipFile.getEntry(FILE_TYPE_ENTRY);

            FileType fileType = (FileType) xstream.fromXML(zipFile.getInputStream(zipEntry));

            zipEntry = zipFile.getEntry(ROW_HEADER_ENTRY);

            List<SimpleEntity> rowHeaders = null;

            if (zipEntry != null)
                rowHeaders = (List<SimpleEntity>) xstream.fromXML(zipFile.getInputStream(zipEntry));

            switch (fileType) {
                case CSV:
                    zipEntry = zipFile.getEntry(DATA_VALUES_ENTRY_PREIFX + CSV_SUFFIX);
                    break;
                case TXT:
                    zipEntry = zipFile.getEntry(DATA_VALUES_ENTRY_PREIFX + TXT_SUFFIX);
                    break;
                case XLS:
                    zipEntry = zipFile.getEntry(DATA_VALUES_ENTRY_PREIFX + XLS_SUFFIX);
                    break;
                case XLSX:
                    zipEntry = zipFile.getEntry(DATA_VALUES_ENTRY_PREIFX + XLSX_SUFFIX);
                    break;
                default:
                    break;
            }

            TableReader reader = IOUtilities.createRowReader(
                    new BufferedReader(new InputStreamReader(zipFile.getInputStream(zipEntry))), fileType);

            reader.setAllConversionTypes(DatasetUtils.getConversionTypes(features));

            List<List<Object>> values = reader.readCells();

            reader.close();

            zipFile.close();

            dataset = createDataset(identification, features, rowHeaders, values);

        } catch (Exception e) {
            throw new DatasetException(e);
        }

        return dataset;
    }

    private FeatureData createDataset(SimpleEntity identification, List<FeaturePojo> features,
            List<SimpleEntity> rowHeaders, List<List<Object>> values) throws DatasetException {
        if (rowHeaders != null)
            return new ArrayFeatureData(identification.getUniqueIdentifier(), identification.getName(), features, rowHeaders, values);
        else
            return new ArrayFeatureData(identification.getUniqueIdentifier(), identification.getName(), features, values);
    }

}
