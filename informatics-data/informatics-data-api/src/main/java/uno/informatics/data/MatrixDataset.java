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
 * API for datasets that consist of a 2-dimensional matrix of elements of a single type
 * The efficiency of each of the methods depends highly on the implementation, 
 * which may be Array based, List based, or a wrapper around another type of storage.
 * The methods {@link #getRowCount()} and {@link #getColumnCount()} should be efficient and give a good
 * indication of the size of the dataset. If the row count and or column count is high use 
 * {@link #getValue(int, int)} to subset the dataset.
 * 
 * @author Guy Davenport
 *
 */
public interface MatrixDataset<ValueType extends Object> extends Dataset
{
  public List<List<ValueType>> getValues();
  
  public ValueType[][] getValuesAsArray();
  
  public ValueType getValue(int rowIndex, int columnIndex);
	
  public int getRowCount() ;
	
  public int getColumnCount() ;
	
	public boolean hasRowHeaders();
	
	public Feature getRowHeaderFeature();
	
  public List<Object> getRowHeaders();
  
  public Object[] getRowHeadersAsArray();
  
  public Object getRowHeader(int rowIndex);
	
	public Feature getColumnHeaderFeature();
	
  public List<Object> getColumnHeaders();
  
  public Object[] getColumnHeadersAsArray();
  
  public Object getColumnHeader(int columnIndex);

  public boolean hasColumnHeaders();
}
