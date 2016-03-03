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
package uno.informatics.data.tests.feature.array;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import uno.informatics.data.FeatureDataset;
import uno.informatics.data.feature.array.ArrayFeatureDataset;
import uno.informatics.data.pojo.EntityPojo;
import uno.informatics.data.tests.feature.DatasetTest;

/**
 * @author Guy Davenport
 *
 */
public class ArrayFeatureDatasetTest extends DatasetTest
{
	/**
	 * Test method for {@link uno.informatics.data.tests.feature.array.ArrayFeatureDatasetRow.ArrayDatasetRow#setName(java.lang.String)}.
	 */
	@Test
	public void testSetName()
	{
		ArrayFeatureDataset dataset = new ArrayFeatureDataset(UID, NAME, DESCRIPTION, OBJECT_FEATURES, OBJECT_TABLE_AS_ARRAY) ;
		
		assertEquals(NAME, dataset.getName()) ;
		
		dataset.setName("asdf") ;
		
		assertEquals("asdf", dataset.getName()) ;
	}

	/**
	 * Test method for {@link uno.informatics.data.feature.array.ArrayFeatureDataset#ArrayFeatureDataset(java.lang.String, java.util.List, java.lang.Object[][])}.
	 */
	@Test
	public void testArrayFeatureDatasetStringListOfDatasetFeatureObjectArrayArray()
	{
		ArrayFeatureDataset dataset = new ArrayFeatureDataset(NAME, OBJECT_FEATURES, OBJECT_TABLE_AS_ARRAY) ;
		
    checkCompleteDataset(null, NAME, null, OBJECT_FEATURES, dataset, BLANK_HEADERS, null, false) ;
	}
	
	/**
	 * Test method for {@link uno.informatics.data.feature.array.ArrayFeatureDataset#ArrayFeatureDataset(java.lang.String, java.lang.String, java.util.List, java.lang.Object[][])}.
	 */
	@Test
	public void testArrayFeatureDatasetStringStringListOfDatasetFeatureObjectArrayArray()
	{
		ArrayFeatureDataset dataset = new ArrayFeatureDataset(UID, NAME, OBJECT_FEATURES, OBJECT_TABLE_AS_ARRAY) ;
		
    checkCompleteDataset(UID, NAME, null, OBJECT_FEATURES, dataset, BLANK_HEADERS, null, false) ;
	}

	/**
	 * Test method for {@link uno.informatics.data.feature.array.ArrayFeatureDataset#ArrayFeatureDataset(java.lang.String, java.lang.String, java.lang.String, java.util.List, java.lang.Object[][])}.
	 */
	@Test
	public void testArrayFeatureDatasetStringStringStringListOfDatasetFeatureObjectArrayArray()
	{
		ArrayFeatureDataset dataset = new ArrayFeatureDataset(UID, NAME, DESCRIPTION, OBJECT_FEATURES, OBJECT_TABLE_AS_ARRAY) ;
		
    checkCompleteDataset(UID, NAME, DESCRIPTION, OBJECT_FEATURES, dataset, BLANK_HEADERS, null, false) ;
	}

	/**
	 * Test method for {@link uno.informatics.data.feature.array.ArrayFeatureDataset#ArrayFeatureDataset(uno.informatics.common.model.Identifier, java.util.List, java.lang.Object[][])}.
	 */
	@Test
	public void testArrayFeatureDatasetIdentifierListOfDatasetFeatureObjectArrayArray()
	{
		ArrayFeatureDataset dataset = new ArrayFeatureDataset(new EntityPojo(UID, NAME, DESCRIPTION), OBJECT_FEATURES, OBJECT_TABLE_AS_ARRAY) ;
		
    checkCompleteDataset(UID, NAME, DESCRIPTION, OBJECT_FEATURES, dataset, BLANK_HEADERS, null, false) ;
	}

	/**
	 * Test method for {@link uno.informatics.data.feature.array.ArrayFeatureDataset#ArrayFeatureDataset(java.lang.String, java.util.List, java.util.List)}.
	 */
	@Test
	public void testArrayFeatureDatasetStringListOfDatasetFeatureListOfListOfObject()
	{
		ArrayFeatureDataset dataset = new ArrayFeatureDataset(NAME, OBJECT_FEATURES, OBJECT_TABLE_AS_LIST) ;
		
    checkCompleteDataset(null, NAME, null, OBJECT_FEATURES, dataset, BLANK_HEADERS, null, false) ;
	}

	/**
	 * Test method for {@link uno.informatics.data.feature.array.ArrayFeatureDataset#ArrayFeatureDataset(java.lang.String, java.lang.String, java.util.List, java.util.List)}.
	 */
	@Test
	public void testArrayFeatureDatasetStringStringListOfDatasetFeatureListOfListOfObject()
	{
		ArrayFeatureDataset dataset = new ArrayFeatureDataset(UID, NAME, OBJECT_FEATURES, OBJECT_TABLE_AS_LIST) ;
		
    checkCompleteDataset(UID, NAME, null, OBJECT_FEATURES, dataset, BLANK_HEADERS, null, false) ;
	}

	/**
	 * Test method for {@link uno.informatics.data.feature.array.ArrayFeatureDataset#ArrayFeatureDataset(java.lang.String, java.lang.String, java.lang.String, java.util.List, java.util.List)}.
	 */
	@Test
	public void testArrayFeatureDatasetStringStringStringListOfDatasetFeatureListOfListOfObject()
	{
		ArrayFeatureDataset dataset = new ArrayFeatureDataset(new EntityPojo(UID, NAME, DESCRIPTION), OBJECT_FEATURES, OBJECT_TABLE_AS_LIST) ;
		
    checkCompleteDataset(UID, NAME, DESCRIPTION, OBJECT_FEATURES, dataset, BLANK_HEADERS, null, false) ;
	}

	/**
	 * Test method for {@link uno.informatics.data.feature.array.ArrayFeatureDataset#ArrayFeatureDataset(uno.informatics.common.model.Identifier, java.util.List, java.util.List)}.
	 */
	@Test
	public void testArrayFeatureDatasetIdentifierListOfDatasetFeatureListOfListOfObject()
	{
		ArrayFeatureDataset dataset = new ArrayFeatureDataset(new EntityPojo(UID, NAME, DESCRIPTION), OBJECT_FEATURES, OBJECT_TABLE_AS_LIST) ;
		
    checkCompleteDataset(UID, NAME, DESCRIPTION, OBJECT_FEATURES, dataset, BLANK_HEADERS, null, false) ;
	}

	/**
	 * Test method for {@link uno.informatics.data.feature.array.ArrayFeatureDataset#createFeatureDataset(java.lang.String, java.lang.String, java.lang.String, java.util.List, java.util.List, boolean)}.
	 */
	@Test
	public void testCreateFeatureDatasetStringStringStringListOfDatasetFeatureListOfListOfObjectBoolean()
	{
	  FeatureDataset dataset = 
	      new ArrayFeatureDataset(UID, NAME, DESCRIPTION, OBJECT_FEATURES, OBJECT_TABLE_AS_LIST) ;
	  
	  checkCompleteDataset(UID, NAME, DESCRIPTION, OBJECT_FEATURES, dataset, BLANK_HEADERS, null, false) ;
	  
	  dataset = 
	      new ArrayFeatureDataset(UID, NAME, DESCRIPTION, OBJECT_FEATURES, OBJECT_TABLE_AS_LIST_WITH_HEADER, NAMES_HEADER_FEATURE) ;
	  
	  checkCompleteDataset(UID, NAME, DESCRIPTION, OBJECT_FEATURES, dataset, ROW_HEADERS, NAMES_HEADER_FEATURE, false) ;

	}

	/**
	 * Test method for {@link uno.informatics.data.feature.array.ArrayFeatureDataset#createFeatureDataset(java.lang.String, java.lang.String, java.lang.String, java.util.List, java.lang.Object[][], boolean)}.
	 */
	@Test
	public void testCreateFeatureDatasetStringStringStringListOfDatasetFeatureObjectArrayArrayBoolean()
	{
	  FeatureDataset dataset = 
	      new ArrayFeatureDataset(UID, NAME, DESCRIPTION, OBJECT_FEATURES, OBJECT_TABLE_AS_ARRAY) ;
	  
	  checkCompleteDataset(UID, NAME, DESCRIPTION, OBJECT_FEATURES, dataset, BLANK_HEADERS, null, false) ;
	  
	  dataset = 
	      new ArrayFeatureDataset(UID, NAME, DESCRIPTION, OBJECT_FEATURES, OBJECT_TABLE_AS_ARRAY_WITH_HEADER, NAMES_HEADER_FEATURE) ;
	  
	  checkCompleteDataset(UID, NAME, DESCRIPTION, OBJECT_FEATURES, dataset, ROW_HEADERS, NAMES_HEADER_FEATURE, false) ;

	}

	/* (non-Javadoc)
	 * @see uno.informatics.data.tests.feature.array.array.DatasetTest#createDataset()
	 */
  @Override
  protected FeatureDataset createDataset()
  {
	  return new ArrayFeatureDataset(NAME, OBJECT_FEATURES, OBJECT_TABLE_AS_ARRAY);
  }
}
