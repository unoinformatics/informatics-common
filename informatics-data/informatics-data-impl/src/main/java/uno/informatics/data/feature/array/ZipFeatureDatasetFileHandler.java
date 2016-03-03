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

import java.io.File;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import uno.informatics.data.pojo.FileHandler;

/**
 * @author Guy Davenport
 *
 */
public class ZipFeatureDatasetFileHandler extends FileHandler
{
	protected static final String DATA_DIRECTORY = "data" ;
	
	protected static final String META_DATA_DIRECTORY = "metadata" ;
	
	protected static final String FEATURES_ENTRY =  META_DATA_DIRECTORY + "/features.xml";
	
	protected static final String IDENTIFICATION_ENTRY =  META_DATA_DIRECTORY + "/identification.xml";
	
	protected static final String FILE_TYPE_ENTRY =  META_DATA_DIRECTORY + "/fileType.xml";
	
	protected static final String ROW_HEADER_FEATURE_ENTRY =  META_DATA_DIRECTORY + "/rowHeaderFeature.xml";
	
	protected static final String ROW_HEADER_ENTRY =  META_DATA_DIRECTORY + "/rowHeaders.xml";
	
	protected static final String DATA_VALUES_ENTRY_PREIFX =  DATA_DIRECTORY + "/values";
	
	protected static final String CSV_SUFFIX = ".csv";
	
	protected static final String TXT_SUFFIX = ".txt";
	
	protected static final String XLS_SUFFIX = ".xls";
	
	protected static final String XLSX_SUFFIX = ".xlsx";
	
	protected static final String SPREADSHEET_NAME = "values";
	
	
  public ZipFeatureDatasetFileHandler(File file)
  {
	  super(file);
  }

	
  protected XStream createXStream()
  {
  	XStream xstream = new XStream(new StaxDriver());
  	
  	xstream.setClassLoader(getClass().getClassLoader()) ;
  	
  	return xstream ;
  }
}
