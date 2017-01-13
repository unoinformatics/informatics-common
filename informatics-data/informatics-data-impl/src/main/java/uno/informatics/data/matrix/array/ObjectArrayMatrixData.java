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

package uno.informatics.data.matrix.array;

import static uno.informatics.common.Constants.UNKNOWN_COUNT;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import uno.informatics.common.ConversionException;
import uno.informatics.common.ConversionUtilities;
import uno.informatics.common.io.IOUtilities;
import uno.informatics.common.io.RowReader;
import uno.informatics.data.Feature;
import uno.informatics.data.SimpleEntity;
import uno.informatics.data.dataset.MatrixData;
import uno.informatics.data.io.FileType;
import uno.informatics.data.pojo.SimpleEntityPojo;
import uno.informatics.data.utils.DataOption;

/**
 * @author Guy Davenport
 *
 */
public class ObjectArrayMatrixData extends ArrayMatrixData<Object> {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ObjectArrayMatrixData(String name, Feature elementFeature, Object[][] values) {
        super(name, elementFeature, values);
    }

    public ObjectArrayMatrixData(String name, Feature elementFeature, List<List<Object>> values) {
        super(name, elementFeature, values);
    }

    public ObjectArrayMatrixData(String uniqueIdentifier, String name, Feature elementFeature, Object[][] values) {
        super(uniqueIdentifier, name, elementFeature, values);
    }

    public ObjectArrayMatrixData(String uniqueIdentifier, String name, Feature elementFeature,
            List<List<Object>> values) {
        super(uniqueIdentifier, name, elementFeature, values);
    }

    public static final MatrixData<Object> readData(Path filePath, FileType type, DataOption... options)
            throws IOException {

        // validate arguments

        if (filePath == null) {
            throw new IllegalArgumentException("File path not defined.");
        }

        if (!filePath.toFile().exists()) {
            throw new IOException("File does not exist : " + filePath + ".");
        }

        if (type == null) {
            throw new IllegalArgumentException("File type not defined.");
        }

        if (type != FileType.TXT && type != FileType.CSV) {
            throw new IllegalArgumentException(
                    String.format("Only file types TXT and CSV are supported. Got: %s.", type));
        }

        String uniqueIdentifier = DataOption.findValue(options, ID, String.class);
        String name = DataOption.findValue(options, NAME, String.class);
        Feature elementFeature = DataOption.findValue(options, ELEMENT_FEATURE, Feature.class);

        List<SimpleEntity> columnHeaders = null;
        List<SimpleEntity> rowHeaders = null;
        RowReader reader = null;
        List<List<Object>> rowList = new LinkedList<List<Object>>();

        reader = IOUtilities.createRowReader(filePath, type);

        if (reader != null && reader.ready()) {
            int columnCount = UNKNOWN_COUNT;
            int row = 0;
            List<String> headers;
            String rowName;
            String rowID = null;

            boolean hasRowIDs = false;

            if (reader.nextRow()) {
                ++row;

                headers = reader.getRowCellsAsString();

                if (NAME.equals(headers.get(0))) {
                    headers.remove(0); // ignore cell!

                    if (ID.equals(headers.get(0))) {
                        hasRowIDs = true;
                        headers.remove(0); // ignore cell!
                    }
                } else {
                    if (name == null)
                        name = headers.get(0);

                    headers.remove(0);
                }

                columnCount = headers.size();

                columnHeaders = new ArrayList<SimpleEntity>(columnCount);

                Iterator<String> iterator = headers.iterator();

                while (iterator.hasNext())
                    columnHeaders.add(new SimpleEntityPojo(iterator.next()));

                columnCount = headers.size();
            }

            List<Object> cells;

            if (reader.nextRow()) {
                ++row;

                rowHeaders = new LinkedList<SimpleEntity>();

                reader.nextColumn();

                rowName = reader.getCellAsString();

                if (hasRowIDs) {
                    reader.nextColumn();

                    rowID = reader.getCellAsString();
                }

                reader.nextColumn();

                if (ID.equals(rowName)) {
                    hasRowIDs = true;
                    headers = reader.getRowCellsAsString();

                    Iterator<String> iterator1 = headers.iterator();
                    Iterator<SimpleEntity> iterator2 = columnHeaders.iterator();

                    while (iterator1.hasNext() && iterator2.hasNext()) {
                        ((SimpleEntityPojo) iterator2.next()).setUniqueIdentifier(iterator1.next());
                    }
                } else {
                    cells = reader.getRowCells();

                    if (cells.size() != columnCount)
                        throw new IOException(String.format("Row %d is not right size, expecting %d but was %d!", row, columnCount, cells.size()));

                    rowList.add(cells);

                    if (hasRowIDs) {
                        rowHeaders.add(new SimpleEntityPojo(rowID, rowName));
                    } else {
                        rowHeaders.add(new SimpleEntityPojo(rowName));
                    }
                }

                while (reader.nextRow()) {
                    ++row;

                    reader.nextColumn();

                    rowName = reader.getCellAsString();

                    if (hasRowIDs) {
                        reader.nextColumn();

                        rowID = reader.getCellAsString();
                    }

                    reader.nextColumn();

                    cells = reader.getRowCells();

                    if (cells.size() != columnCount)
                        throw new IOException(String.format("Row %d is not right size, expecting %d but was %d!", row, columnCount, cells.size()));

                    rowList.add(cells);

                    ++row;
                }
            }
        }

        if (reader != null)
            reader.close();

        /// updateRowsScales(newFeatures, rowList) ;

        ObjectArrayMatrixData matrix = new ObjectArrayMatrixData(uniqueIdentifier, name, elementFeature, rowList);

        if (columnHeaders != null && !columnHeaders.isEmpty())
            matrix.setColumnHeaders(columnHeaders);

        if (rowHeaders != null && !rowHeaders.isEmpty())
            matrix.setRowHeaders(rowHeaders);

        return matrix;

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * uno.informatics.data.matrix.array.ArrayMatrixDataset#createArrayArray(
     * int)
     */
    @Override
    protected final Double[][] createArrayArray(int size) {
        return new Double[size][];
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * uno.informatics.data.matrix.array.ArrayMatrixDataset#createArray(int)
     */
    @Override
    protected final Double[] createArray(int size) {
        return new Double[size];
    }

    /**
     * @param cells
     * @return
     * @throws ConversionException
     */
    @SuppressWarnings("unused")
    private static List<Object> convertCells(List<String> cells, int type) throws ConversionException {
        return ConversionUtilities.convertToObjectList(cells, type);
    }

    /**
     * @param cells
     * @return
     * @throws ConversionException
     */
    @SuppressWarnings("unused")
    private static List<Object> convertCells(List<String> cells, int[] types) throws ConversionException {
        return ConversionUtilities.convertToObjectList(cells, types);
    }
}
