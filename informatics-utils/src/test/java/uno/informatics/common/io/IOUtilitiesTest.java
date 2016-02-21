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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import uno.informatics.common.io.text.TextFileRowReader;

/**
 * @author Guy Davenport
 *
 */
public class IOUtilitiesTest
{

	/**
	 * Test method for {@link uno.informatics.common.io.IOUtilities#createRowReader(uno.informatics.common.io.FileProperties)}.
	 */
	@Test
	public void testCreateRowReader()
	{
		// TODO xsl, xlsx support
		try
		{
	    assertEquals(TextFileRowReader.class, IOUtilities.createRowReader(new FileProperties(this.getClass().getResource("/object_table.txt").getFile())).getClass()) ;
	    assertEquals(TextFileRowReader.class, IOUtilities.createRowReader(new FileProperties(this.getClass().getResource("/object_table.txt").getFile(), FileType.TXT)).getClass()) ;
	    assertEquals(TextFileRowReader.class, IOUtilities.createRowReader(new FileProperties(this.getClass().getResource("/object_table.csv").getFile(), FileType.CSV)).getClass()) ;
	    //assertEquals(null, IOUtilities.createRowReader(new FileProperties(this.getClass().getResource("/object_table.xls").getFile(), FileType.XLS)).getClass()) ;
	    //assertEquals(null, IOUtilities.createRowReader(new FileProperties(this.getClass().getResource("/object_table.xlsx").getFile(), FileType.XLSX)).getClass()) ;
	  }
	  catch (IOException e)
	  {
	    fail(e.getLocalizedMessage()) ;
	  }
	}

	/**
	 * Test method for {@link uno.informatics.common.io.IOUtilities#getSheets(uno.informatics.common.io.FileProperties)}.
	 */
	@Test
	public void testGetSheets()
	{
		// TODO xsl, xlsx support
		ArrayList<String> expected = new ArrayList<String>() ;
		
		expected.add("sheet2") ;
		expected.add("Sheet1") ;
		
		try
    {
	    assertEquals(new ArrayList<String>(), IOUtilities.getSheets(new FileProperties(this.getClass().getResource("/object_table.txt").getFile()))) ;
	    assertEquals(new ArrayList<String>(), IOUtilities.getSheets(new FileProperties(this.getClass().getResource("/object_table.txt").getFile(), FileType.TXT))) ;
	    assertEquals(new ArrayList<String>(), IOUtilities.getSheets(new FileProperties(this.getClass().getResource("/object_table.csv").getFile(), FileType.CSV))) ;
	    //assertEquals(expected, IOUtilities.getSheets(new FileProperties(this.getClass().getResource("/object_table.xls").getFile(), FileType.XLS))) ;
	    //assertEquals(expected, IOUtilities.getSheets(new FileProperties(this.getClass().getResource("/object_table.xlsx").getFile(), FileType.XLSX))) ;
    }
    catch (IOException e)
    {
	    fail(e.getLocalizedMessage()) ;
    }
	}

}
