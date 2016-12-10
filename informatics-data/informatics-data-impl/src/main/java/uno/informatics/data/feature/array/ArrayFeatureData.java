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

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import uno.informatics.common.ConversionException;
import uno.informatics.common.ConversionUtilities;
import uno.informatics.common.io.IOUtilities;
import uno.informatics.common.io.RowReader;
import uno.informatics.common.io.RowWriter;
import uno.informatics.common.io.text.TextFileRowReader;
import uno.informatics.data.DataType;
import uno.informatics.data.DataTypeConstants;
import uno.informatics.data.Feature;
import uno.informatics.data.Scale;
import uno.informatics.data.ScaleType;
import uno.informatics.data.SimpleEntity;
import uno.informatics.data.dataset.DatasetException;
import uno.informatics.data.dataset.FeatureData;
import uno.informatics.data.dataset.FeatureDataRow;
import uno.informatics.data.feature.AbstractFeatureData;
import uno.informatics.data.io.FileType;
import uno.informatics.data.pojo.DataPojo;
import uno.informatics.data.pojo.FeaturePojo;
import uno.informatics.data.pojo.MethodPojo;
import uno.informatics.data.pojo.ScalePojo;
import uno.informatics.data.pojo.SimpleEntityPojo;
import uno.informatics.data.utils.DataOption;
import uno.informatics.data.utils.DatasetUtils;

/**
 * @author Guy Davenport
 *
 */
public class ArrayFeatureData extends AbstractFeatureData {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private FeatureDataRow[] rows;
    private int rowCount;

    // item IDs (0..n-1)
    private Set<Integer> ids;
    private SimpleEntity[] rowHeaders;

    public ArrayFeatureData(String name, Feature[] features, Object[][] values) {
        super(name, features);

        setValuesWithHeaders(values);
    }

    public ArrayFeatureData(String uniqueIdentifier, String name, Feature[] features,
            Object[][] values) {
        super(uniqueIdentifier, name, features);

        setValuesWithHeaders(values);
    }

    public ArrayFeatureData(String name, List<? extends Feature> features, SimpleEntity[] rowHeaders,
            Object[][] values) {
        super(name, features);

        setValuesWithHeaders(rowHeaders, values);
    }

    public ArrayFeatureData(String uniqueIdentifier, String name, List<? extends Feature> features,
            SimpleEntity[] rowHeaders, Object[][] values) {
        super(uniqueIdentifier, name, features);

        setValuesWithHeaders(rowHeaders, values);
    }

    public ArrayFeatureData(String name, List<? extends Feature> features, List<List<Object>> values) {
        super(name, features);

        setValuesWithHeaders(values);
    }

    public ArrayFeatureData(String uniqueIdentifier, String name, List<? extends Feature> features,
            List<List<Object>> values) {
        super(uniqueIdentifier, name, features);

        setValuesWithHeaders(values);
    }

    public ArrayFeatureData(String name, List<? extends Feature> features, List<SimpleEntity> rowHeaders,
            List<List<Object>> values) {
        super(name, features);

        setValuesWithHeaders(rowHeaders, values);
    }

    public ArrayFeatureData(String uniqueIdentifier, String name, List<? extends Feature> features,
            List<SimpleEntity> rowHeaders, List<List<Object>> values) {
        super(uniqueIdentifier, name, features);

        setValuesWithHeaders(rowHeaders, values);
    }
    
    @Override
    public final int getRowCount() {
        return rowCount;
    }

    @Override
    public FeatureDataRow getRow(int rowIndex) {
        return rows[rowIndex];
    }

    @Override
    public final FeatureDataRow[] getRowsAsArray() {
        return rows;
    }

    @Override
    public final List<FeatureDataRow> getRows() {
        return toRowList(rows);
    }

    @Override
    public List<List<Object>> getValues() {
        return toObjectListList(rows);
    }

    @Override
    public Object[][] getValuesAsArray() {
        return toObjectArray(rows);
    }
    
    @Override
    public List<SimpleEntity> getRowHeaders(){
        return toObjectList(rowHeaders); 
    }
    
    @Override
    public SimpleEntity[] getRowHeadersAsArray(){
        return rowHeaders ;
    }
    
    public static final ArrayFeatureData readData(Path filePath, FileType type, DataOption... options)
            throws IOException {

        ArrayFeatureData data = null;

        RowReader reader = null;
        List<List<Object>> rowList = new LinkedList<>();

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
        
        if (name == null){
            name = filePath.getFileName().toString() ;
        }
            
        // TODO extract options and pass to reader
        try {
            reader = IOUtilities.createRowReader(filePath, type, TextFileRowReader.ROWS_SAME_SIZE,
                    TextFileRowReader.REMOVE_WHITE_SPACE);

            if (reader != null && reader.ready()) {
                int columnCount = 0;
                int row = 0;

                boolean hasRowNames = false;
                
                List<String> cells;
                List<String> typeCells;
                List<String> minCells;
                List<String> maxCells;
                
                String rowId;
                String rowName;
                List<SimpleEntity> rowHeaders = new LinkedList<>();
                List<String> columnIDs;
                List<String> columnNames;
                List<FeaturePojo> newFeatures = null;
                int[] types = null;

                Iterator<String> iterator;

                if (reader.nextRow()) {
                    // assume that the first row is the column headers
                    cells = reader.getRowCellsAsString();

                    if (cells.size() > 0) {
                        iterator = cells.iterator();

                        if (ID.equals(cells.get(0))) {

                            iterator.next();

                            if (cells.size() > 1 && NAME.equals(cells.get(1))) {
                                hasRowNames = true;

                                columnCount = cells.size() - 2;

                                iterator.next();
                            } else {
                                columnCount = cells.size() - 1;
                            }
                        } else {
                            if (uniqueIdentifier != null){
                                throw new IllegalArgumentException("Using ID DataOption: First cell must be " + ID);
                            }
                            
                            uniqueIdentifier = cells.get(0);
                            
                            columnCount = cells.size();
                        }

                        columnIDs = new ArrayList<>(columnCount);
                        columnNames = new ArrayList<>(columnCount);

                        while (iterator.hasNext()) {
                            columnIDs.add(iterator.next());
                        }
                    } else {
                        throw new IllegalArgumentException("First row is empty!");
                    }

                    if (reader.nextRow()) {
                        ++row ;
                        
                        if (reader.nextColumn()){
                            rowId = reader.getCellAsString();
                        } else {
                            throw new IllegalArgumentException(String.format("First cell in row %d not found!", row));
                        }

                        if (hasRowNames && reader.nextColumn()){
                            rowName = reader.getCellAsString();
                        } else {
                            rowName = null;
                        }

                        reader.nextColumn();

                        cells = reader.getRowCellsAsString();
                        
                        if (NAME.equals(rowId)) {
                            
                            columnNames.addAll(cells) ;

                            if (name != null && rowName != null) {
                                throw new IllegalArgumentException("Using NAME DataOption: Second cell must be empty!");
                            }
     
                            if (reader.nextRow()) {
                                ++row ;
                                
                                if (reader.nextColumn()){
                                    rowId = reader.getCellAsString();
                                } else {
                                    throw new IllegalArgumentException(
                                            String.format("First cell in row %d not found!", row)
                                    );
                                }

                                if (hasRowNames && reader.nextColumn()){
                                    rowName = reader.getCellAsString();
                                } else {
                                    rowName = null;
                                }

                                reader.nextColumn();

                                cells = reader.getRowCellsAsString();
                            }

                        } else {
                            // use IDs as column names
                            columnNames.addAll(columnIDs) ;
                        }
                            
                        if (TYPE.equals(rowId)) {

                            typeCells = cells;

                            if (reader.nextRow()) {
                                ++row ;

                                if (reader.nextColumn()){
                                    rowId = reader.getCellAsString();
                                } else {
                                    throw new IllegalArgumentException(
                                            String.format("First cell in row %d not found!", row)
                                    );
                                }

                                if (hasRowNames && reader.nextColumn()){
                                    rowName = reader.getCellAsString();
                                } else {
                                    rowName = null;               
                                }

                                reader.nextColumn();

                                cells = reader.getRowCellsAsString();

                                if (MIN.equals(rowId)) {
                                    minCells = cells;

                                    if (reader.nextRow()) {
                                        ++row ;

                                        if (reader.nextColumn()){
                                            rowId = reader.getCellAsString();
                                        } else {
                                            throw new IllegalArgumentException(
                                                    String.format("First cell in row %d not found!", row)
                                            );
                                        }

                                        if (hasRowNames && reader.nextColumn()){
                                            rowName = reader.getCellAsString();
                                        } else {
                                            rowName = null;
                                        }

                                        reader.nextColumn();

                                        cells = reader.getRowCellsAsString();

                                        if (MAX.equals(rowId)) {
                                            maxCells = cells;

                                            newFeatures = createFeatures(
                                                    columnIDs, columnNames, typeCells, minCells, maxCells
                                            );

                                            types = DatasetUtils.getConversionTypes(newFeatures);
                                        } else {
                                            addHeaders(rowId, rowName, rowHeaders);

                                            newFeatures = createFeatures(
                                                    columnIDs, columnNames, typeCells, minCells, new LinkedList<>()
                                            );

                                            types = DatasetUtils.getConversionTypes(newFeatures);

                                            addValues(rowList, cells, newFeatures, types);
                                        }
                                    }
                                } else {
                                    newFeatures = createFeatures(
                                            columnIDs, columnNames, typeCells, new LinkedList<>(), new LinkedList<>()
                                    );

                                    types = DatasetUtils.getConversionTypes(newFeatures);

                                    addHeaders(rowId, rowName, rowHeaders);

                                    addValues(rowList, cells, newFeatures, types);
                                }
                            }
                        } else {
                            newFeatures = createFeatures(columnIDs, columnNames);

                            types = DatasetUtils.getConversionTypes(newFeatures);

                            addHeaders(rowId, rowName, rowHeaders);

                            addValues(rowList, cells, newFeatures, types);
                        }
                    }

                    ++row;

                    while (reader.nextRow()) {

                        if (reader.nextColumn()){
                            rowId = reader.getCellAsString();
                        } else {
                            throw new IllegalArgumentException(String.format("First cell in row %d not found!", row));
                        }

                        if (hasRowNames && reader.nextColumn()){
                            rowName = reader.getCellAsString();
                        } else {
                            rowName = null;
                        }

                        addHeaders(rowId, rowName, rowHeaders);

                        if (reader.nextColumn()) {
                            cells = reader.getRowCellsAsString();

                            if (cells.size() != columnCount){
                                throw new IOException(
                                        String.format("In row %d number of cell not as expected. "
                                                    + "Should be %d but was %d!", row, columnCount, cells.size())
                                );
                            }

                            addValues(rowList, cells, newFeatures, types);
                        }

                        ++row;
                    }
                    
                    // sort values of ordinal variables
                    if(newFeatures != null){
                        for(Feature feature : newFeatures){
                            Scale scale = feature.getMethod().getScale();
                            if(scale.getScaleType() == ScaleType.ORDINAL){
                                List<Object> values = scale.getValues();
                                values.sort(null);
                                scale.setValues(values);
                            }
                        }
                    }
                    
                }

                reader.close();
                
                if (rowList.isEmpty()){
                    throw new IOException("The data has no values!");    
                }

                data = new ArrayFeatureData(filePath.getFileName().toString(), filePath.getFileName().toString(),
                            newFeatures, rowHeaders, rowList);

                // check unique identifiers
                Set<String> uniqueIds = new HashSet<>();
                for (int i = 0; i < data.getRowCount(); i++) {
                    SimpleEntity header = data.getRow(i).getHeader();
                    if (header != null && !uniqueIds.add(header.getUniqueIdentifier())) {
                        throw new IOException(String.format(
                                "Duplicate name/id %s for item %d. "
                                        + "Names should either be unique or complemented with unique identifiers.",
                                header.getUniqueIdentifier(), i));
                    }
                }
                // check for missing identifiers
                if (!uniqueIds.isEmpty() && uniqueIds.size() < data.getRowCount()) {
                    throw new IOException("Missing names/ids. Unique identifier is "
                            + "required for items whose name is not defined.");
                }

            } else {
                throw new IOException("The data has no values!");  
            }
            

            return data;
        } catch (ConversionException | DatasetException e) {
            throw new IOException(e);
        }

    }
    
    @Override
    public final void writeData(Path filePath, FileType type)
            throws IOException {
        
        // validate arguments

        if (filePath == null) {
            throw new IllegalArgumentException("File path not defined.");
        }

        if (filePath.toFile().exists()) {
            throw new IOException("File does  exist : " + filePath + ".");
        }

        if (type == null) {
            throw new IllegalArgumentException("File type not defined.");
        }

        if (type != FileType.TXT && type != FileType.CSV) {
            throw new IllegalArgumentException(
                    String.format("Only file types TXT and CSV are supported. Got: %s.", type));
        }

        RowWriter writer = IOUtilities.createRowWriter(filePath, type, TextFileRowReader.ROWS_SAME_SIZE,
                    TextFileRowReader.REMOVE_WHITE_SPACE);
        
        
        writer.writeCell(ID);
        writer.newColumn() ;
        writer.writeCell(NAME);

        Iterator<Feature> iterator = getFeatures().iterator() ;
        
        Feature feature ;
        
        while (iterator.hasNext()){
            writer.newColumn() ;
            feature = iterator.next() ;
            writer.writeCell(feature.getUniqueIdentifier()); 
        }
        
        writer.newRow() ;
        
        writer.writeCell(NAME);
        writer.newColumn() ;

        iterator = getFeatures().iterator() ;
         
        while (iterator.hasNext()){
            writer.newColumn() ;
            feature = iterator.next() ;
            writer.writeCell(feature.getName()); 
        }
        
        writer.newRow() ;
        
        writer.writeCell(TYPE);
        writer.newColumn() ;
        
        iterator = getFeatures().iterator() ;
        
        while (iterator.hasNext()){
            writer.newColumn() ;
            feature = iterator.next() ;
            writer.writeCell(
                    feature.getMethod().getScale().getScaleType().getAbbreviation()
                  + feature.getMethod().getScale().getDataType().getAbbreviation()
            ); 
        }

        writer.newRow() ;
        
        writer.writeCell(MIN);
        writer.newColumn() ;
        
        iterator = getFeatures().iterator() ;
        
        while (iterator.hasNext()){
            writer.newColumn() ;
            feature = iterator.next() ;
            writer.writeCell(feature.getMethod().getScale().getMinimumValue()); 
        }
 
        writer.newRow() ;
        
        writer.writeCell(MAX);
        writer.newColumn() ;
        
        iterator = getFeatures().iterator() ;
        
        while (iterator.hasNext()){
            writer.newColumn() ;
            feature = iterator.next() ;
            writer.writeCell(feature.getMethod().getScale().getMaximumValue()); 
        }
   
        Iterator<FeatureDataRow> rows = getRows().iterator() ;
        
        FeatureDataRow row ;
        
        while (rows.hasNext()){
            writer.newRow() ;
            row = rows.next() ;
            writer.writeCell(row.getHeader().getUniqueIdentifier());
            writer.newColumn() ;
            writer.writeCell(row.getHeader().getName());
            writer.newColumn() ;
            writer.writeRowCells(row.getValues());
        }
           
        writer.close(); 
    }

    private static void addValues(List<List<Object>> rowList, List<String> cells, List<FeaturePojo> features,
            int[] types) throws ConversionException {
        List<Object> values = convertCells(cells, types);

        rowList.add(values);

        updateRowScales(features, values);
    }

    private static void addHeaders(String rowId, String rowName, List<SimpleEntity> rowHeaders) {
   
        if (rowId != null) {
            if (rowName != null) {
                rowHeaders.add(new SimpleEntityPojo(rowId, rowName));
            } else {
                rowHeaders.add(new SimpleEntityPojo(rowId, rowId));
            }
        } else {
            if (rowName != null) {
                rowHeaders.add(new SimpleEntityPojo(rowName, rowName));   
            } else {
                throw new IllegalArgumentException("Row id or name must be defined.");
            }
        }
    }

    private static List<FeaturePojo> createFeatures(List<String> columnIDs, List<String> columnNames,
            List<String> typeCells, List<String> minCells, List<String> maxCells) throws DatasetException {
        List<FeaturePojo> features = new ArrayList<>(columnNames.size());

        Iterator<String> iterator1 = columnIDs.iterator();
        Iterator<String> iterator2 = columnNames.iterator();
        Iterator<String> iterator3 = typeCells.iterator();
        Iterator<String> iterator4 = minCells.iterator();
        Iterator<String> iterator5 = maxCells.iterator();

        while (iterator1.hasNext()){
            features.add(createFeature(
                    iterator1.next(), iterator2.next(), iterator3.hasNext() ? iterator3.next() : null,
                    iterator4.hasNext() ? iterator4.next() : null, iterator5.hasNext() ? iterator5.next() : null)
            );
        }

        return features;
    }
    
    private static List<FeaturePojo> createFeatures(List<String> columnIDs, List<String> columnNames) {
        List<FeaturePojo> features = new ArrayList<>(columnIDs.size());

        Iterator<String> iterator1 = columnIDs.iterator();
        Iterator<String> iterator2 = columnNames.iterator();

        while (iterator1.hasNext()){
            features.add(createFeature(iterator1.next(), iterator2.next()));
        }

        return features;
    }

    private static FeaturePojo createFeature(String id, String name, String type, String min, String max)
            throws DatasetException {
        try {

            ScalePojo scale;

            // default to nominal
            ScaleType scaleType = type != null && type.length() > 0 ? getScaleTypeByAbbreviation(type.substring(0, 1))
                    : ScaleType.NOMINAL;
            // set default or specified encoding
            DataType dataType = type != null && type.length() > 1 ? getDataTypeByAbbreviation(type.substring(1, 2))
                    : scaleType.getDefaultEncoding();

            Number minNumber = null;
            Number maxNumber = null;

            if (min != null){
                minNumber = ConversionUtilities.convertToNumber(min, DataTypeConstants.getConversionType(dataType));
            }

            if (max != null){
                maxNumber = ConversionUtilities.convertToNumber(max, DataTypeConstants.getConversionType(dataType));
            }

            if (minNumber != null || maxNumber != null){
                if(scaleType != ScaleType.INTERVAL && scaleType != ScaleType.RATIO){
                    throw new IllegalArgumentException(
                            "Minimum and maximum value can only be set for Interval or Ratio scale."
                    );
                }
                scale = new ScalePojo(id, name, dataType, scaleType, minNumber, maxNumber);
            } else {
                scale = new ScalePojo(id, name, dataType, scaleType);
            }

            return new FeaturePojo(id, name, new MethodPojo(id, name, scale));
        } catch (ConversionException e) {
            throw new DatasetException(e);
        }
    }
    
    private static FeaturePojo createFeature(String id, String name) {
        ScalePojo scale = new ScalePojo(id, name);

        scale.setDataType(DataType.STRING);
        scale.setScaleType(ScaleType.NOMINAL);

        return new FeaturePojo(id, name, new MethodPojo(id, name, scale));
    }

    private static DataType getDataTypeByAbbreviation(String type) throws DatasetException {
        DataType dataType = DataType.getTypeByAbbreviation(type);

        if (dataType == null){
            throw new DatasetException("Unknown data type abbeviation : '" + type + "'");
        }

        return dataType;
    }

    private static ScaleType getScaleTypeByAbbreviation(String type) throws DatasetException {
        ScaleType scaleType = ScaleType.getTypeByAbbreviation(type);

        if (scaleType == null){
            throw new DatasetException("Unknown scale abbeviation : '" + type + "'");
        }

        return scaleType;
    }

    // TODO not tested
    public static final FeatureData createSubsetFeatureDataset(String uniqueIdentifier, String name,
            FeatureData dataset, Set<Integer> indices) throws DatasetException {
        Iterator<Integer> iterator = indices.iterator();

        Object[][] values = new Object[indices.size()][dataset.getFeatures().size()];

        int i = 0;

        while (iterator.hasNext()) {
            values[i] = dataset.getRow(iterator.next()).getValuesAsArray();
        }

        return new ArrayFeatureData(uniqueIdentifier, name, dataset.getFeaturesAsArray(), values);
    }

    // TODO not tested
    public static final FeatureData createSubsetFeatureDataset(String uniqueIdentifier, String name,
            FeatureData dataset, int[] indices) throws DatasetException {
        Object[][] values = new Object[indices.length][dataset.getFeatures().size()];

        for (int i = 0; i < indices.length; ++i) {
            values[i] = dataset.getRow(indices[i]).getValuesAsArray();
        }

        return new ArrayFeatureData(uniqueIdentifier, name, dataset.getFeaturesAsArray(), values);
    }

    private static void updateRowScales(List<FeaturePojo> features, List<Object> row) {
        Iterator<FeaturePojo> iterator = features.iterator();

        Iterator<Object> cell = row.iterator();

        while (iterator.hasNext() && cell.hasNext()) {
            updateScale((ScalePojo) iterator.next().getMethod().getScale(), cell.next());
        }
    }

    private static void updateScale(ScalePojo scale, Object value) {
        if(value != null){
            // add value to list of possible values (not for ratio variables)
            if(!ScaleType.RATIO.equals(scale.getScaleType())) {
                scale.addValue(value);
            }
            // update minimum and maximum (only for interval and ratio; number data type expected)
            if(ScaleType.INTERVAL.equals(scale.getScaleType()) || ScaleType.RATIO.equals(scale.getScaleType())){
                if(!(value instanceof Number)){
                    throw new IllegalArgumentException("Interval and Ratio variables should use a numeric data type.");
                }
                Number v = (Number) value;
                if (scale.getMinimumValue() == null || v.doubleValue() < scale.getMinimumValue().doubleValue()){
                    scale.setMinimumValue(v);
                }
                if (scale.getMaximumValue() == null || v.doubleValue() > scale.getMaximumValue().doubleValue()){
                    scale.setMaximumValue(v);
                }
            }
        }
    }

    private static List<Object> convertCells(List<String> cells, int[] types) throws ConversionException {
        return ConversionUtilities.convertToObjectList(cells, types);
    }

    private void setValuesWithHeaders(Object[][] values) throws IllegalArgumentException {
        rowCount = 0;

        if (values != null && values.length > 0) {

            rowCount = values.length;

            int columnCount = getFeatures().size();

            rows = new FeatureDataRow[rowCount];
            rowHeaders = new SimpleEntity[rowCount];
            
            for (int i = 0; i < values.length; ++i) {
                if (columnCount != values[i].length - 1){
                    throw new IllegalArgumentException("Row : " + i + " size : " + (values[i].length - 1)
                            + " does not match the number of features : " + columnCount);
                }

                rows[i] = ArrayFeatureDataRow.createRow(true, values[i]);
                rowHeaders[i] = rows[i].getHeader() ;
            }
            
            DataPojo.checkHeaders(rowHeaders) ;
            createIds(rows.length);
        }
    }

    private void setValuesWithHeaders(SimpleEntity[] headers, Object[][] values) throws IllegalArgumentException {
        rowCount = 0;
        
        if (values != null && values.length > 0) {

            rowCount = values.length;
    
            int columnCount = getFeatures().size();
    
            rows = new FeatureDataRow[rowCount];
            rowHeaders = new SimpleEntity[rowCount];
            
            for (int i = 0; i < values.length; ++i) {
                if (columnCount != values[i].length - 1){
                    throw new IllegalArgumentException("Row : " + i + " size : " + (values[i].length - 1)
                            + " does not match the number of features : " + columnCount);
                }
    
                rows[i] = ArrayFeatureDataRow.createRow(headers[i], values[i]);
                rowHeaders[i] = rows[i].getHeader() ;
            }
    
            DataPojo.checkHeaders(rowHeaders) ;
            createIds(rows.length);
        }
    }

    private void setValuesWithHeaders(List<List<Object>> values) {
        rowCount = 0;

        if (values != null && values.size() > 0) {
            rowCount = values.size();

            rows = new FeatureDataRow[rowCount];
            rowHeaders = new SimpleEntity[rowCount];
            
            Iterator<List<Object>> iterator = values.iterator();

            int i = 0;

            while (iterator.hasNext()) {
                rows[i] = ArrayFeatureDataRow.createRow(true, iterator.next());
                rowHeaders[i] = rows[i].getHeader() ;
                ++i;
            }
            
            DataPojo.checkHeaders(rowHeaders) ;
            createIds(rows.length);
        }  
    }

    private void setValuesWithHeaders(List<SimpleEntity> headers, List<List<Object>> values) {
        rowCount = 0;

        if (values != null && values.size() > 0) {
            rowCount = values.size();

            if (headers == null){
                throw new IllegalArgumentException("Headers not defined whereas row count is : " + rowCount);
            }
            
            if (headers.size() != values.size()){
                throw new IllegalArgumentException(
                        "Number of headers : " + headers.size() + " does not match row count : " + rowCount);
            }

            rows = new FeatureDataRow[rowCount];
            rowHeaders = new SimpleEntity[rowCount];

            Iterator<List<Object>> iterator1 = values.iterator();
            Iterator<SimpleEntity> iterator2 = headers.iterator();

            int i = 0;

            while (iterator1.hasNext()) {
                rows[i] = ArrayFeatureDataRow.createRow(iterator2.next(), iterator1.next());
                rowHeaders[i] = rows[i].getHeader() ;
                ++i;
            }
            
            DataPojo.checkHeaders(rowHeaders) ;
            createIds(rows.length);
        } 
    }

    private List<FeatureDataRow> toRowList(FeatureDataRow[] array) {
        List<FeatureDataRow> list = new ArrayList<>(array.length);

        for (int i = 0; i < array.length; ++i){
            list.add(array[i]);
        }

        return list;
    }

    private List<List<Object>> toObjectListList(FeatureDataRow[] array) {
        List<List<Object>> list = new ArrayList<>(array.length);

        for (int i = 0; i < array.length; ++i){
            list.add(toObjectList(array[i].getValuesAsArray()));
        }

        return list;
    }

    private List<Object> toObjectList(Object[] array) {
        List<Object> list = new ArrayList<>(array.length);

        for (int i = 0; i < array.length; ++i){
            list.add(array[i]);
        }

        return list;
    }
    
    private List<SimpleEntity> toObjectList(SimpleEntity[] array) {
        List<SimpleEntity> list = new ArrayList<>(array.length);

        for (int i = 0; i < array.length; ++i){
            list.add(array[i]);
        }

        return list;
    }

    private Object[][] toObjectArray(FeatureDataRow[] array) {
        Object[][] list = new Object[array.length][];

        for (int i = 0; i < array.length; ++i){
            list[i] = array[i].getValuesAsArray();
        }

        return list;
    }

    @Override
    public SimpleEntity getHeader(int id) {
        return rows[id].getHeader();
    }

    @Override
    public int getSize() {
        return rows.length;
    }

    @Override
    public Set<Integer> getIDs() {
        return ids;
    }

    private void createIds(int n) {

        ids = new HashSet<>();

        for (int i = 0; i < n; i++) {
            ids.add(i);
        }

        ids = Collections.unmodifiableSet(ids);
    }

}