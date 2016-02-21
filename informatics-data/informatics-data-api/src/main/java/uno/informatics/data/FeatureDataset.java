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
package uno.informatics.data;

import java.util.List;

/**
 * A dataset that consists of columns recorded for a specific feature, with
 * each row providing one value for each feature. The efficiency of each of the methods
 * depends highly on the implementation, which may be Array based,
 * List based, or a wrapper around another type of storage.
 * The method {@link #getRowCount()} should be efficient and give a good
 * indication of the size of the dataset. If the row count is high use 
 * {@link #getRow(int)} to subset the dataset.
 * 
 * @author Guy Davenport
 *
 */
public interface FeatureDataset extends Dataset
{
	public List<Feature> getFeatures() ;
	
  public Feature[] getFeaturesAsArray();
  
  public List<List<Object>> getValues();
  
  public Object[][] getValuesAsArray();
	
	public int getRowCount() ;
	
	public FeatureDatasetRow getRow(int rowIndex) ;
	
	public FeatureDatasetRow[] getRowsAsArray() ;
	
	public List<FeatureDatasetRow> getRows() ;
	
	public Feature getRowHeaderFeature();
}
