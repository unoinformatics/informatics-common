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
import java.nio.file.Path;
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
	public static final RowReader createRowReader(Path path, FileType type, int... options) throws IOException
	{
		RowReader reader = null ;
		
		switch (type)
		{
			case CSV:
				TextFileRowReader textFileRowStringReader = new TextFileRowReader(path) ;

				textFileRowStringReader.setDelimiterString(COMMA) ;
				
	                        textFileRowStringReader.setOptions(getOptions(options));

				reader = textFileRowStringReader ;
				break;
			case TXT:
				textFileRowStringReader = new TextFileRowReader(path) ;

				textFileRowStringReader.setDelimiterString(TAB) ;
				
	                        textFileRowStringReader.setOptions(getOptions(options));

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
			FileType type, int... options) throws IOException
	{
		RowReader reader = null ;
		
		switch (type)
		{
			case CSV:
				TextFileRowReader textFileRowStringReader = new TextFileRowReader(bufferedReader) ;

				textFileRowStringReader.setDelimiterString(COMMA) ;
				
                                textFileRowStringReader.setOptions(getOptions(options));

				reader = textFileRowStringReader ;
				break;
			case TXT:
				textFileRowStringReader = new TextFileRowReader(bufferedReader) ;

				textFileRowStringReader.setDelimiterString(TAB) ;
				
	                        textFileRowStringReader.setOptions(getOptions(options));

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
	
	public static final RowWriter createRowWriter(Path filePath, FileType type, int... options) throws IOException
	{
		RowWriter writer = null ;
		
		switch (type)
		{
			case CSV:
				TextFileRowWriter textFileRowStringWriter = new TextFileRowWriter(filePath) ;

				textFileRowStringWriter.setDelimiterString(COMMA) ;

				writer = textFileRowStringWriter ;
				break;
			case TXT:
				textFileRowStringWriter = new TextFileRowWriter(filePath) ;

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
	
	public static final RowWriter createRowWriter(BufferedWriter bufferedWriter, FileType type, int... options) throws IOException
	{
		RowWriter writer = null ;
		
		switch (type)
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
	
	public static final List<String> getSheets(Path filePath, FileType type, int... options) throws IOException
	{
	        // validate arguments

	        if (filePath == null) {
	            throw new IllegalArgumentException("File path not defined.");
	        }

	        if (!filePath.toFile().exists()) {
	            throw new IOException("File does not exist : " + filePath + ".");
	        }

	        if (type == null) {
	            throw new IllegalArgumentException("File type not defined.");
	        }

	        if (type != FileType.TXT && type != FileType.CSV) {
	            throw new IllegalArgumentException(
	                    String.format("Only file types TXT and CSV are supported. Got: %s.", type));
	        }
	
			switch (type)
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

	}
	
	private static final int getOptions(int... options)
	{
	    int combinedOptions = 0 ;
	    
	    for (int i = 0 ; i < options.length ;  ++i)
	        combinedOptions = combinedOptions | options[i] ;
	    
	    return combinedOptions ;
	}
}
