
package uno.informatics.data.utils;
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

import static uno.informatics.common.Constants.UNKNOWN_INDEX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import uno.informatics.common.ConversionUtilities;
import uno.informatics.common.io.FileProperties;
import uno.informatics.common.io.IOUtilities;
import uno.informatics.common.io.RowReader;
import uno.informatics.data.DataType;
import uno.informatics.data.DataTypeConstants;
import uno.informatics.data.Feature;
import uno.informatics.data.Scale;
import uno.informatics.data.ScaleType;
import uno.informatics.data.dataset.DatasetException;
import uno.informatics.data.feature.ColumnFeature;
import uno.informatics.data.feature.ColumnFeaturePojo;
import uno.informatics.data.pojo.FeaturePojo;
import uno.informatics.data.pojo.SimpleFeaturePojo;

public class DatasetUtils {
    private static final int INVALID_ROW_COUNT = -1;

    public static final List<ColumnFeature> generateDatasetFeatures(FileProperties fileProperties, String columnLabel)
            throws DatasetException {
        return generateDatasetFeatures(fileProperties, columnLabel, INVALID_ROW_COUNT);
    }

    public static final List<ColumnFeature> generateDatasetFeatures(FileProperties fileProperties, String columnLabel,
            int numRows) throws DatasetException {
        RowReader reader = null;
        List<ColumnFeature> features = null;

        if (fileProperties == null)
            throw new DatasetException("File properties not defined!");

        if (fileProperties.getFile() == null)
            throw new DatasetException("File not defined!");

        if (fileProperties.getFileType() == null)
            throw new DatasetException("File type not defined!");

        if (fileProperties.getRowHeaderPosition() > UNKNOWN_INDEX && fileProperties.getDataRowPosition() > UNKNOWN_INDEX
                && fileProperties.getDataRowPosition() <= fileProperties.getRowHeaderPosition())
            throw new DatasetException("Header position : " + fileProperties.getDataRowPosition()
                    + " must be before data position : " + fileProperties.getRowHeaderPosition());

        if (!fileProperties.getFile().exists())
            return features;

        try {
            reader = IOUtilities.createRowReader(fileProperties);

            if (reader != null && reader.ready()) {
                features = new ArrayList<ColumnFeature>();

                List<String> headers;

                List<Integer> dataTypes = new ArrayList<Integer>();

                int columnCount = 0;
                int row = 0;
                int rowsRead = 0;

                if (fileProperties.hasColumnHeader()) {
                    List<String> cells;

                    if (reader.nextRow()) {
                        if (fileProperties.getRowHeaderPosition() > UNKNOWN_INDEX)
                            while (row < fileProperties.getRowHeaderPosition() && reader.nextRow())
                                ++row;

                        cells = reader.getRowCellsAsString();

                        ++row;

                        headers = cells;

                        if (reader.nextRow() && (numRows < 0 || rowsRead < numRows)) {
                            if (fileProperties.getDataRowPosition() > UNKNOWN_INDEX)
                                while (row < fileProperties.getDataRowPosition() && reader.nextRow())
                                    ++row;

                            cells = reader.getRowCellsAsString();

                            ++row;
                            ++rowsRead;

                            dataTypes = ConversionUtilities.getDataTypes(cells);

                            columnCount = cells.size();

                            features = new ArrayList<ColumnFeature>(columnCount);

                            dataTypes = ConversionUtilities.getDataTypes(cells);

                            while (reader.nextRow() && (numRows < 0 || rowsRead < numRows)) {
                                cells = reader.getRowCellsAsString();

                                if (cells.size() != columnCount)
                                    throw new DatasetException("Rows are not all the same size!");

                                dataTypes = ConversionUtilities.getDataTypes(cells, dataTypes);

                                ++row;
                                ++rowsRead;
                            }
                        } else {
                            throw new DatasetException("No columns");
                        }
                    } else {
                        throw new DatasetException("No columns");
                    }
                } else {
                    if (columnLabel == null)
                        throw new DatasetException("Column label not defined!");

                    List<String> cells;

                    if (fileProperties.getDataRowPosition() > UNKNOWN_INDEX)
                        while (reader.nextRow() && row < fileProperties.getDataRowPosition())
                            ++row;

                    if (reader.nextRow() && (numRows < 0 || rowsRead < numRows)) {
                        cells = reader.getRowCellsAsString();

                        ++row;
                        ++rowsRead;

                        dataTypes = ConversionUtilities.getDataTypes(cells);

                        columnCount = cells.size();

                        headers = new ArrayList<String>(columnCount);

                        for (int i = 0; i < cells.size(); ++i)
                            headers.add(columnLabel + (i + 1));

                        while (reader.nextRow() && row < numRows) {
                            cells = reader.getRowCellsAsString();

                            if (cells.size() != columnCount)
                                throw new DatasetException("Rows are not all the same size!");

                            dataTypes = ConversionUtilities.getDataTypes(cells, dataTypes);
                        }
                    } else {
                        throw new DatasetException("No columns");
                    }
                }

                reader.close();

                Iterator<String> iterator = headers.iterator();
                Iterator<Integer> iterator2 = dataTypes.iterator();

                if (fileProperties.hasRowHeader()) {
                    if (headers.size() == dataTypes.size()) {
                        iterator.next();
                        iterator2.next();
                    } else {
                        if (headers.size() - 1 == dataTypes.size()) {
                            iterator2.next();
                        } else {
                            if (headers.size() != dataTypes.size())
                                throw new DatasetException("Number of headers : " + headers.size()
                                        + " does not match number of columns : " + dataTypes.size());
                        }
                    }
                } else {
                    if (headers.size() != dataTypes.size())
                        throw new DatasetException("Number of headers : " + headers.size()
                                + " does not match number of columns : " + dataTypes.size());
                }

                while (iterator.hasNext() && iterator2.hasNext()) {
                    features.add(createDefaultColumnFeature(iterator.next(), iterator2.next()));
                }
            }

            if (reader != null)
                reader.close();

            return features;

        } catch (IOException e) {
            throw new DatasetException(e);
        }
    }

    /**
     * Creates a default column feature from a name and a bit mask of possible
     * data types
     * 
     * @param name
     *            the name of the column feature
     * @param possibleDataTypes
     *            a bit mask of possible data types
     * @return the default column feature
     */
    public static ColumnFeature createDefaultColumnFeature(String name, int possibleDataTypes) {
        return new ColumnFeaturePojo(name, possibleDataTypes);
    }

    /**
     * Creates a default feature from a name and a bit mask of possible data
     * types
     * 
     * @param name
     *            the name of the feature
     * @param dataType
     *            the data type of the feature
     * @param scaleType
     *            the scale type of the feature
     * @return the default feature
     */
    public static Feature createDefaultFeature(String name, DataType dataType, ScaleType scaleType) {
        return new SimpleFeaturePojo(name, dataType, scaleType);
    }

    /**
     * Converts column features to simple features
     * 
     * @param columnFeatures
     *            list of column features
     * @return list of features
     */
    public static List<Feature> createFeatures(List<ColumnFeature> columnFeatures) {
        Iterator<ColumnFeature> iterator = columnFeatures.iterator();

        List<Feature> features = new ArrayList<Feature>();

        while (iterator.hasNext())
            features.add(createFeature(iterator.next()));

        return features;
    }

    /**
     * Converts a column feature to simple feature
     * 
     * @param columnFeature
     *            a column feature to be converted
     * @return converted simple feature
     */
    public static Feature createFeature(ColumnFeature columnFeature) {
        return new FeaturePojo(columnFeature);
    }

    public static final int getConversionType(Feature scale) {
        return scale != null ? DataTypeConstants.getConversionType(scale.getMethod().getScale().getDataType())
                : DataTypeConstants.STRING_ID;
    }

    public static final int getConversionType(Scale scale) {
        return scale != null ? DataTypeConstants.getConversionType(scale.getDataType()) : DataTypeConstants.STRING_ID;
    }

    public static final int getConversionTypes(DataType... dataTypes) {
        int conversionType = 0;

        for (int i = 0; i < dataTypes.length; ++i)
            conversionType = conversionType | DataTypeConstants.getConversionType(dataTypes[i]);

        return conversionType;
    }

    public static final int[] getConversionTypes(List<? extends Feature> features) {
        int[] types = new int[features.size()];

        Iterator<? extends Feature> iterator = features.iterator();

        int i = 0;

        while (iterator.hasNext()) {
            types[i] = DataTypeConstants.getConversionType(iterator.next().getMethod().getScale().getDataType());
            ++i;
        }

        return types;
    }

    public static final int[] getConversionTypes(Feature rowHeaderFeature, List<? extends Feature> features) {
        int[] types = new int[features.size() + 1];

        types[0] = DataTypeConstants.getConversionType(rowHeaderFeature.getMethod().getScale().getDataType());

        Iterator<? extends Feature> iterator = features.iterator();

        int i = 1;

        while (iterator.hasNext()) {
            types[i] = DataTypeConstants.getConversionType(iterator.next().getMethod().getScale().getDataType());
            ++i;
        }

        return types;
    }

    public static final List<DataType> getDataTypes(int conversionTypes) {
        DataType[] dataTypes = DataType.getAllTypes();

        List<DataType> list = new LinkedList<DataType>();

        for (int i = 0; i < dataTypes.length; ++i)
            if ((DataTypeConstants.getConversionType(dataTypes[i]) & conversionTypes) > 0)
                list.add(dataTypes[i]);

        return list;
    }

    public static final List<ScaleType> getScaleTypes(DataType dataType) {

        List<ScaleType> list = new LinkedList<ScaleType>();

        switch (dataType) {
            case BOOLEAN:
                list.add(ScaleType.NOMINAL);
                break;
            case DATE:
                list.add(ScaleType.NOMINAL);
                break;
            case DOUBLE:
            case BIG_DECIMAL:
            case FLOAT:
                list.add(ScaleType.RATIO);
                list.add(ScaleType.INTERVAL);
                list.add(ScaleType.ORDINAL);
                list.add(ScaleType.NOMINAL);
                break;
            case INTEGER:
            case LONG:
            case SHORT:
            case BIG_INTEGER:
                list.add(ScaleType.INTERVAL);
                list.add(ScaleType.ORDINAL);
                list.add(ScaleType.NOMINAL);
                break;
            case STRING:
            default:
                list.add(ScaleType.NOMINAL);

        }

        return list;
    }

    /**
     * Copies a feature and its properties, validating at the same time. For
     * example scale type and data type are checked for consistency.
     * 
     * @param feature
     *            feature to copy
     * @return copy
     * @throws DatasetException
     *             if the feature is invalid
     */
    public static final Feature copyFeature(Feature feature) throws DatasetException {
        Feature newFeature = new FeaturePojo(feature);

        return newFeature;
    }
}