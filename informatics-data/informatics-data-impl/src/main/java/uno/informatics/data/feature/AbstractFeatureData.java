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

import uno.informatics.data.Dataset;
import uno.informatics.data.Feature;
import uno.informatics.data.dataset.FeatureData;
import uno.informatics.data.dataset.FeatureDataRow;
import uno.informatics.data.pojo.SimpleEntityPojo;

/**
 * @author Guy Davenport
 *
 */
public abstract class AbstractFeatureData extends SimpleEntityPojo implements FeatureData {
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
}
