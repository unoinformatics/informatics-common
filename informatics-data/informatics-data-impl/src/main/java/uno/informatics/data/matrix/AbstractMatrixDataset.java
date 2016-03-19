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

package uno.informatics.data.matrix;

import uno.informatics.data.Feature;
import uno.informatics.data.dataset.MatrixDataset;
import uno.informatics.data.pojo.EntityPojo;

/**
 * @author Guy Davenport
 *
 */
public abstract class AbstractMatrixDataset<ValueType extends Object> extends EntityPojo
        implements MatrixDataset<ValueType> {
    private Feature valueFeature;

    protected AbstractMatrixDataset(String name, Feature elementFeature) {
        super(null, name);

        setValueFeature(elementFeature);
    }

    protected AbstractMatrixDataset(String uniqueIdentifier, String name, Feature elementFeature) {
        super(uniqueIdentifier, name);

        setValueFeature(elementFeature);
    }

    protected AbstractMatrixDataset(String uniqueIdentifier, String name, String desription, Feature elementFeature) {
        super(uniqueIdentifier, name, desription);

        setValueFeature(elementFeature);
    }

    public abstract int getRowCount();

    public abstract int getColumnCount();

    @Override
    public final Feature getValueFeature() {
        return valueFeature;
    }

    public final void setValueFeature(Feature valueFeature) {
        this.valueFeature = valueFeature;
    }

}
