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
package uno.informatics.common.io;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestData
{
	protected final static String[] STRING_ROW1 = new String[] {"R1C1","R1C2","R1C3"} ;
	protected final static String[] STRING_ROW2 = new String[] {"R2C1","R2C2","R2C3"} ;
	protected final static String[] STRING_ROW3 = new String[] {"R3C1","R3C2","R3C3"} ;

	protected final static String[][] STRING_TABLE_AS_ARRAY = new String[][] {STRING_ROW1, STRING_ROW2, STRING_ROW3} ;
	
	protected static final List<List<Object>> STRING_TABLE_AS_LIST = new ArrayList<List<Object>>();
	
	static
	{
		STRING_TABLE_AS_LIST.add(new ArrayList<Object>(STRING_ROW1.length)) ;
		
		STRING_TABLE_AS_LIST.get(0).add(STRING_ROW1[0]) ;
		STRING_TABLE_AS_LIST.get(0).add(STRING_ROW1[1]) ;
		STRING_TABLE_AS_LIST.get(0).add(STRING_ROW1[2]) ;
		
		STRING_TABLE_AS_LIST.add(new ArrayList<Object>(STRING_ROW2.length)) ;
		
		STRING_TABLE_AS_LIST.get(1).add(STRING_ROW2[0]) ;
		STRING_TABLE_AS_LIST.get(1).add(STRING_ROW2[1]) ;
		STRING_TABLE_AS_LIST.get(1).add(STRING_ROW2[2]) ;
		
		STRING_TABLE_AS_LIST.add(new ArrayList<Object>(STRING_ROW2.length)) ;
		
		STRING_TABLE_AS_LIST.get(2).add(STRING_ROW3[0]) ;
		STRING_TABLE_AS_LIST.get(2).add(STRING_ROW3[1]) ;
		STRING_TABLE_AS_LIST.get(2).add(STRING_ROW3[2]) ;
	}
	
	protected static final List<List<String>> STRING_TABLE_AS_LIST2 = new ArrayList<List<String>>();
	
	static
	{
		STRING_TABLE_AS_LIST2.add(new ArrayList<String>(STRING_ROW1.length)) ;
		
		STRING_TABLE_AS_LIST2.get(0).add(STRING_ROW1[0]) ;
		STRING_TABLE_AS_LIST2.get(0).add(STRING_ROW1[1]) ;
		STRING_TABLE_AS_LIST2.get(0).add(STRING_ROW1[2]) ;
		
		STRING_TABLE_AS_LIST2.add(new ArrayList<String>(STRING_ROW2.length)) ;
		
		STRING_TABLE_AS_LIST2.get(1).add(STRING_ROW2[0]) ;
		STRING_TABLE_AS_LIST2.get(1).add(STRING_ROW2[1]) ;
		STRING_TABLE_AS_LIST2.get(1).add(STRING_ROW2[2]) ;
		
		STRING_TABLE_AS_LIST2.add(new ArrayList<String>(STRING_ROW2.length)) ;
		
		STRING_TABLE_AS_LIST2.get(2).add(STRING_ROW3[0]) ;
		STRING_TABLE_AS_LIST2.get(2).add(STRING_ROW3[1]) ;
		STRING_TABLE_AS_LIST2.get(2).add(STRING_ROW3[2]) ;
	}
	
	@SuppressWarnings("deprecation")
  protected final static Object[] OBJECT_ROW1 = new Object[] {1,1.1,"R1C3", true, new Date("12/12/2012")} ;
	@SuppressWarnings("deprecation")
  protected final static Object[] OBJECT_ROW2 = new Object[] {2,2.2,"R2C3", false, new Date("13/12/2012")} ;
	@SuppressWarnings("deprecation")
  protected final static Object[] OBJECT_ROW3 = new Object[] {3,3.3,"R3C3", true, new Date("14/12/2012")} ;

	protected final static Object[][] OBJECT_TABLE_AS_ARRAY = new Object[][] {OBJECT_ROW1, OBJECT_ROW2, OBJECT_ROW3} ;
	
	protected static final List<List<Object>> OBJECT_TABLE_AS_LIST = new ArrayList<List<Object>>();
	
	static
	{
		OBJECT_TABLE_AS_LIST.add(new ArrayList<Object>(OBJECT_ROW1.length)) ;
		
		OBJECT_TABLE_AS_LIST.get(0).add(OBJECT_ROW1[0]) ;
		OBJECT_TABLE_AS_LIST.get(0).add(OBJECT_ROW1[1]) ;
		OBJECT_TABLE_AS_LIST.get(0).add(OBJECT_ROW1[2]) ;
		OBJECT_TABLE_AS_LIST.get(0).add(OBJECT_ROW1[3]) ;
		OBJECT_TABLE_AS_LIST.get(0).add(OBJECT_ROW1[4]) ;
		
		OBJECT_TABLE_AS_LIST.add(new ArrayList<Object>(OBJECT_ROW2.length)) ;
		
		OBJECT_TABLE_AS_LIST.get(1).add(OBJECT_ROW2[0]) ;
		OBJECT_TABLE_AS_LIST.get(1).add(OBJECT_ROW2[1]) ;
		OBJECT_TABLE_AS_LIST.get(1).add(OBJECT_ROW2[2]) ;
		OBJECT_TABLE_AS_LIST.get(1).add(OBJECT_ROW2[3]) ;
		OBJECT_TABLE_AS_LIST.get(1).add(OBJECT_ROW2[4]) ;
		
		OBJECT_TABLE_AS_LIST.add(new ArrayList<Object>(OBJECT_ROW2.length)) ;
		
		OBJECT_TABLE_AS_LIST.get(2).add(OBJECT_ROW3[0]) ;
		OBJECT_TABLE_AS_LIST.get(2).add(OBJECT_ROW3[1]) ;
		OBJECT_TABLE_AS_LIST.get(2).add(OBJECT_ROW3[2]) ;
		OBJECT_TABLE_AS_LIST.get(2).add(OBJECT_ROW3[3]) ;
		OBJECT_TABLE_AS_LIST.get(2).add(OBJECT_ROW3[4]) ;
	}
}
