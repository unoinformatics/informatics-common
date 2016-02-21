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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

import com.thoughtworks.xstream.XStream;

import uno.informatics.common.io.FileType;
import uno.informatics.common.io.IOUtilities;
import uno.informatics.common.io.RowWriter;
import uno.informatics.data.Dataset;
import uno.informatics.data.FeatureDataset;
import uno.informatics.data.FeatureDatasetRow;
import uno.informatics.data.dataset.DatasetException;
import uno.informatics.data.io.DatasetWriter;
import uno.informatics.data.pojo.EntityPojo;

/**
 * @author Guy Davenport
 *
 */
public class ZipFeatureDatasetWriter extends ZipFeatureDatasetFileHandler implements DatasetWriter
{
	private FileType fileType ;

	/**
	 * @param file
	 */
	public ZipFeatureDatasetWriter(File file)
	{
		super(file);

		setFileType(FileType.TXT) ;
	}

	public final FileType getFileType()
	{
		return fileType;
	}

	public final void setFileType(FileType fileType)
	{
		this.fileType = fileType;
	}

	/* (non-Javadoc)
	 * @see uno.informatics.data.io.DatasetWriter#write(uno.informatics.data.tests.feature.array.Dataset)
	 */
	@Override
	public void write(Dataset dataset) throws DatasetException
	{
		if (dataset instanceof FeatureDataset)
		{
			FeatureDataset featureDataset = (FeatureDataset)dataset ;
			
			try
			{
				ZipOutputStream outputStream = new ZipOutputStream(new FileOutputStream(getFile())) ;
	
				XStream xstream = createXStream() ;
	
				outputStream.putNextEntry(new ZipEntry(FEATURES_ENTRY)) ;
	
				xstream.toXML(featureDataset.getFeatures(), outputStream) ;
	
				outputStream.closeEntry();
	
				outputStream.putNextEntry(new ZipEntry(IDENTIFICATION_ENTRY)) ;
	
				xstream.toXML(new EntityPojo(dataset), outputStream) ;
	
				outputStream.closeEntry();
	
				outputStream.putNextEntry(new ZipEntry(FILE_TYPE_ENTRY)) ;
	
				xstream.toXML(fileType, outputStream) ;
				
				outputStream.closeEntry();
				
				if (featureDataset.getRowHeaderFeature() != null)
				{
					outputStream.putNextEntry(new ZipEntry(ROW_HEADER_FEATURE_ENTRY)) ;
				
					xstream.toXML(featureDataset.getRowHeaderFeature(), outputStream) ;
					
					outputStream.closeEntry();
				}
				
				String zipEntry = "unknown-type";
				
				switch (fileType)
				{
					case CSV:
						zipEntry = DATA_VALUES_ENTRY_PREIFX + CSV_SUFFIX ;
						break;
					case TXT:
						zipEntry = DATA_VALUES_ENTRY_PREIFX + TXT_SUFFIX ;
						break;
					case XLS:
						zipEntry = DATA_VALUES_ENTRY_PREIFX + XLS_SUFFIX ;
						break;
					case XLSX:
						zipEntry = DATA_VALUES_ENTRY_PREIFX + XLSX_SUFFIX ;
						break;
					default:
						break;	
				}
				
				outputStream.putNextEntry(new ZipEntry(zipEntry)) ;

				RowWriter writer = IOUtilities.createRowWriter(new BufferedWriter(new OutputStreamWriter(outputStream)), fileType) ;
				
				Iterator<FeatureDatasetRow> iterator = featureDataset.getRows().iterator() ;

				if (featureDataset.getRowHeaderFeature() != null)
				{
					FeatureDatasetRow row ;
					
					if (iterator.hasNext())
					{
						row = iterator.next() ;
						writer.writeCell(row.getHeader()) ;
						if (row.getColumnCount() > 0)
							writer.newColumn() ;
						writer.writeRowCells(row.getValues()) ;
						
						while (iterator.hasNext() && writer.newRow())
						{
							row = iterator.next() ;
							writer.writeCell(row.getHeader()) ;
							if (row.getColumnCount() > 0)
								writer.newColumn() ;
							writer.writeRowCells(row.getValues()) ;
						}
					}
				}
				else
				{
					if (iterator.hasNext())
					{
						writer.writeRowCells(iterator.next().getValues()) ;
						
						while (iterator.hasNext() && writer.newRow())
						{
							writer.writeRowCells(iterator.next().getValues()) ;
						}
					}
				}
	
				outputStream.closeEntry();
	
				outputStream.close(); 
				
				writer.close(); 
			}
			catch (ZipException e)
			{
				throw new DatasetException(e) ;
			}
			catch (IOException e)
			{
				throw new DatasetException(e) ;
			}
		}
		else
		{
			throw new DatasetException("Can not save dataset of type : " + dataset.getClass().getName()) ;
		}
	}

}
