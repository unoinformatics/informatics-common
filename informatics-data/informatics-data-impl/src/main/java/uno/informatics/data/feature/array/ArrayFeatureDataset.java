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

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import uno.informatics.common.ConversionException;
import uno.informatics.common.ConversionUtilities;
import uno.informatics.common.io.FileType;
import uno.informatics.common.io.IOUtilities;
import uno.informatics.common.io.RowReader;
import uno.informatics.common.io.text.TextFileRowReader;
import uno.informatics.data.DataType;
import uno.informatics.data.DataTypeConstants;
import uno.informatics.data.Entity;
import uno.informatics.data.Feature;
import uno.informatics.data.FeatureDataset;
import uno.informatics.data.FeatureDatasetRow;
import uno.informatics.data.ScaleType;
import uno.informatics.data.SimpleEntity;
import uno.informatics.data.Study;
import uno.informatics.data.dataset.DatasetException;
import uno.informatics.data.feature.AbstractFeatureDataset;
import uno.informatics.data.pojo.FeaturePojo;
import uno.informatics.data.pojo.MethodPojo;
import uno.informatics.data.pojo.ScalePojo;
import uno.informatics.data.pojo.SimpleEntityPojo;
import uno.informatics.data.utils.DatasetUtils;

/**
 * @author Guy Davenport
 *
 */
public class ArrayFeatureDataset extends AbstractFeatureDataset {
    private static final String NAME = "NAME";
    private static final String ID = "ID";
    private static final String TYPE = "TYPE";
    private static final String MIN = "MIN";
    private static final String MAX = "MAX";

    private FeatureDatasetRow[] rows;
    private int rowCount;
    private FeaturePojo rowHeaderFeature;
    private Study study;

    public ArrayFeatureDataset(String name, List<? extends Feature> features, Object[][] values) {
        super(name, features);

        setValues(values);
    }

    public ArrayFeatureDataset(String uniqueIdentifier, String name, List<? extends Feature> features,
            Object[][] values) {
        super(uniqueIdentifier, name, features);

        setValues(values);
    }

    public ArrayFeatureDataset(String uniqueIdentifier, String name, String description,
            List<? extends Feature> features, Object[][] values) {
        super(uniqueIdentifier, name, description, features);

        setValues(values);
    }

    public ArrayFeatureDataset(Entity identification, List<? extends Feature> features, Object[][] values) {
        super(identification, features);

        setValues(values);
    }

    public ArrayFeatureDataset(String name, List<? extends Feature> features, Object[][] values,
            Feature rowHeaderFeature) {
        super(name, features);

        setRowHeaderFeature(rowHeaderFeature);

        setValuesWithHeaders(values);
    }

    public ArrayFeatureDataset(String uniqueIdentifier, String name, List<? extends Feature> features,
            Object[][] values, Feature rowHeaderFeature) {
        super(uniqueIdentifier, name, features);

        setRowHeaderFeature(rowHeaderFeature);

        setValuesWithHeaders(values);
    }

    public ArrayFeatureDataset(String uniqueIdentifier, String name, String description,
            List<? extends Feature> features, Object[][] values, Feature rowHeaderFeature) {
        super(uniqueIdentifier, name, description, features);

        setRowHeaderFeature(rowHeaderFeature);

        setValuesWithHeaders(values);
    }

    public ArrayFeatureDataset(Entity identification, List<? extends Feature> features, Object[][] values,
            Feature rowHeaderFeature) {
        super(identification, features);

        setRowHeaderFeature(rowHeaderFeature);

        setValuesWithHeaders(values);
    }

    public ArrayFeatureDataset(String name, List<? extends Feature> features, SimpleEntity[] headers, Object[][] values,
            Feature rowHeaderFeature) {
        super(name, features);

        setRowHeaderFeature(rowHeaderFeature);

        setValuesWithHeaders(headers, values);
    }

    public ArrayFeatureDataset(String uniqueIdentifier, String name, List<? extends Feature> features,
            SimpleEntity[] headers, Object[][] values, Feature rowHeaderFeature) {
        super(uniqueIdentifier, name, features);

        setRowHeaderFeature(rowHeaderFeature);

        setValuesWithHeaders(headers, values);
    }

    public ArrayFeatureDataset(String uniqueIdentifier, String name, String description,
            List<? extends Feature> features, SimpleEntity[] headers, Object[][] values, Feature rowHeaderFeature) {
        super(uniqueIdentifier, name, description, features);

        setRowHeaderFeature(rowHeaderFeature);

        setValuesWithHeaders(headers, values);
    }

    public ArrayFeatureDataset(Entity identification, List<? extends Feature> features, SimpleEntity[] headers,
            Object[][] values, Feature rowHeaderFeature) {
        super(identification, features);

        setRowHeaderFeature(rowHeaderFeature);

        setValuesWithHeaders(headers, values);
    }

    public ArrayFeatureDataset(String name, List<? extends Feature> features, SimpleEntity[] headers, String[] rowNames,
            Object[][] values, Feature rowHeaderFeature) {
        super(name, features);

        setRowHeaderFeature(rowHeaderFeature);

        setValuesWithHeaders(headers, rowNames, values);
    }

    public ArrayFeatureDataset(String uniqueIdentifier, String name, List<? extends Feature> features,
            SimpleEntity[] headers, String[] rowNames, Object[][] values, Feature rowHeaderFeature) {
        super(uniqueIdentifier, name, features);

        setRowHeaderFeature(rowHeaderFeature);

        setValuesWithHeaders(headers, rowNames, values);
    }

    public ArrayFeatureDataset(String uniqueIdentifier, String name, String description,
            List<? extends Feature> features, SimpleEntity[] headers, String[] rowNames, Object[][] values,
            Feature rowHeaderFeature) {
        super(uniqueIdentifier, name, description, features);

        setRowHeaderFeature(rowHeaderFeature);

        setValuesWithHeaders(headers, rowNames, values);
    }

    public ArrayFeatureDataset(Entity identification, List<? extends Feature> features, SimpleEntity[] headers,
            String[] rowNames, Object[][] values, Feature rowHeaderFeature) {
        super(identification, features);

        setRowHeaderFeature(rowHeaderFeature);

        setValuesWithHeaders(headers, rowNames, values);
    }

    public ArrayFeatureDataset(String name, List<? extends Feature> features, List<List<Object>> values) {
        super(name, features);

        setValues(values);
    }

    public ArrayFeatureDataset(String uniqueIdentifier, String name, List<? extends Feature> features,
            List<List<Object>> values) {
        super(uniqueIdentifier, name, features);

        setValues(values);
    }

    public ArrayFeatureDataset(String uniqueIdentifier, String name, String description,
            List<? extends Feature> features, List<List<Object>> values) {
        super(uniqueIdentifier, name, description, features);

        setValues(values);
    }

    public ArrayFeatureDataset(Entity identification, List<? extends Feature> features, List<List<Object>> values) {
        super(identification != null ? identification.getUniqueIdentifier() : null,
                identification != null ? identification.getName() : null,
                identification != null ? identification.getDescription() : null, features);

        setValues(values);
    }

    public ArrayFeatureDataset(String name, List<? extends Feature> features, List<List<Object>> values,
            Feature rowHeaderFeature) {
        super(name, features);

        setRowHeaderFeature(rowHeaderFeature);

        if (rowHeaderFeature != null)
            setValuesWithHeaders(values);
        else
            setValues(values);
    }

    public ArrayFeatureDataset(String uniqueIdentifier, String name, List<? extends Feature> features,
            List<List<Object>> values, Feature rowHeaderFeature) {
        super(uniqueIdentifier, name, features);

        setRowHeaderFeature(rowHeaderFeature);

        if (rowHeaderFeature != null)
            setValuesWithHeaders(values);
        else
            setValues(values);
    }

    public ArrayFeatureDataset(String uniqueIdentifier, String name, String description,
            List<? extends Feature> features, List<List<Object>> values, Feature rowHeaderFeature) {
        super(uniqueIdentifier, name, description, features);

        setRowHeaderFeature(rowHeaderFeature);

        if (rowHeaderFeature != null)
            setValuesWithHeaders(values);
        else
            setValues(values);
    }

    public ArrayFeatureDataset(Entity identification, List<? extends Feature> features, List<List<Object>> values,
            Feature rowHeaderFeature) {
        super(identification != null ? identification.getUniqueIdentifier() : null,
                identification != null ? identification.getName() : null,
                identification != null ? identification.getDescription() : null, features);

        setRowHeaderFeature(rowHeaderFeature);

        if (rowHeaderFeature != null)
            setValuesWithHeaders(values);
        else
            setValues(values);
    }

    public ArrayFeatureDataset(String name, List<? extends Feature> features, List<SimpleEntity> headers,
            List<List<Object>> values, Feature rowHeaderFeature) {
        super(name, features);

        setRowHeaderFeature(rowHeaderFeature);

        setValuesWithHeaders(headers, values);
    }

    public ArrayFeatureDataset(String uniqueIdentifier, String name, List<? extends Feature> features,
            List<SimpleEntity> headers, List<List<Object>> values, Feature rowHeaderFeature) {
        super(uniqueIdentifier, name, features);

        setRowHeaderFeature(rowHeaderFeature);

        setValuesWithHeaders(headers, values);
    }

    public ArrayFeatureDataset(String uniqueIdentifier, String name, String description,
            List<? extends Feature> features, List<SimpleEntity> headers, List<List<Object>> values,
            Feature rowHeaderFeature) {
        super(uniqueIdentifier, name, description, features);

        setRowHeaderFeature(rowHeaderFeature);

        setValuesWithHeaders(headers, values);
    }

    public ArrayFeatureDataset(Entity identification, List<? extends Feature> features, List<SimpleEntity> headers,
            List<List<Object>> values, Feature rowHeaderFeature) {
        super(identification != null ? identification.getUniqueIdentifier() : null,
                identification != null ? identification.getName() : null,
                identification != null ? identification.getDescription() : null, features);

        setRowHeaderFeature(rowHeaderFeature);

        setValuesWithHeaders(headers, values);
    }

    @Override
    public final Study getStudy() {
        return study;
    }

    public synchronized final void setStudy(Study study) {
        this.study = study;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.data.tests.feature.array.array.AbstractDataset#
     * getRowCount()
     */
    @Override
    public final int getRowCount() {
        return rowCount;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * uno.informatics.data.tests.feature.array.array.AbstractDataset#getRow()
     */
    @Override
    public FeatureDatasetRow getRow(int rowIndex) {
        return rows[rowIndex];
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.data.tests.feature.array.array.AbstractDataset#
     * getRowsAsArray()
     */
    @Override
    public final FeatureDatasetRow[] getRowsAsArray() {
        return rows;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * uno.informatics.data.tests.feature.array.array.AbstractDataset#getRows()
     */
    @Override
    public final List<FeatureDatasetRow> getRows() {
        return toRowList(rows);
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.data.feature.FeatureDataset#getValues()
     */
    @Override
    public List<List<Object>> getValues() {
        return toObjectListList(rows);
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.data.feature.FeatureDataset#getValuesAsArray()
     */
    @Override
    public Object[][] getValuesAsArray() {
        return toObjectArray(rows);
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.data.dataset.FeatureDataset#getRowHeaderFeature()
     */
    @Override
    public Feature getRowHeaderFeature() {
        return rowHeaderFeature;
    }

    public final void setRowHeaderFeature(Feature rowHeaderFeature) {
        if (rowHeaderFeature != null)
            this.rowHeaderFeature = new FeaturePojo(rowHeaderFeature);
        else
            this.rowHeaderFeature = null;
    }

    public static final FeatureDataset readFeatureDatasetFromTextFile(File file, FileType fileType)
            throws DatasetException {
        RowReader reader = null;
        List<List<Object>> rowList = new LinkedList<List<Object>>();

        if (file == null)
            throw new DatasetException("File not defined!");

        if (fileType == null)
            throw new DatasetException("File type not defined!");

        if (!file.exists())
            throw new DatasetException("File does not exist : " + file);

        try {
            reader = IOUtilities.createRowReader(file, fileType, TextFileRowReader.ROWS_SAME_SIZE);

            if (reader != null && reader.ready()) {
                int columnCount = 0;
                int row = 0;

                boolean hasNames = false;
                boolean hasIDs = false;

                List<String> cells;
                List<String> typeCells;
                List<String> minCells;
                List<String> maxCells;
                String rowName;
                String rowID;
                List<SimpleEntity> rowHeaders = null;
                List<String> columnNames;
                List<FeaturePojo> newFeatures = null;
                int[] types = null;
                FeaturePojo rowHeaderFeature = null;

                Iterator<String> iterator;

                if (reader.nextRow()) {
                    // assume that the first row is the header
                    cells = reader.getRowCellsAsString();

                    if (cells.size() > 0) {
                        iterator = cells.iterator();

                        if (NAME.equals(cells.get(0))) {
                            hasNames = true;
                            rowHeaders = new LinkedList<SimpleEntity>();

                            iterator.next();

                            if (cells.size() > 1 && ID.equals(cells.get(1))) {
                                hasIDs = true;

                                columnCount = cells.size() - 2;

                                iterator.next();

                                rowHeaderFeature = new FeaturePojo("Ids", new MethodPojo("Ids",
                                        new ScalePojo("Ids", DataType.STRING, ScaleType.NOMINAL)));
                            } else {
                                columnCount = cells.size() - 1;

                                rowHeaderFeature = new FeaturePojo("Names", new MethodPojo("Names",
                                        new ScalePojo("Names", DataType.STRING, ScaleType.NOMINAL)));
                            }
                        } else {
                            columnCount = cells.size();
                        }

                        columnNames = new ArrayList<String>(columnCount);

                        while (iterator.hasNext()) {
                            columnNames.add(iterator.next());
                        }
                    } else {
                        columnCount = 0;
                        columnNames = null;
                    }

                    if (columnCount > 0 && reader.nextRow()) {
                        if (hasNames && reader.nextColumn())
                            rowName = reader.getCellAsString();
                        else
                            rowName = null;

                        if (hasIDs && reader.nextColumn())
                            rowID = reader.getCellAsString();
                        else
                            rowID = null;

                        reader.nextColumn();

                        cells = reader.getRowCellsAsString();

                        if (TYPE.equals(rowName)) {
                            if (reader.nextRow()) {
                                if (hasNames && reader.nextColumn())
                                    rowName = reader.getCellAsString();
                                else
                                    rowName = null;

                                if (hasIDs && reader.nextColumn())
                                    rowID = reader.getCellAsString();
                                else
                                    rowID = null;

                                typeCells = cells;

                                reader.nextColumn();

                                cells = reader.getRowCellsAsString();

                                if (MIN.equals(rowName)) {
                                    minCells = cells;

                                    if (reader.nextRow()) {
                                        if (hasNames && reader.nextColumn())
                                            rowName = reader.getCellAsString();
                                        else
                                            rowName = null;

                                        if (hasIDs && reader.nextColumn())
                                            rowID = reader.getCellAsString();
                                        else
                                            rowID = null;

                                        reader.nextColumn();

                                        cells = reader.getRowCellsAsString();

                                        if (MAX.equals(rowName)) {
                                            maxCells = cells;

                                            newFeatures = createFeatures(columnNames, typeCells, minCells, maxCells);

                                            types = DatasetUtils.getConversionTypes(newFeatures);
                                        } else {
                                            addHeaders(rowHeaderFeature, rowName, rowID, rowHeaders);

                                            newFeatures = createFeatures(columnNames, typeCells, minCells,
                                                    new LinkedList<String>());

                                            types = DatasetUtils.getConversionTypes(newFeatures);

                                            addValues(rowList, cells, newFeatures, types);
                                        }
                                    }
                                } else {
                                    newFeatures = createFeatures(columnNames, typeCells, new LinkedList<String>(),
                                            new LinkedList<String>());

                                    types = DatasetUtils.getConversionTypes(newFeatures);

                                    addHeaders(rowHeaderFeature, rowName, rowID, rowHeaders);

                                    addValues(rowList, cells, newFeatures, types);
                                }
                            }
                        } else {
                            newFeatures = createDefaultFeatures(columnNames);

                            types = DatasetUtils.getConversionTypes(newFeatures);

                            addHeaders(rowHeaderFeature, rowName, rowID, rowHeaders);

                            addValues(rowList, cells, newFeatures, types);
                        }

                    }

                    ++row;

                    while (reader.nextRow()) {
                        if (hasNames && reader.nextColumn())
                            rowName = reader.getCellAsString();
                        else
                            rowName = null;

                        if (hasIDs && reader.nextColumn())
                            rowID = reader.getCellAsString();
                        else
                            rowID = null;

                        addHeaders(rowHeaderFeature, rowName, rowID, rowHeaders);

                        if (reader.nextColumn()) {
                            cells = reader.getRowCellsAsString();

                            if (cells.size() != columnCount)
                                throw new DatasetException("Rows are not all the same size in row : " + row
                                        + " expected " + columnCount + " but was " + cells.size() + " !");

                            addValues(rowList, cells, newFeatures, types);
                        }

                        ++row;
                    }
                }

                if (reader != null)
                    reader.close();

                if (hasNames)
                    return new ArrayFeatureDataset(file.getName(), file.getName(), createDescription(file), newFeatures,
                            rowHeaders, rowList, rowHeaderFeature);
                else
                    return new ArrayFeatureDataset(file.getName(), file.getName(), createDescription(file), newFeatures,
                            rowList, rowHeaderFeature);
            }

            return null;

        } catch (Exception e) {
            throw new DatasetException(e);
        }
    }

    private static void addValues(List<List<Object>> rowList, List<String> cells, List<FeaturePojo> features,
            int[] types) throws ConversionException {
        List<Object> values = convertCells(cells, types);

        rowList.add(values);

        updateRowScales(features, values);
    }

    private static void addHeaders(FeaturePojo rowHeaderFeature, String rowName, String rowId,
            List<SimpleEntity> rowHeaders) {
        if (rowName != null) {
            if (rowId != null) {
                SimpleEntityPojo simpleEntityPojo = new SimpleEntityPojo(rowId, rowName);
                rowHeaders.add(simpleEntityPojo);

                if (rowHeaderFeature != null)
                    updateRowHeaderScale(rowHeaderFeature, simpleEntityPojo);
            } else {
                SimpleEntityPojo simpleEntityPojo = new SimpleEntityPojo(rowName);
                rowHeaders.add(simpleEntityPojo);

                if (rowHeaderFeature != null)
                    updateRowHeaderScale(rowHeaderFeature, simpleEntityPojo);
            }
        }
    }

    private static String createDescription(File file) {
        return "Dataset loading from " + file.getAbsolutePath();
    }

    private static List<FeaturePojo> createFeatures(List<String> columnNames, List<String> typeCells,
            List<String> minCells, List<String> maxCells) throws DatasetException {
        List<FeaturePojo> features = new ArrayList<FeaturePojo>(columnNames.size());

        Iterator<String> iterator1 = columnNames.iterator();
        Iterator<String> iterator2 = typeCells.iterator();
        Iterator<String> iterator3 = minCells.iterator();
        Iterator<String> iterator4 = maxCells.iterator();

        while (iterator1.hasNext())
            features.add(createFeaure(iterator1.next(), iterator2.hasNext() ? iterator2.next() : null,
                    iterator3.hasNext() ? iterator3.next() : null, iterator4.hasNext() ? iterator4.next() : null));

        return features;
    }

    private static FeaturePojo createFeaure(String name, String type, String min, String max) throws DatasetException {
        try {
            FeaturePojo feature = new FeaturePojo(name);

            ScalePojo scale;

            DataType dataType = type != null && type.length() > 0 ? getDataTypeByAbbreviation(type.substring(1, 2))
                    : DataType.STRING;
            ScaleType scaleType = type != null && type.length() > 1 ? getScaleTypeByAbbreviation(type.substring(0, 1))
                    : ScaleType.NOMINAL;

            Number minNumber = null;
            Number maxNumber = null;

            if (min != null)
                minNumber = ConversionUtilities.convertToNumber(min, DataTypeConstants.getConversionType(dataType));

            if (max != null)
                maxNumber = ConversionUtilities.convertToNumber(max, DataTypeConstants.getConversionType(dataType));

            if (minNumber != null || maxNumber != null)
                scale = new ScalePojo(name, dataType, scaleType, minNumber, maxNumber);
            else
                scale = new ScalePojo(name, dataType, scaleType);

            feature.setMethod(new MethodPojo(name, scale));

            return feature;
        } catch (ConversionException e) {
            throw new DatasetException(e);
        }
    }

    private static DataType getDataTypeByAbbreviation(String type) throws DatasetException {
        DataType dataType = DataType.getTypeByAbbreviation(type);

        if (dataType == null)
            throw new DatasetException("Unknown data type abbeviation : '" + type + "'");

        return dataType;
    }

    private static ScaleType getScaleTypeByAbbreviation(String type) throws DatasetException {
        ScaleType scaleType = ScaleType.getTypeByAbbreviation(type);

        if (scaleType == null)
            throw new DatasetException("Unknown scale abbeviation : '" + type + "'");

        return scaleType;
    }

    private static List<FeaturePojo> createDefaultFeatures(List<String> cells) {
        List<FeaturePojo> features = new ArrayList<FeaturePojo>(cells.size());

        Iterator<String> iterator = cells.iterator();

        while (iterator.hasNext())
            features.add(createDefaultFeaure(iterator.next()));

        return features;
    }

    private static FeaturePojo createDefaultFeaure(String name) {
        FeaturePojo feature = new FeaturePojo(name);

        ScalePojo scale = new ScalePojo(name);

        scale.setDataType(DataType.STRING);
        scale.setScaleType(ScaleType.NOMINAL);

        feature.setMethod(new MethodPojo(name, scale));

        return feature;
    }

    public static final FeatureDataset createSubsetFeatureDataset(String uniqueIdentifier, String name,
            String description, FeatureDataset dataset, Set<Integer> indices) throws DatasetException {
        Iterator<Integer> iterator = indices.iterator();

        Object[][] values = new Object[indices.size()][dataset.getFeatures().size()];

        int i = 0;

        while (iterator.hasNext()) {
            values[i] = dataset.getRow(iterator.next()).getValuesAsArray();
        }

        return new ArrayFeatureDataset(uniqueIdentifier, name, description, dataset.getFeatures(), values);
    }

    public static final FeatureDataset createSubsetFeatureDataset(String uniqueIdentifier, String name,
            String description, FeatureDataset dataset, int[] indices) throws DatasetException {
        Object[][] values = new Object[indices.length][dataset.getFeatures().size()];

        for (int i = 0; i < indices.length; ++i) {
            values[i] = dataset.getRow(indices[i]).getValuesAsArray();
        }

        return new ArrayFeatureDataset(uniqueIdentifier, name, description, dataset.getFeatures(), values);
    }

    /**
     * @param rowHeaderFeature
     * @param features
     * @param next
     */
    private static void updateRowScales(List<FeaturePojo> features, List<Object> row) {
        Iterator<FeaturePojo> iterator = features.iterator();

        Iterator<Object> cell = row.iterator();

        while (iterator.hasNext() && cell.hasNext()) {
            updateScale((ScalePojo) iterator.next().getMethod().getScale(), cell.next());
        }
    }

    private static void updateRowHeaderScale(FeaturePojo rowHeaderFeature, SimpleEntity header) {
        updateScale((ScalePojo) rowHeaderFeature.getMethod().getScale(), header);
    }

    /**
     * @param scale
     * @param next
     */
    private static void updateScale(ScalePojo scale, Object value) {
        if (ScaleType.NOMINAL.equals(scale.getScaleType()) || ScaleType.ORDINAL.equals(scale.getScaleType())
                || ScaleType.INTERVAL.equals(scale.getScaleType())) {
            scale.addValue(value);
        }

        if (ScaleType.ORDINAL.equals(scale.getScaleType()) || ScaleType.INTERVAL.equals(scale.getScaleType())
                || ScaleType.RATIO.equals(scale.getScaleType())) {
            if (value instanceof Number) {
                if (scale.getMinimumValue() == null
                        || scale.getMinimumValue().doubleValue() > ((Number) value).doubleValue())
                    scale.setMinimumValue((Number) value);

                if (scale.getMaximumValue() == null
                        || scale.getMaximumValue().doubleValue() < ((Number) value).doubleValue())
                    scale.setMaximumValue((Number) value);

            }
        }
    }

    /**
     * @param cells
     * @return
     * @throws ConversionException
     */
    private static List<Object> convertCells(List<String> cells, int[] types) throws ConversionException {
        return ConversionUtilities.convertToObjectList(cells, types);
    }

    /**
     * @param values
     * @throws DatasetException
     */
    private final void setValues(Object[][] values) throws IllegalArgumentException {
        rowCount = 0;

        if (values != null) {
            rowCount = values.length;
            int columnCount = getFeatures().size();

            rows = new FeatureDatasetRow[rowCount];

            for (int i = 0; i < values.length; ++i) {
                if (columnCount != values[i].length)
                    throw new IllegalArgumentException("Row : " + i + " size : " + values[i].length
                            + " does not match the number of feaures : " + columnCount);

                rows[i] = new ArrayFeatureDatasetRow(values[i]);
            }
        }
    }

    /**
     * @param values
     * @throws DatasetException
     */
    private final void setValuesWithHeaders(Object[][] values) throws IllegalArgumentException {
        rowCount = 0;

        if (rowHeaderFeature != null)
            this.rowHeaderFeature = new FeaturePojo(rowHeaderFeature);

        if (values != null) {
            rowCount = values.length;

            int columnCount = getFeatures().size();

            rows = new FeatureDatasetRow[rowCount];

            for (int i = 0; i < values.length; ++i) {
                if (columnCount != values[i].length - 1)
                    throw new IllegalArgumentException("Row : " + i + " size : " + (values[i].length - 1)
                            + " does not match the number of feaures : " + columnCount);

                rows[i] = ArrayFeatureDatasetRow.createRow(true, values[i]);
            }
        }
    }

    /**
     * @param values
     * @throws DatasetException
     */
    private final void setValuesWithHeaders(SimpleEntity[] headers, Object[][] values) throws IllegalArgumentException {
        rowCount = 0;

        if (rowHeaderFeature != null)
            this.rowHeaderFeature = new FeaturePojo(rowHeaderFeature);

        if (headers != null && values != null) {
            rowCount = values.length;

            if (headers.length != values.length)
                throw new IllegalArgumentException("Number of headers does not match number of rows!");

            int columnCount = getFeatures().size();

            rows = new FeatureDatasetRow[rowCount];

            for (int i = 0; i < values.length; ++i) {
                if (columnCount != values[i].length - 1)
                    throw new IllegalArgumentException("Row : " + i + " size : " + (values[i].length - 1)
                            + " does not match the number of feaures : " + columnCount);

                rows[i] = ArrayFeatureDatasetRow.createRow(headers[i], values[i]);
            }
        }
    }

    /**
     * @param values
     * @throws DatasetException
     */
    private final void setValuesWithHeaders(SimpleEntity[] headers, String[] rowNames, Object[][] values)
            throws IllegalArgumentException {
        rowCount = 0;

        if (rowHeaderFeature != null)
            this.rowHeaderFeature = new FeaturePojo(rowHeaderFeature);

        if (headers != null && values != null) {
            rowCount = values.length;

            if (headers.length != values.length)
                throw new IllegalArgumentException("Number of headers does not match number of rows!");

            if (rowNames.length != values.length)
                throw new IllegalArgumentException("Number of row names does not match number of rows!");

            int columnCount = getFeatures().size();

            rows = new FeatureDatasetRow[rowCount];

            for (int i = 0; i < values.length; ++i) {
                if (columnCount != values[i].length - 1)
                    throw new IllegalArgumentException("Row : " + i + " size : " + (values[i].length - 1)
                            + " does not match the number of feaures : " + columnCount);

                rows[i] = ArrayFeatureDatasetRow.createRow(headers[i], values[i]);
            }
        }
    }

    /**
     * @param values
     */
    private final void setValues(List<List<Object>> values) {
        rowCount = 0;

        if (values != null) {
            rowCount = values.size();

            rows = new FeatureDatasetRow[rowCount];

            Iterator<List<Object>> iterator = values.iterator();

            int i = 0;

            while (iterator.hasNext()) {
                rows[i] = new ArrayFeatureDatasetRow(iterator.next());
                ++i;
            }
        }
    }

    /**
     * @param values
     */
    private final void setValuesWithHeaders(List<List<Object>> values) {
        rowCount = 0;

        if (values != null) {
            rowCount = values.size();

            rows = new FeatureDatasetRow[rowCount];

            Iterator<List<Object>> iterator = values.iterator();

            int i = 0;

            while (iterator.hasNext()) {
                rows[i] = ArrayFeatureDatasetRow.createRow(true, iterator.next());
                ++i;
            }
        }
    }

    /**
     * @param values
     */
    private final void setValuesWithHeaders(List<SimpleEntity> headers, List<List<Object>> values) {
        rowCount = 0;

        if (values != null) {
            rowCount = values.size();

            if (headers == null)
                throw new IllegalArgumentException("Headers not defined whereas row count is : " + rowCount);

            if (headers.size() != values.size())
                throw new IllegalArgumentException(
                        "Number of headers : " + headers.size() + " does not match row count : " + rowCount);

            rows = new FeatureDatasetRow[rowCount];

            Iterator<List<Object>> iterator1 = values.iterator();
            Iterator<SimpleEntity> iterator2 = headers.iterator();

            int i = 0;

            while (iterator1.hasNext()) {
                rows[i] = ArrayFeatureDatasetRow.createRow(iterator2.hasNext() ? iterator2.next() : null,
                        iterator1.next());
                ++i;
            }
        }
    }

    private List<FeatureDatasetRow> toRowList(FeatureDatasetRow[] array) {
        List<FeatureDatasetRow> list = new ArrayList<FeatureDatasetRow>(array.length);

        for (int i = 0; i < array.length; ++i)
            list.add(array[i]);

        return list;
    }

    private List<List<Object>> toObjectListList(FeatureDatasetRow[] array) {
        List<List<Object>> list = new ArrayList<List<Object>>(array.length);

        for (int i = 0; i < array.length; ++i)
            list.add(toObjectList(array[i].getValuesAsArray()));

        return list;
    }

    private List<Object> toObjectList(Object[] array) {
        List<Object> list = new ArrayList<Object>(array.length);

        for (int i = 0; i < array.length; ++i)
            list.add(array[i]);

        return list;
    }

    private Object[][] toObjectArray(FeatureDatasetRow[] array) {
        Object[][] list = new Object[array.length][];

        for (int i = 0; i < array.length; ++i)
            list[i] = array[i].getValuesAsArray();

        return list;
    }
}
