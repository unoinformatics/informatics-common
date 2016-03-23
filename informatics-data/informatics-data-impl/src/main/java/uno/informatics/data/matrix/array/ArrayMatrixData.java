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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import uno.informatics.data.Feature;
import uno.informatics.data.SimpleEntity;
import uno.informatics.data.Study;
import uno.informatics.data.matrix.AbstractMatrixData;

/**
 * @author Guy Davenport
 *
 */
public abstract class ArrayMatrixData<ValueType extends Object> extends AbstractMatrixData<ValueType> {
    private ValueType[][] values;
    private SimpleEntity[] rowHeaders;
    private SimpleEntity[] columnHeaders;

    private int rowCount;
    private int columnCount;
    
    private Set<Integer> ids;

    public ArrayMatrixData(String name, Feature elementFeature, ValueType[][] values) {
        super(null, name, elementFeature);

        setValues(values);
    }

    public ArrayMatrixData(String uniqueIdentifier, String name, Feature elementFeature, ValueType[][] values) {
        super(uniqueIdentifier, name, elementFeature);

        setValues(values);
    }

    public ArrayMatrixData(String name, Feature elementFeature, List<List<ValueType>> values) {
        super(null, name, elementFeature);

        setValues(values);
    }

    public ArrayMatrixData(String uniqueIdentifier, String name, Feature elementFeature,
            List<List<ValueType>> values) {
        super(uniqueIdentifier, name, elementFeature);

        setValues(values);
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.data.dataset.MatrixDataset#getValues()
     */
    @Override
    public final List<List<ValueType>> getValues() {
        return toValueListList(values);
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.data.dataset.MatrixDataset#getValuesAsArray()
     */
    @Override
    public final ValueType[][] getValuesAsArray() {
        return values;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.data.dataset.MatrixDataset#getValue(int, int)
     */
    @Override
    public final ValueType getValue(int rowIndex, int columnIndex) {
        return values[rowIndex][columnIndex];
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.data.matrix.AbstractMatrixDataset#getRowCount()
     */
    @Override
    public final int getRowCount() {
        return rowCount;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.data.matrix.AbstractMatrixDataset#getColumnCount()
     */
    @Override
    public final int getColumnCount() {
        return columnCount;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.common.model.MatrixDataset#hasRowHeaders()
     */
    @Override
    public boolean hasRowHeaders() {
        return rowHeaders != null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.common.model.MatrixDataset#getRowHeaders()
     */
    @Override
    public List<SimpleEntity> getRowHeaders() {
        return rowHeaders != null && rowHeaders.length > 0 ? toObjectList(rowHeaders) : null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.common.model.MatrixDataset#getRowHeadersAsArray()
     */
    @Override
    public SimpleEntity[] getRowHeadersAsArray() {
        return rowHeaders;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.common.model.MatrixDataset#getRowHeader(int)
     */
    @Override
    public SimpleEntity getRowHeader(int rowIndex) {
        return rowHeaders[rowIndex];
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.common.model.MatrixDataset#hasColumnHeaders()
     */
    @Override
    public boolean hasColumnHeaders() {
        return columnHeaders != null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.common.model.MatrixDataset#getColumnHeaders()
     */
    @Override
    public List<SimpleEntity> getColumnHeaders() {
        return columnHeaders != null && columnHeaders.length > 0 ? toObjectList(columnHeaders) : null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.common.model.MatrixDataset#getColumnHeadersAsArray()
     */
    @Override
    public SimpleEntity[] getColumnHeadersAsArray() {
        return columnHeaders;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.common.model.MatrixDataset#getColumnHeader(int)
     */
    @Override
    public SimpleEntity getColumnHeader(int columnIndex) {
        return columnHeaders != null && columnHeaders.length > 0 ? columnHeaders[columnIndex] : null;
    }

    public final void setRowHeaders(SimpleEntity[] rowHeaders) {
        if (rowHeaders != null && rowHeaders.length > 0)
            setRowHeadersInternal(copyObjectArray(rowHeaders));
        else
            this.rowHeaders = null;
    }

    public final void setRowHeaders(List<SimpleEntity> rowHeaders) {
        this.rowHeaders = rowHeaders != null && rowHeaders.size() > 0 ? toObjectArray(rowHeaders) : null;
    }

    public final void setColumnHeaders(SimpleEntity[] columnHeaders) {
        if (columnHeaders != null && columnHeaders.length > 0)
            setColumnHeadersInternal(copyObjectArray(columnHeaders));
        else
            this.columnHeaders = null;
    }

    public final void setColumnHeaders(List<SimpleEntity> columnHeaders) {
        this.columnHeaders = columnHeaders != null && columnHeaders.size() > 0 ? toObjectArray(columnHeaders) : null;
    }

    /**
     * @param values2
     */
    private final void setValues(List<List<ValueType>> values) {
        if (values != null) {
            this.values = createArrayArray(values.size());

            Iterator<List<ValueType>> iterator = values.iterator();

            int i = 0;

            if (iterator.hasNext()) {
                this.values[0] = toValueArray(iterator.next());

                columnCount = this.values[0].length;

                ++i;

                while (iterator.hasNext()) {
                    this.values[i] = toValueArray(iterator.next());

                    if (columnCount != this.values[i].length)
                        throw new IllegalArgumentException("Row " + i + " size : " + this.values[i].length
                                + " does not match column count : " + columnCount);

                    ++i;
                }
            }

            rowCount = this.values.length;
        } else {
            this.values = createArrayArray(0);

            columnCount = 0;
            rowCount = 0;
        }
    }

    /**
     * @param values2
     */
    private final void setValues(ValueType[][] values) {
        if (values != null) {
            this.values = createArrayArray(values.length);

            if (values.length > 0) {
                this.values[0] = copyValueArray(values[0]);

                columnCount = this.values.length;

                for (int i = 1; i < values[i].length; ++i) {
                    this.values[i] = copyValueArray(values[i]);

                    if (columnCount != this.values[i].length)
                        throw new IllegalArgumentException("Row " + i + " size : " + this.values[i].length
                                + " does not match column count : " + columnCount);

                    ++i;
                }
            }

            rowCount = this.values.length;
        } else {
            this.values = createArrayArray(0);

            columnCount = 0;
            rowCount = 0;
        }
    }

    private final List<List<ValueType>> toValueListList(ValueType[][] array) {
        if (array != null) {
            List<List<ValueType>> list = new ArrayList<List<ValueType>>(array.length);

            for (int i = 0; i < array.length; ++i)
                list.add(toValueList(array[i]));

            return list;
        } else {
            return new ArrayList<List<ValueType>>();
        }
    }

    private final List<ValueType> toValueList(ValueType[] array) {
        List<ValueType> list = new ArrayList<ValueType>(array.length);

        for (int i = 0; i < array.length; ++i)
            list.add(array[i]);

        return list;
    }

    private final ValueType[] toValueArray(List<ValueType> list) {
        ValueType[] array = createArray(list.size());

        Iterator<ValueType> iterator = list.iterator();

        int i = 0;

        while (iterator.hasNext()) {
            array[i] = iterator.next();
            ++i;
        }

        return array;
    }

    private ValueType[] copyValueArray(ValueType[] oldArray) {
        ValueType[] array = createArray(oldArray.length);

        for (int i = 0; i < oldArray.length; ++i)
            array[i] = oldArray[i];

        return array;
    }

    private final SimpleEntity[] toObjectArray(List<SimpleEntity> list) {
        SimpleEntity[] array = new SimpleEntity[list.size()];

        Iterator<SimpleEntity> iterator = list.iterator();

        int i = 0;

        while (iterator.hasNext()) {
            array[i] = iterator.next();
            ++i;
        }

        return array;
    }

    private final List<SimpleEntity> toObjectList(SimpleEntity[] array) {
        List<SimpleEntity> list = new ArrayList<SimpleEntity>(array.length);

        for (int i = 0; i < array.length; ++i)
            list.add(array[i]);

        return list;
    }

    /**
     * @param rowHeaders2
     * @return
     */
    private SimpleEntity[] copyObjectArray(SimpleEntity[] oldArray) {
        SimpleEntity[] array = new SimpleEntity[oldArray.length];

        for (int i = 0; i < oldArray.length; ++i)
            array[i] = oldArray[i];

        return array;
    }

    private final void setRowHeadersInternal(SimpleEntity[] rowHeaders) {
        this.rowHeaders = rowHeaders;
        
        int n = rowHeaders.length;
        
        ids = new HashSet<Integer>();

        // check unique identifiers
        Set<String> identifiers = new HashSet<>();
        for (int i = 0; i < n; i++) {
            ids.add(i);
            SimpleEntity header = rowHeaders[i];
            if (header == null || header.getUniqueIdentifier() == null) {
                throw new IllegalArgumentException(String.format("No identifier defined for item %d.", i));
            }
            if (!identifiers.add(header.getUniqueIdentifier())) {
                throw new IllegalArgumentException(
                        String.format("Identifiers are not unique. Duplicate identifier %s for item %d.",
                                header.getUniqueIdentifier(), i));
            }
        }
    }

    private final void setColumnHeadersInternal(SimpleEntity[] columnHeaders) {
        this.columnHeaders = columnHeaders;
    }

    protected abstract ValueType[][] createArrayArray(int size);

    protected abstract ValueType[] createArray(int size);
    
    @Override
    public SimpleEntity getHeader(int id) {
        return rowHeaders[id];
    }

    @Override
    public int getSize() {
        return rowHeaders.length;
    }

    @Override
    public Set<Integer> getIDs() {
        // TODO Auto-generated method stub
        return null;
    }
}
