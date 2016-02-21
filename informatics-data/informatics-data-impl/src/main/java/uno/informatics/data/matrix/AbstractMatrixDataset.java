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
import uno.informatics.data.MatrixDataset;
import uno.informatics.data.pojo.EntityPojo;

/**
 * @author Guy Davenport
 *
 */
public abstract class AbstractMatrixDataset<ValueType extends Object> 
	extends EntityPojo implements MatrixDataset<ValueType>
{
	private Feature elementFeature ;
	private Feature rowHeaderFeature ;
	private Feature columnHeaderFeature ;
	
	protected AbstractMatrixDataset(String name, Feature elementFeature)
  {
	  super(null, name);
	  
	  setElementFeature(elementFeature) ;
  }

	protected AbstractMatrixDataset(String uniqueIdentifier, String name, Feature elementFeature)
  {
	  super(uniqueIdentifier, name);
	  
	  setElementFeature(elementFeature) ;
  }
	
	protected AbstractMatrixDataset(String uniqueIdentifier, String name, String desription, Feature elementFeature)
  {
	  super(uniqueIdentifier, name, desription);
	  
	  setElementFeature(elementFeature) ;
  }

	public abstract int getRowCount() ;
	
	public abstract int getColumnCount() ;
	
	public final Feature getElementFeature()
	{
		return elementFeature;
	}

	public final void setElementFeature(Feature elementFeature)
	{
		this.elementFeature = elementFeature;
	}

	public final Feature getRowHeaderFeature()
	{
		return rowHeaderFeature;
	}

	public final void setRowHeaderFeature(Feature rowHeaderFeature)
	{
		this.rowHeaderFeature = rowHeaderFeature;
	}

	public final Feature getColumnHeaderFeature()
	{
		return columnHeaderFeature;
	}

	public final void setColumnHeaderFeature(Feature columnHeaderFeature)
	{
		this.columnHeaderFeature = columnHeaderFeature;
	}
}
