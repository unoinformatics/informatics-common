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
package uno.informatics.data.matrix.array;

import static uno.informatics.common.Constants.UNKNOWN_COUNT;
import static uno.informatics.common.Constants.UNKNOWN_INDEX;

import java.util.LinkedList;
import java.util.List;

import uno.informatics.common.ConversionUtilities;
import uno.informatics.common.io.FileProperties;
import uno.informatics.common.io.IOUtilities;
import uno.informatics.common.io.RowReader;
import uno.informatics.data.Feature;
import uno.informatics.data.MatrixDataset;
import uno.informatics.data.dataset.DatasetException;
import uno.informatics.data.utils.DatasetUtils;

/**
 * @author Guy Davenport
 *
 */
public class DoubleArrayMatrixDataset extends ArrayMatrixDataset<Double>
{
	public DoubleArrayMatrixDataset(String name, Feature elementFeature, Double[][] values)
	{
		super(name, elementFeature, values);
	}

	public DoubleArrayMatrixDataset(String name, Feature elementFeature, List<List<Double>> values)
	{
		super(name, elementFeature, values);
	}

	public DoubleArrayMatrixDataset(String uniqueIdentifier, String name, Feature elementFeature,
			Double[][] values)
	{
		super(uniqueIdentifier, name, elementFeature, values);
	}

	public DoubleArrayMatrixDataset(String uniqueIdentifier, String name, Feature elementFeature,
			List<List<Double>> values)
	{
		super(uniqueIdentifier, name, elementFeature, values);
	}

	public DoubleArrayMatrixDataset(String uniqueIdentifier, String name,
			String desription, Feature elementFeature, Double[][] values)
	{
		super(uniqueIdentifier, name, desription, elementFeature, values);
	}

	public DoubleArrayMatrixDataset(String uniqueIdentifier, String name,
			String desription, Feature elementFeature, List<List<Double>> values)
	{
		super(uniqueIdentifier, name, desription, elementFeature, values);
	}

	public static final MatrixDataset<Double> createMatrixDataset(String uniqueIdentifier, String name, String description, 
			Feature elementFeature, FileProperties fileProperties, Feature rowHeaderFeature, Feature columnHeaderFeature) throws DatasetException
	{
		List<Object> columnHeaders = null ;
		List<Object> rowHeaders = null ;
		RowReader reader = null ;
		List<List<Double>> rowList = new LinkedList<List<Double>>() ;

		if (fileProperties == null)
			throw new DatasetException("File properties not defined!") ;

		if (fileProperties.getFile() == null)
			throw new DatasetException("File not defined!") ;

		if (fileProperties.getFileType() == null)
			throw new DatasetException("File type not defined!") ;

		if (fileProperties.getRowHeaderPosition() > UNKNOWN_INDEX && 
				fileProperties.getDataRowPosition() > UNKNOWN_INDEX && 
				fileProperties.getDataRowPosition() <= fileProperties.getColumnHeaderPosition())
			throw new DatasetException("Column header position : " + 
					fileProperties.getDataRowPosition() + " must be before data position : " + fileProperties.getColumnHeaderPosition()) ;

		if (!fileProperties.getFile().exists())
			throw new DatasetException("File does not exist : " + fileProperties.getFile()) ;

		try
		{
			reader = IOUtilities.createRowReader(fileProperties) ;

			if (reader != null && reader.ready())
			{
				int columnCount = UNKNOWN_COUNT ;
				int row = 0 ;
				List<String> headers ;
				String rowHeader ;

				if (fileProperties.hasColumnHeader() && columnHeaderFeature != null && reader.nextRow())
				{
					++row ;
					
					if (fileProperties.getColumnHeaderPosition() > UNKNOWN_INDEX) 
						while (row < fileProperties.getColumnHeaderPosition() && reader.nextRow())
							++row ;

					headers = reader.getRowCellsAsString() ;

					if (fileProperties.hasRowHeader() && rowHeaderFeature != null)
						headers.remove(0) ; // ignore cell!

					columnCount = headers.size() ;

					columnHeaders = ConversionUtilities.convertToObjectList(headers, DatasetUtils.getConversionType(columnHeaderFeature)) ;
				}

				List<Double> cells ;
				
				if (reader.nextRow())
				{
					++row ;
					
					if (fileProperties.getDataRowPosition() > UNKNOWN_INDEX) 
						while (row < fileProperties.getDataRowPosition() && reader.nextRow())
							++row ;

					if (fileProperties.hasRowHeader() && rowHeaderFeature != null)
					{
						rowHeaders = new LinkedList<Object>() ;

						int rowHeaderType = DatasetUtils.getConversionType(rowHeaderFeature) ;
						
						reader.nextColumn() ;
						
						rowHeader = reader.getCellAsString() ;
						
						reader.nextColumn() ;

						cells = reader.getRowCellsAsDouble() ;

						rowHeaders.add(ConversionUtilities.convertToObject(rowHeader, rowHeaderType)) ;

						if (columnCount == UNKNOWN_COUNT)
						{
							columnCount = cells.size() ;
						}
						else
						{
							if (cells.size() != columnCount)
								throw new DatasetException("Rows are not all the same size!") ;
						}

						rowList.add(cells) ;

						++row ;

						while (reader.nextRow())
						{
							reader.nextColumn() ;
							
							rowHeader = reader.getCellAsString() ;
							
							reader.nextColumn() ;
							
							cells = reader.getRowCellsAsDouble() ;

							rowHeaders.add(ConversionUtilities.convertToObject(rowHeader, rowHeaderType)) ;

							if (cells.size() != columnCount)
								throw new DatasetException("Rows are not all the same size!") ;

							rowList.add(cells) ;

							++row ;
						}	
					}
					else
					{
						cells = reader.getRowCellsAsDouble() ;

						if (columnCount == UNKNOWN_COUNT)
						{
							columnCount = cells.size() ;
						}
						else
						{
							if (cells.size() != columnCount)
								throw new DatasetException("Rows are not all the same size!") ;
						}

						rowList.add(cells) ;

						++row ;

						while (reader.nextRow())
						{
							cells = reader.getRowCellsAsDouble() ;

							if (cells.size() != columnCount)
								throw new DatasetException("Rows are not all the same size!") ;

							rowList.add(cells) ;

							++row ;
						}
					}
				}      	
			}

			if (reader != null)
				reader.close() ;

			///updateRowsScales(newFeatures, rowList) ;

			DoubleArrayMatrixDataset matrix = new DoubleArrayMatrixDataset(uniqueIdentifier, name, description, elementFeature, rowList) ;

			matrix.setColumnHeaderFeature(columnHeaderFeature) ;
			matrix.setRowHeaderFeature(rowHeaderFeature) ;

			if (columnHeaders != null && !columnHeaders.isEmpty())
				matrix.setColumnHeaders(columnHeaders) ;
			
			if (rowHeaders != null && !rowHeaders.isEmpty())
				matrix.setRowHeaders(rowHeaders) ;

			return matrix ;

		}
		catch (Exception e)
		{
			throw new DatasetException(e) ;
		}
	}

	/* (non-Javadoc)
	 * @see uno.informatics.data.matrix.array.ArrayMatrixDataset#createArrayArray(int)
	 */
	@Override
	protected final Double[][] createArrayArray(int size)
	{
		return new Double[size][];
	}

	/* (non-Javadoc)
	 * @see uno.informatics.data.matrix.array.ArrayMatrixDataset#createArray(int)
	 */
	@Override
	protected final Double[] createArray(int size)
	{
		return new Double[size];
	}
}
