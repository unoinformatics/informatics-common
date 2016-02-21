/*******************************************************************************
 * Copyright 2015 Guy Davenport
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
package uno.informatics.common.io;

import static uno.informatics.common.io.TextFileHandler.COMMA;
import static uno.informatics.common.io.TextFileHandler.TAB;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uno.informatics.common.io.text.TextFileRowReader;
import uno.informatics.common.io.text.TextFileRowWriter;

/**
 * @author Guy Davenport
 *
 */
public class IOUtilities
{
	public static final RowReader createRowReader(FileProperties fileProperties) throws IOException
	{
		return createRowReader(fileProperties.getFile(), fileProperties.getFileType()) ;
	}

	public static final RowReader createRowReader(File file, FileType fileType) throws IOException
	{
		RowReader reader = null ;
		
		switch (fileType)
		{
			case CSV:
				TextFileRowReader textFileRowStringReader = new TextFileRowReader(file) ;

				textFileRowStringReader.setDelimiterString(COMMA) ;

				reader = textFileRowStringReader ;
				break;
			case TXT:
				textFileRowStringReader = new TextFileRowReader(file) ;

				textFileRowStringReader.setDelimiterString(TAB) ;

				reader = textFileRowStringReader ;
				break;
			case XLS:
				throw new IOException("XLS Type not supported") ;
				//break;
			case XLSX:
				throw new IOException("XLSX Type not supported") ;
				//break;
			default:
				break;	
		}
		
		return reader ;
	}	
	
	public static RowReader createRowReader(BufferedReader bufferedReader,
			FileType fileType) throws IOException
	{
		RowReader reader = null ;
		
		switch (fileType)
		{
			case CSV:
				TextFileRowReader textFileRowStringReader = new TextFileRowReader(bufferedReader) ;

				textFileRowStringReader.setDelimiterString(COMMA) ;

				reader = textFileRowStringReader ;
				break;
			case TXT:
				textFileRowStringReader = new TextFileRowReader(bufferedReader) ;

				textFileRowStringReader.setDelimiterString(TAB) ;

				reader = textFileRowStringReader ;
				break;
			case XLS:
				throw new IOException("XLS Type not supported") ;
				//break;
			case XLSX:
				throw new IOException("XLSX Type not supported") ;
				//break;
			default:
				break;	
		}
		
		return reader ;
	}
	
	public static final RowWriter createRowWriter(FileProperties fileProperties) throws IOException
	{
		return createRowWriter(fileProperties.getFile(), fileProperties.getFileType()) ;
	}

	public static final RowWriter createRowWriter(File file, FileType fileType) throws IOException
	{
		RowWriter writer = null ;
		
		switch (fileType)
		{
			case CSV:
				TextFileRowWriter textFileRowStringWriter = new TextFileRowWriter(file) ;

				textFileRowStringWriter.setDelimiterString(COMMA) ;

				writer = textFileRowStringWriter ;
				break;
			case TXT:
				textFileRowStringWriter = new TextFileRowWriter(file) ;

				textFileRowStringWriter.setDelimiterString(TAB) ;

				writer = textFileRowStringWriter ;
				break;
			case XLS:
				throw new IOException("XLS Type not supported") ;
				//break;
			case XLSX:
				throw new IOException("XLSX Type not supported") ;
				//break;
			default:
				break;	
		}
		
		return writer ;
	}	
	
	public static final RowWriter createRowWriter(BufferedWriter bufferedWriter, FileType fileType) throws IOException
	{
		RowWriter writer = null ;
		
		switch (fileType)
		{
			case CSV:
				TextFileRowWriter textFileRowStringWriter = new TextFileRowWriter(bufferedWriter) ;

				textFileRowStringWriter.setDelimiterString(COMMA) ;

				writer = textFileRowStringWriter ;
				break;
			case TXT:
				textFileRowStringWriter = new TextFileRowWriter(bufferedWriter) ;

				textFileRowStringWriter.setDelimiterString(TAB) ;

				writer = textFileRowStringWriter ;
				break;
			case XLS:
				throw new IOException("XLS Type not supported") ;
				//break;
			case XLSX:
				throw new IOException("XLSX Type not supported") ;
				//break;
			default:
				break;	
		}
		
		return writer ;
	}
	
	public static final List<String> getSheets(FileProperties fileProperties) throws IOException
	{
		if (fileProperties == null)
			throw new IOException("File properties not defined!") ;
	
		if (fileProperties.getFile() == null)
			throw new IOException("File not defined!") ;
	
		if (fileProperties.getFileType() == null)
			throw new IOException("File type not defined!") ;
	
		//try
		//{
			switch (fileProperties.getFileType())
			{
				case CSV:
					return new ArrayList<String>() ;
				case TXT:
					return new ArrayList<String>() ;
				case XLS:
					return new ArrayList<String>() ;
					//JXLExcelRowStringReader jxlExcelRowStringReader = new JXLExcelRowStringReader(fileProperties.getFile()) ;
	
					//return jxlExcelRowStringReader.getAllSpreadSheetNames() ;
				case XLSX:
					return new ArrayList<String>() ;
					//jxlExcelRowStringReader = new JXLExcelRowStringReader(fileProperties.getFile()) ;
	
					//return jxlExcelRowStringReader.getAllSpreadSheetNames() ;
				default:
					return new ArrayList<String>() ;
			}
		/*}
		catch (IOException e)
		{
			throw new DatasetException(e) ;
		}*/
	}
}
