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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

import com.thoughtworks.xstream.XStream;

import uno.informatics.common.io.FileType;
import uno.informatics.common.io.IOUtilities;
import uno.informatics.common.io.RowWriter;
import uno.informatics.data.Data;
import uno.informatics.data.SimpleEntity;
import uno.informatics.data.dataset.DatasetException;
import uno.informatics.data.dataset.FeatureData;
import uno.informatics.data.dataset.FeatureDataRow;
import uno.informatics.data.io.DataWriter;
import uno.informatics.data.pojo.SimpleEntityPojo;

//TODO  needs updating 

/**
 * @author Guy Davenport
 *
 */
public class ZipFeatureDataWriter extends ZipFeatureDataFileHandler implements DataWriter {
    private FileType fileType;

    public ZipFeatureDataWriter(File file) {
        super(file);

        setFileType(FileType.TXT);
    }

    public final FileType getFileType() {
        return fileType;
    }

    public final void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * uno.informatics.data.io.DatasetWriter#write(uno.informatics.data.tests.
     * feature.array.Dataset)
     */
    @Override
    public void write(Data dataset) throws DatasetException {
        if (dataset instanceof FeatureData) {
            FeatureData featureDataset = (FeatureData) dataset;

            try {
                ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(getFile()));

                XStream xstream = createXStream();

                outputStream.putNextEntry(new ZipEntry(FEATURES_ENTRY));

                xstream.toXML(featureDataset.getFeatures(), outputStream);

                outputStream.closeEntry();

                outputStream.putNextEntry(new ZipEntry(IDENTIFICATION_ENTRY));

                xstream.toXML(new SimpleEntityPojo(dataset), outputStream);

                outputStream.closeEntry();

                outputStream.putNextEntry(new ZipEntry(FILE_TYPE_ENTRY));

                xstream.toXML(fileType, outputStream);

                outputStream.closeEntry();

                String zipEntry = "unknown-type";

                switch (fileType) {
                    case CSV:
                        zipEntry = DATA_VALUES_ENTRY_PREIFX + CSV_SUFFIX;
                        break;
                    case TXT:
                        zipEntry = DATA_VALUES_ENTRY_PREIFX + TXT_SUFFIX;
                        break;
                    case XLS:
                        zipEntry = DATA_VALUES_ENTRY_PREIFX + XLS_SUFFIX;
                        break;
                    case XLSX:
                        zipEntry = DATA_VALUES_ENTRY_PREIFX + XLSX_SUFFIX;
                        break;
                    default:
                        break;
                }

                outputStream.putNextEntry(new ZipEntry(zipEntry));

                RowWriter writer = IOUtilities.createRowWriter(new BufferedWriter(new OutputStreamWriter(outputStream)),
                        fileType);

                Iterator<FeatureDataRow> iterator = featureDataset.getRows().iterator();

                FeatureDataRow row;

                List<SimpleEntity> rowHeaders = new ArrayList<SimpleEntity>(featureDataset.getRowCount());

                if (iterator.hasNext()) {
                    row = iterator.next();
                    writer.writeRowCells(row.getValues());

                    rowHeaders.add(row.getHeader());

                    while (iterator.hasNext() && writer.newRow()) {
                        row = iterator.next();
                        writer.writeRowCells(row.getValues());

                        rowHeaders.add(row.getHeader());
                    }
                }

                outputStream.closeEntry();

                outputStream.putNextEntry(new ZipEntry(ROW_HEADER_ENTRY));

                xstream.toXML(rowHeaders, outputStream);

                outputStream.closeEntry();

                outputStream.close();

                writer.close();
            } catch (ZipException e) {
                throw new DatasetException(e);
            } catch (IOException e) {
                throw new DatasetException(e);
            }
        } else {
            throw new DatasetException("Can not save dataset of type : " + dataset.getClass().getName());
        }
    }

}
