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

import java.util.List;

import uno.informatics.data.DataType;
import uno.informatics.data.Feature;
import uno.informatics.data.Method;
import uno.informatics.data.ScaleType;
import uno.informatics.data.pojo.EntityPojo;
import uno.informatics.data.pojo.MethodPojo;
import uno.informatics.data.pojo.ScalePojo;
import uno.informatics.data.utils.DatasetUtils;

/**
 * @author Guy Davenport
 * 
 */
public class ColumnFeaturePojo extends EntityPojo implements ColumnFeature {
    private DataType dataType = DataType.STRING;
    private ScaleType scaleType = ScaleType.NOMINAL;
    private ScalePojo scale;
    private MethodPojo method;
    private int possibleDataTypes;

    public ColumnFeaturePojo(String name, DataType dataType, ScaleType scaleType, int possibleDataTypes) {
        super(name);

        scale = new ScalePojo(name);
        method = new MethodPojo(name, scale);

        setDataType(dataType);
        setScaleType(scaleType);

        setPossibleDataTypes(possibleDataTypes);
    }

    public ColumnFeaturePojo(String uniqueIdentifier, String name, DataType dataType, ScaleType scaleType,
            int possibleDataTypes) {
        super(uniqueIdentifier, name);

        scale = new ScalePojo(name);
        method = new MethodPojo(name, scale);

        setDataType(dataType);
        setScaleType(scaleType);

        setPossibleDataTypes(possibleDataTypes);
    }

    public ColumnFeaturePojo(String uniqueIdentifier, String name, String description, DataType dataType,
            ScaleType scaleType, int possibleDataTypes) {
        super(uniqueIdentifier, name, description);

        scale = new ScalePojo(name);
        method = new MethodPojo(name, scale);

        setDataType(dataType);
        setScaleType(scaleType);

        setPossibleDataTypes(possibleDataTypes);
    }

    public ColumnFeaturePojo(Feature feature) {
        super(feature);

        method = new MethodPojo(feature.getMethod());
        scale = (ScalePojo) method.getScale();

        setDataType(scale.getDataType());
        setScaleType(scale.getScaleType());

        setPossibleDataTypes(possibleDataTypes);
    }

    public ColumnFeaturePojo(String name, int possibleDataTypes) {
        super(name);

        scale = new ScalePojo(name);
        method = new MethodPojo(name, scale);

        setPossibleDataTypes(possibleDataTypes);
    }

    public ColumnFeaturePojo(String uniqueIdentifier, String name, int possibleDataTypes) {
        super(uniqueIdentifier, name);

        scale = new ScalePojo(name);
        method = new MethodPojo(name, scale);

        setPossibleDataTypes(possibleDataTypes);
    }

    public ColumnFeaturePojo(String uniqueIdentifier, String name, String description, int possibleDataTypes) {
        super(uniqueIdentifier, name, description);

        scale = new ScalePojo(name);
        method = new MethodPojo(name, scale);

        setPossibleDataTypes(possibleDataTypes);
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.ui.data.ColumnFeature#getPossibleDataTypes()
     */
    @Override
    public int getPossibleDataTypes() {
        return possibleDataTypes;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.ui.data.ColumnFeature#setPossibleDataTypes(int)
     */
    @Override
    public void setPossibleDataTypes(int possibleDataTypes) {
        this.possibleDataTypes = possibleDataTypes;

        List<DataType> dataTypes = DatasetUtils.getDataTypes(possibleDataTypes);

        // make sure current data type is in list of possible data types
        if (!dataTypes.isEmpty()) // if necessary choose the first possible in
                                  // list
        {
            if (getDataType() == DataType.UNKNOWN || !dataTypes.contains(getDataType()))
                setDataType(dataTypes.get(0));
        }

        if (getDataType() != DataType.UNKNOWN) {
            List<ScaleType> scaleTypes = DatasetUtils.getScaleTypes(getDataType());

            // make sure scale type is ok for data type
            if (getScaleType() == ScaleType.NONE || !scaleTypes.contains(getScaleType()))
                setScaleType(scaleTypes.get(0));
        } else {
            setScaleType(ScaleType.NONE);
        }
    }

    // @Override
    public Method getMethod() {
        return method;
    }

    public final DataType getDataType() {
        return dataType;
    }

    public final void setDataType(DataType dataType) {
        this.dataType = dataType;

        getScale().setDataType(dataType);
    }

    public final ScaleType getScaleType() {
        return scaleType;
    }

    public final void setScaleType(ScaleType scaleType) {
        this.scaleType = scaleType;

        getScale().setScaleType(scaleType);
    }

    public ScalePojo getScale() {
        return scale;
    }
}
