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

package uno.informatics.data.feature;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import uno.informatics.common.ConversionUtilities;
import uno.informatics.common.io.IOUtilities;
import uno.informatics.common.io.RowReader;
import uno.informatics.data.DataOption;
import uno.informatics.data.Dataset;
import uno.informatics.data.Feature;
import uno.informatics.data.dataset.FeatureData;
import uno.informatics.data.dataset.FeatureDataRow;
import uno.informatics.data.io.FileType;
import uno.informatics.data.pojo.SimpleEntityPojo;
import uno.informatics.data.utils.DatasetUtils;

/**
 * @author Guy Davenport
 *
 */
public abstract class AbstractFeatureData extends SimpleEntityPojo implements FeatureData {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private static final int INVALID_ROW_COUNT = -1;

    public static final String ID = "ID";
    public static final String NAME = "NAME";
    public static final String DESCRIPTION = "DESCRIPTION";
    public static final String TYPE = "TYPE";
    public static final String MIN = "MIN";
    public static final String MAX = "MAX";
    public static final String DATE_FORMAT = "DATE_FORMAT" ;
    
    private List<Feature> features;
    private Dataset dataset;

    protected AbstractFeatureData(String name, List<? extends Feature> features) {
        super(null, name);

        setFeatures(features);
    }

    protected AbstractFeatureData(String uniqueIdentifier, String name, List<? extends Feature> features) {
        super(uniqueIdentifier, name);

        setFeatures(features);
    }
    
    protected AbstractFeatureData(String name, Feature[] features) {
        super(null, name);

        setFeatures(features);
    }

    protected AbstractFeatureData(String uniqueIdentifier, String name, Feature[] features) {
        super(uniqueIdentifier, name);

        setFeatures(features);
    }

    @Override
    public final List<Feature> getFeatures() {
        return features;
    }

    @Override
    public Feature[] getFeaturesAsArray() {
        return features.toArray(new Feature[features.size()]);
    }

    public abstract int getRowCount();

    public abstract FeatureDataRow[] getRowsAsArray();

    public abstract List<FeatureDataRow> getRows();

    @Override
    public Dataset getDataset() {
        return dataset;
    }

    protected final void setFeatures(List<? extends Feature> features) {
        this.features = new ArrayList<Feature>();

        this.features.addAll(features);
    }
    
    protected final void setFeatures(Feature[] features) {
        this.features = new ArrayList<Feature>();

        for (int i = 0 ; i < features.length ; ++i)
            this.features.add(features[i]);
    }
    
    public static final List<ColumnFeature> generateDatasetFeatures(Path path, FileType fileType, String columnLabel, DataOption... options)
            throws IOException {
        return generateDatasetFeatures(path, fileType, columnLabel, INVALID_ROW_COUNT, options);
    }

    public static final List<ColumnFeature> generateDatasetFeatures(Path filePath, FileType type, String columnLabel,
            int numRows, DataOption... options) throws IOException {
        RowReader reader = null;
        List<ColumnFeature> features = null;

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
        
        reader = IOUtilities.createRowReader(filePath, type);

        if (reader != null && reader.ready()) {
            features = new ArrayList<ColumnFeature>();

            List<String> colIds = null;
            List<String> colNames = null;
            
            List<Integer> dataTypes = new ArrayList<Integer>();

            int columnCount = 0;
            int row = 0;
            int rowsRead = 0;

            List<String> cells;
            String rowID = null ;
            boolean hasRowNames = false ;
            
            if (reader.nextRow()) {
               
                colIds = reader.getRowCellsAsString();
                
                if (ID.equals(colIds.get(0))) {

                    if (colIds.size() > 1 && NAME.equals(colIds.get(1))) {
                        colIds.remove(0) ;
                        colIds.remove(0) ;

                        hasRowNames = true ;
                    } else {
                        colIds.remove(0) ;
                    }
                
                    
                } else {
                    if (uniqueIdentifier != null) 
                        throw new IllegalArgumentException("Using ID DataOption: First cell must be " + ID);
                    
                    colIds.remove(0) ;
                }
                
                columnCount = colIds.size() ;

                ++row;

                if (reader.nextRow()) {  
                    
                    reader.nextColumn() ;
                    
                    rowID = reader.getCellAsString() ;
                    
                    if (hasRowNames) {
                        reader.nextColumn() ;
                    }
                    reader.nextColumn() ;
                    
                    cells = reader.getRowCellsAsString();
                    
                    ++row;
                    ++rowsRead;
                    
                    boolean hasColNames = NAME.equals(rowID) ; 
                    
                    if (hasColNames)  {
                        if (reader.nextRow()) {
    
                            reader.nextColumn() ;
                            colNames = cells ;
    
                            if (hasRowNames) {
                                reader.nextColumn() ;
                            }
                            reader.nextColumn() ;
                            
                            ++row;
                            ++rowsRead;
                        }
                        else {
                            throw new IOException("Not enough rows");
                        } 
                    } else {
                        colNames = colIds ;                        
                    }
      
                    cells = reader.getRowCellsAsString();
                    
                    dataTypes = ConversionUtilities.getDataTypes(cells);

                    features = new ArrayList<ColumnFeature>(columnCount);

                    dataTypes = ConversionUtilities.getDataTypes(cells);

                    while (reader.nextRow() && (numRows < 0 || rowsRead < numRows)) {
                        reader.nextColumn() ;
                        
                        if (hasRowNames) {
                            reader.nextColumn() ;
                        }
                        reader.nextColumn() ;
                        
                        cells = reader.getRowCellsAsString();

                        if (cells.size() != columnCount)
                            throw new IOException(String.format("Row %d is not right size, expecting %d but was %d!",
                                    row, columnCount, cells.size()));

                        dataTypes = ConversionUtilities.getDataTypes(cells, dataTypes);

                        ++row;
                        ++rowsRead;
                    }
                } else {
                    throw new IOException("No enough rows");
                }
            }

            reader.close();
            
            if (colIds.size() != colNames.size()) {
                throw new IOException(String.format("Number of column IDs : %d  does not match number of column Name %d !", colIds.size(), colNames.size()));
            }
            
            if (dataTypes.size() != colIds.size()) {
                throw new IOException(String.format("Number of headers : %d  does not match number of columns %d !", colIds.size(), dataTypes.size()));        
            } 
           

            Iterator<String> iterator1 = colIds.iterator();
            Iterator<String> iterator2 = colNames.iterator();
            Iterator<Integer> iterator3 = dataTypes.iterator();
            
            while (iterator1.hasNext()) {
                features.add(DatasetUtils.createDefaultColumnFeature(iterator1.next(), iterator2.next(), iterator3.next()));
            }
        }

        if (reader != null)
            reader.close();

        return features;

    }
}
