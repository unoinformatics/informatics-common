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

import java.util.ArrayList;
import java.util.List;

import uno.informatics.data.Entity;
import uno.informatics.data.Feature;
import uno.informatics.data.dataset.FeatureDataset;
import uno.informatics.data.dataset.FeatureDatasetRow;
import uno.informatics.data.pojo.EntityPojo;

/**
 * @author Guy Davenport
 *
 */
public abstract class AbstractFeatureDataset extends EntityPojo implements FeatureDataset {
    private List<Feature> features;

    protected AbstractFeatureDataset(String name, List<? extends Feature> features) {
        super(null, name);

        setFeatures(features);
    }

    protected AbstractFeatureDataset(String uniqueIdentifier, String name, List<? extends Feature> features) {
        super(uniqueIdentifier, name);

        setFeatures(features);
    }

    protected AbstractFeatureDataset(String uniqueIdentifier, String name, String desription,
            List<? extends Feature> features) {
        super(uniqueIdentifier, name, desription);

        setFeatures(features);
    }

    protected AbstractFeatureDataset(Entity entity, List<? extends Feature> features) {
        super(entity);

        setFeatures(features);
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.data.tests.feature.array.array.Dataset#getFeatures()
     */
    @Override
    public final List<Feature> getFeatures() {
        return features;
    }

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.data.tests.feature.array.array.Dataset#getFeatures()
     */
    @Override
    public Feature[] getFeaturesAsArray() {
        return features.toArray(new Feature[features.size()]);
    }

    public abstract int getRowCount();

    /*
     * (non-Javadoc)
     * 
     * @see
     * uno.informatics.data.tests.feature.array.array.Dataset#getRowsAsArray()
     */
    public abstract FeatureDatasetRow[] getRowsAsArray();

    /*
     * (non-Javadoc)
     * 
     * @see uno.informatics.data.tests.feature.array.array.Dataset#getRows()
     */
    public abstract List<FeatureDatasetRow> getRows();

    protected final void setFeatures(List<? extends Feature> features) {
        this.features = new ArrayList<Feature>();

        this.features.addAll(features);
    }
}
