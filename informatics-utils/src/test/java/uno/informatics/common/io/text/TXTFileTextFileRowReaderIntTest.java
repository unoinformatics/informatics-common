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
package uno.informatics.common.io.text;

import java.io.FileNotFoundException;
import java.io.IOException;

import uno.informatics.common.io.RowReader;
import uno.informatics.common.io.RowReaderIntTest;
import uno.informatics.common.io.TextFileHandler;

public class TXTFileTextFileRowReaderIntTest extends RowReaderIntTest
{
	private static final String FILE = "/int_table.txt";
	
	protected RowReader createReader() throws FileNotFoundException, IOException 
	{
		TextFileRowReader reader = new TextFileRowReader(getClass().getResource(FILE).getPath()) ;
		
		reader.setDelimiterString(TextFileHandler.TAB);
		
		return reader ;
	}
}
