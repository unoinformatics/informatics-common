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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import uno.informatics.data.SimpleEntity;
import uno.informatics.data.dataset.FeatureDataRow;
import uno.informatics.data.pojo.PropertyHandler;
import uno.informatics.data.pojo.SimpleEntityPojo;

/**
 * @author Guy Davenport
 *
 */
public class ArrayFeatureDataRow extends PropertyHandler implements FeatureDataRow {
    public static final String HEADER_PROPERTY = FeatureDataRow.class.getName() + ".header";

    private SimpleEntity header;
    private Object[] values;

    /**
     * Creates a new ArrayFeatureDatasetRow using the given values. The values
     * array is not copied, but used as is.
     * 
     * @param values
     *            values for the new row
     */
    public ArrayFeatureDataRow(Object[] values) {
        if (values != null)
            this.values = values;
        else
            this.values = new Object[0];
    }

    /**
     * Creates a new ArrayFeatureDatasetRow using the given values. The values
     * array is not copied, but used as is. The name of the row is extracted
     * from the header by using {@link SimpleEntity#getName()} if the object
     * implements {@link SimpleEntity} or using {@link Object#toString()}
     * otherwise
     * 
     * @param header
     *            the header of the row
     * @param values
     *            values for the new row
     */
    public ArrayFeatureDataRow(SimpleEntity header, Object[] values) {
        super();

        setHeader(header);

        if (values != null)
            this.values = values;
        else
            this.values = new Object[0];
    }

    /**
     * Creates a new ArrayFeatureDatasetRow using the given values. If the
     * header argument is <code>true</code> the name of the row is extracted
     * from the header by using {@link SimpleEntity#getName()} if the object
     * implements {@link SimpleEntity} or using {@link Object#toString()}
     * otherwise. If the header argument is <code>false</code> the header and
     * name are set to null.
     * 
     * @param hasHeader
     *            <code>true</code> if the first element in the values is the
     *            header
     * @param values
     *            values for the new row
     */
    private ArrayFeatureDataRow(boolean hasHeader, Object[] values) {
        super();

        if (values != null && values.length > 0) {
            if (hasHeader) {
                this.values = new Object[values.length - 1];

                setHeaderAsObject(values[0]);

                for (int i = 0; i < values.length - 1; ++i)
                    this.values[i] = values[i + 1];
            } else {
                this.values = new Object[values.length];

                for (int i = 0; i < values.length; ++i)
                    this.values[i] = values[i];
            }
        } else {
            this.values = new Object[0];
        }
    }

    /**
     * Creates a new ArrayFeatureDatasetRow using the given values.
     * 
     * @param values
     *            values for the new row
     */
    public ArrayFeatureDataRow(List<Object> values) {
        this(null, values);
    }

    /**
     * Creates a new ArrayFeatureDatasetRow using the given values. The values
     * array is not copied, but used as is. The name of the row is extracted
     * from the header by using {@link SimpleEntity#getName()} if the object
     * implements {@link SimpleEntity} or using {@link Object#toString()}
     * otherwise.
     * 
     * @param header
     *            the header of the row
     * @param values
     *            values for the new row
     */
    public ArrayFeatureDataRow(SimpleEntity header, List<Object> values) {
        setHeaderAsObject(header);

        if (values != null)
            this.values = values.toArray();
        else
            this.values = new Object[0];
    }

    /**
     * Creates a new ArrayFeatureDatasetRow using the given values. If the
     * header argument is <code>true</code> the name of the row is extracted
     * from the header by using {@link SimpleEntity#getName()} if the object
     * implements {@link SimpleEntity} or using {@link Object#toString()}
     * otherwise. If the header argument is <code>false</code> the header and
     * name are set to null.
     * 
     * @param hasHeader
     *            <code>true</code> if the first element in the values is the
     *            header
     * @param values
     *            values for the new row
     */
    private ArrayFeatureDataRow(boolean hasHeader, List<Object> values) {
        if (values != null && !values.isEmpty()) {
            if (hasHeader) {
                Iterator<Object> iterator = values.iterator();

                this.values = new Object[values.size() - 1];

                Object header = iterator.next();

                setHeaderAsObject(header);

                int i = 0;

                while (iterator.hasNext()) {
                    this.values[i] = iterator.next();
                    ++i;
                }

            } else {
                this.values = values.toArray();
            }
        } else {
            this.values = new Object[0];
        }
    }

    @Override
    public final SimpleEntity getHeader() {
        return header;
    }

    public final void setHeader(SimpleEntity header) {
        SimpleEntity oldValue = this.header;

        this.header = header;

        getPropertyChangeSupport().firePropertyChange(HEADER_PROPERTY, oldValue, this.header);
    }

    private final void setHeaderAsObject(Object header) {
        if (header != null)
            if (header instanceof String)
                setHeader(new SimpleEntityPojo((String) values[0]));
            else if (header instanceof SimpleEntity)
                setHeader((SimpleEntity) header);
            else
                setHeader(new SimpleEntityPojo(header.toString()));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * uno.informatics.data.tests.feature.array.array.DatasetRow#getValues()
     */
    @Override
    public final List<Object> getValues() {
        return toList(values);
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.data.tests.feature.array.array.DatasetRow#
     * getValuesAsArray()
     */
    @Override
    public final Object[] getValuesAsArray() {
        return values;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.data.tests.feature.array.array.DatasetRow#getValue()
     */
    @Override
    public final Object getValue(int columnIndex) {
        return values[columnIndex];
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * uno.informatics.data.tests.feature.array.array.DatasetRow#getColumnCount(
     * )
     */
    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return values.length;
    }

    /**
     * @param dataTypes
     * @return
     */
    private List<Object> toList(Object[] array) {
        List<Object> list = new ArrayList<Object>(array.length);

        for (int i = 0; i < array.length; ++i)
            list.add(array[i]);

        return list;
    }

    /**
     * Creates new ArrayFeatureDatasetRow
     *
     * @param header
     *            <code>true</code> if the first value the header,
     *            <code>false</code> otherwise
     * @param values
     *            for the row
     * @return created row
     */
    public static FeatureDataRow createRow(boolean header, List<Object> values) {
        if (header)
            return new ArrayFeatureDataRow(header, values);
        else
            return new ArrayFeatureDataRow(values);
    }

    /**
     * Creates new ArrayFeatureDatasetRow
     *
     * @param header
     *            <code>true</code> if the first value the header,
     *            <code>false</code> otherwise
     * @param values
     *            for the row
     * @return created row
     */
    public static FeatureDataRow createRow(boolean header, Object[] values) {
        if (header)
            return new ArrayFeatureDataRow(header, values);
        else
            return new ArrayFeatureDataRow(null, values);
    }

    /**
     * Creates new ArrayFeatureDatasetRow
     * 
     * @param header
     *            the header value
     * @param values
     *            for the row
     * @return created row
     */
    public static FeatureDataRow createRow(SimpleEntity header, List<Object> values) {
        return new ArrayFeatureDataRow(header, values);
    }

    /**
     * Creates new ArrayFeatureDatasetRow
     * 
     * @param header
     *            the header value
     * @param values
     *            for the row
     * @return created row
     */
    public static FeatureDataRow createRow(SimpleEntity header, Object[] values) {
        return new ArrayFeatureDataRow(header, values);
    }
}
