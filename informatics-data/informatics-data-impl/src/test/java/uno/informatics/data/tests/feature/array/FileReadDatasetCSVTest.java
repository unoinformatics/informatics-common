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

import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;

import uno.informatics.common.io.FileType;
import uno.informatics.data.FeatureDataset;
import uno.informatics.data.dataset.DatasetException;
import uno.informatics.data.feature.array.ArrayFeatureDataset;
import uno.informatics.data.tests.TestData;

/**
 * @author Guy Davenport
 *        
 */
public class FileReadDatasetCSVTest extends TestData
{
  private static final String OBJECT_TABLE                   = "/object_table_with_col_headers.csv";
  private static final String OBJECT_TABLE_WITH_ROW_NAMES    = "/object_table_with_col_name_row_headers.csv";
  private static final String OBJECT_TABLE_WITH_ROW_NAMES_IDS = "/object_table_with_col_name_id_row_headers.csv";
  private static final String OBJECT_TABLE_WITH_TYPE_ROW_NAMES    = "/object_table_with_col_type_name_row_headers.csv";
  private static final String OBJECT_TABLE_WITH_TYPE_ROW_NAMES_IDS = "/object_table_with_col_type_name_id_row_headers.csv";        
  private static final String OBJECT_TABLE_WITH_TYPE_MIN_ROW_NAMES    = "/object_table_with_col_type_min_name_row_headers.csv";
  private static final String OBJECT_TABLE_WITH_TYPE_MIN_ROW_NAMES_IDS = "/object_table_with_col_type_min_name_id_row_headers.csv"; 
  private static final String OBJECT_TABLE_WITH_TYPE_MIN_MAX_ROW_NAMES    = "/object_table_with_col_type_min_max_name_row_headers.csv";
  private static final String OBJECT_TABLE_WITH_TYPE_MIN_MAX_ROW_NAMES_IDS = "/object_table_with_col_type_min_max_name_id_row_headers.csv"; 
  /**
   * Test method for
   * {@link uno.informatics.data.feature.array.ArrayFeatureDataset#readFeatureDatasetFromTextFile(java.io.File, uno.informatics.common.io.FileType)}
   * .
   */
  @Test
  public void testReadFeatureDatasetFromTextFile()
  {
    try
    {
      File file = new File(ArrayFeatureDataset.class.getResource(OBJECT_TABLE).getFile());
      
      FeatureDataset dataset = ArrayFeatureDataset.readFeatureDatasetFromTextFile(file, getFileType());
      
      checkCompleteDataset(file.getName(), file.getName(), "Dataset loading from " + file.getAbsolutePath(),
          STRING_FEATURES, dataset, BLANK_HEADERS, null, true);
          
    }
    catch (DatasetException e)
    {
      e.printStackTrace();
      
      fail(e.getLocalizedMessage());
    }
  }
  
  protected FileType getFileType()
  {
    return FileType.CSV;
  }
  
  /**
   * Test method for
   * {@link uno.informatics.data.feature.array.ArrayFeatureDataset#readFeatureDatasetFromTextFile(java.io.File, uno.informatics.common.io.FileType)}
   * .
   */
  @Test
  public void testReadFeatureDatasetFromTextFileWithNameRowHeaders()
  {
    try
    {
      File file = new File(ArrayFeatureDataset.class.getResource(OBJECT_TABLE_WITH_ROW_NAMES).getFile());
      
      FeatureDataset dataset = ArrayFeatureDataset.readFeatureDatasetFromTextFile(file, getFileType());
      
      checkCompleteDataset(file.getName(), file.getName(), "Dataset loading from " + file.getAbsolutePath(),
          STRING_FEATURES, dataset, ROW_HEADERS, NAMES_HEADER_FEATURE, true);
          
    }
    catch (DatasetException e)
    {
      e.printStackTrace();
      
      fail(e.getLocalizedMessage());
    }
  }
  
  /**
   * Test method for
   * {@link uno.informatics.data.feature.array.ArrayFeatureDataset#readFeatureDatasetFromTextFile(java.io.File, uno.informatics.common.io.FileType)}
   * .
   */
  @Test
  public void testReadFeatureDatasetFromTextFileWithNameAndIDRowHeaders()
  {
    try
    {
      File file = new File(ArrayFeatureDataset.class.getResource(OBJECT_TABLE_WITH_ROW_NAMES_IDS).getFile());
      
      FeatureDataset dataset = ArrayFeatureDataset.readFeatureDatasetFromTextFile(file, getFileType());
      
      checkCompleteDataset(file.getName(), file.getName(), "Dataset loading from " + file.getAbsolutePath(),
          STRING_FEATURES, dataset, ROW_HEADERS_WITH_ID, IDS_HEADER_FEATURE, true);
          
    }
    catch (DatasetException e)
    {
      e.printStackTrace();
      
      fail(e.getLocalizedMessage());
    }
  }
  
  /**
   * Test method for
   * {@link uno.informatics.data.feature.array.ArrayFeatureDataset#readFeatureDatasetFromTextFile(java.io.File, uno.informatics.common.io.FileType)}
   * .
   */
  @Test
  public void testReadFeatureDatasetFromTextFileWithTypeNameRowHeaders()
  {
    try
    {
      File file = new File(ArrayFeatureDataset.class.getResource(OBJECT_TABLE_WITH_TYPE_ROW_NAMES).getFile());
      
      FeatureDataset dataset = ArrayFeatureDataset.readFeatureDatasetFromTextFile(file, getFileType());
      
      checkCompleteDataset(file.getName(), file.getName(), "Dataset loading from " + file.getAbsolutePath(),
          OBJECT_FEATURES, dataset, ROW_HEADERS, NAMES_HEADER_FEATURE, false);
          
    }
    catch (DatasetException e)
    {
      e.printStackTrace();
      
      fail(e.getLocalizedMessage());
    }
  }
  
  /**
   * Test method for
   * {@link uno.informatics.data.feature.array.ArrayFeatureDataset#readFeatureDatasetFromTextFile(java.io.File, uno.informatics.common.io.FileType)}
   * .
   */
  @Test
  public void testReadFeatureDatasetFromTextFileWithTypeNameAndIDRowHeaders()
  {
    try
    {
      File file = new File(ArrayFeatureDataset.class.getResource(OBJECT_TABLE_WITH_TYPE_ROW_NAMES_IDS).getFile());
      
      FeatureDataset dataset = ArrayFeatureDataset.readFeatureDatasetFromTextFile(file, getFileType());
      
      checkCompleteDataset(file.getName(), file.getName(), "Dataset loading from " + file.getAbsolutePath(),
          OBJECT_FEATURES, dataset, ROW_HEADERS_WITH_ID, IDS_HEADER_FEATURE, false);
          
    }
    catch (DatasetException e)
    {
      e.printStackTrace();
      
      fail(e.getLocalizedMessage());
    }
  }
  
  /**
   * Test method for
   * {@link uno.informatics.data.feature.array.ArrayFeatureDataset#readFeatureDatasetFromTextFile(java.io.File, uno.informatics.common.io.FileType)}
   * .
   */
  @Test
  public void testReadFeatureDatasetFromTextFileWithTypeMinNameRowHeaders()
  {
    try
    {
      File file = new File(ArrayFeatureDataset.class.getResource(OBJECT_TABLE_WITH_TYPE_MIN_ROW_NAMES).getFile());
      
      FeatureDataset dataset = ArrayFeatureDataset.readFeatureDatasetFromTextFile(file, getFileType());
      
      checkCompleteDataset(file.getName(), file.getName(), "Dataset loading from " + file.getAbsolutePath(),
          OBJECT_FEATURES_MIN, dataset, ROW_HEADERS, NAMES_HEADER_FEATURE, false);
          
    }
    catch (DatasetException e)
    {
      e.printStackTrace();
      
      fail(e.getLocalizedMessage());
    }
  }
  
  /**
   * Test method for
   * {@link uno.informatics.data.feature.array.ArrayFeatureDataset#readFeatureDatasetFromTextFile(java.io.File, uno.informatics.common.io.FileType)}
   * .
   */
  @Test
  public void testReadFeatureDatasetFromTextFileWithTypeMinNameAndIDRowHeaders()
  {
    try
    {
      File file = new File(ArrayFeatureDataset.class.getResource(OBJECT_TABLE_WITH_TYPE_MIN_ROW_NAMES_IDS).getFile());
      
      FeatureDataset dataset = ArrayFeatureDataset.readFeatureDatasetFromTextFile(file, getFileType());
      
      checkCompleteDataset(file.getName(), file.getName(), "Dataset loading from " + file.getAbsolutePath(),
          OBJECT_FEATURES_MIN, dataset, ROW_HEADERS_WITH_ID, IDS_HEADER_FEATURE, false);
          
    }
    catch (DatasetException e)
    {
      e.printStackTrace();
      
      fail(e.getLocalizedMessage());
    }
  }
  
  /**
   * Test method for
   * {@link uno.informatics.data.feature.array.ArrayFeatureDataset#readFeatureDatasetFromTextFile(java.io.File, uno.informatics.common.io.FileType)}
   * .
   */
  @Test
  public void testReadFeatureDatasetFromTextFileWithTypeMinMaxNameRowHeaders()
  {
    try
    {
      File file = new File(ArrayFeatureDataset.class.getResource(OBJECT_TABLE_WITH_TYPE_MIN_MAX_ROW_NAMES).getFile());
      
      FeatureDataset dataset = ArrayFeatureDataset.readFeatureDatasetFromTextFile(file, getFileType());
      
      checkCompleteDataset(file.getName(), file.getName(), "Dataset loading from " + file.getAbsolutePath(),
          OBJECT_FEATURES_MIN_MAX, dataset, ROW_HEADERS, NAMES_HEADER_FEATURE, false);
          
    }
    catch (DatasetException e)
    {
      e.printStackTrace();
      
      fail(e.getLocalizedMessage());
    }
  }
  
  /**
   * Test method for
   * {@link uno.informatics.data.feature.array.ArrayFeatureDataset#readFeatureDatasetFromTextFile(java.io.File, uno.informatics.common.io.FileType)}
   * .
   */
  @Test
  public void testReadFeatureDatasetFromTextFileWithTypeMinMaxNameAndIDRowHeaders()
  {
    try
    {
      File file = new File(ArrayFeatureDataset.class.getResource(OBJECT_TABLE_WITH_TYPE_MIN_MAX_ROW_NAMES_IDS).getFile());
      
      FeatureDataset dataset = ArrayFeatureDataset.readFeatureDatasetFromTextFile(file, getFileType());
      
      checkCompleteDataset(file.getName(), file.getName(), "Dataset loading from " + file.getAbsolutePath(),
          OBJECT_FEATURES_MIN_MAX, dataset, ROW_HEADERS_WITH_ID, IDS_HEADER_FEATURE, false);
    }
    catch (DatasetException e)
    {
      e.printStackTrace();
      
      fail(e.getLocalizedMessage());
    }
  }
}
