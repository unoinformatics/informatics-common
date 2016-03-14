
package uno.informatics.common.io.text;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import uno.informatics.common.io.TextFileHandler;

public class DoubleCSVTextFileRowReaderTest {
    
    @Test
    public void testReadCells1a() {
        try {
            
            TextFileRowReader reader = new TextFileRowReader(getClass().getResource("/double_table1.csv").getPath()) ;
            
            reader.setDelimiterString(TextFileHandler.COMMA);
  
            assertTrue(reader.ready());
            
            String[] header1 = new String[]
                    {"NAME", " Alice", null," Bob  "," Carol"," Bob"} ;
            
            String[] header2 = new String[]
                    {"ID  ", " acc-1", " acc-2"," acc-3",null," acc-5"} ;
            
            double[][] expected = new double[][] 
            {
                {0.0,0.2,0.4,0.6,0.8},
                {0.2,0.0,0.2,0.4,0.6},
                {0.4,0.2,0.0,0.1,0.4},
                {0.6,0.4,0.1,0.0,0.2},
                {0.8,0.6,0.4,0.2,0.0}
            };
            
            assertTrue("Missing row 1", reader.nextRow());
            
            assertArrayEquals("Headers 1 not correct", header1, reader.getRowCellsAsStringArray());

            assertTrue("Missing row 2", reader.nextRow());
            
            assertArrayEquals("Headers 2 not correct", header2, reader.getRowCellsAsStringArray());
            
            int i = 0;

            while (i < expected.length && reader.nextRow()) {
                assertArrayEquals("row " + i + " not equal", expected[i], reader.getRowCellsAsDoubleArray(), 0.0000000001);
                ++i;
            }

            assertFalse("Still rows to read!", reader.nextRow());

            reader.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            fail(e.getLocalizedMessage());
        }
    }
    
    @Test
    public void testReadCells1b() {
        try {
            
            TextFileRowReader reader = new TextFileRowReader(getClass().getResource("/double_table1.csv").getPath()) ;
            
            reader.setDelimiterString(TextFileHandler.COMMA);
            
            reader.setOptions(TextFileRowReader.REMOVE_WHITE_SPACE);
            
            assertTrue(reader.ready());
            
            String[] header1 = new String[]
                    {"NAME", "Alice", null,"Bob","Carol","Bob"} ;
            
            String[] header2 = new String[]
                    {"ID", "acc-1", "acc-2","acc-3",null,"acc-5"} ;
            
            double[][] expected = new double[][] 
            {
                {0.0,0.2,0.4,0.6,0.8},
                {0.2,0.0,0.2,0.4,0.6},
                {0.4,0.2,0.0,0.1,0.4},
                {0.6,0.4,0.1,0.0,0.2},
                {0.8,0.6,0.4,0.2,0.0}
            };
            
            assertTrue("Missing row 1", reader.nextRow());
            
            assertArrayEquals("Headers 1 not correct", header1, reader.getRowCellsAsStringArray());

            assertTrue("Missing row 2", reader.nextRow());
            
            assertArrayEquals("Headers 2 not correct", header2, reader.getRowCellsAsStringArray());
            
            int i = 0;

            while (i < expected.length && reader.nextRow()) {
                assertArrayEquals("row " + i + " not equal", expected[i], reader.getRowCellsAsDoubleArray(), 0.0000000001);
                ++i;
            }

            assertFalse("Still rows to read!", reader.nextRow());

            reader.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            fail(e.getLocalizedMessage());
        }
    }

    
    @Test
    public void testReadCells2a() {
        try {
            
            TextFileRowReader reader = new TextFileRowReader(getClass().getResource("/double_table2.csv").getPath()) ;
            
            reader.setDelimiterString(TextFileHandler.COMMA);

            assertTrue(reader.ready());
            
            String[] header1 = new String[]
                    {"NAME", " Alice", null," Bob  ",null,null} ;
            
            String[] header2 = new String[]
                    {"ID  ", " acc-1", " acc-2",null,null,null} ;
            
            double[][] expected = new double[][] 
            {
                {0.0,0.2,0.4,0.6,0.8},
                {0.2,0.0,0.2,0.4,0.6},
                {0.4,0.2,0.0,0.1,0.4},
                {0.6,0.4,0.1,0.0,0.2},
                {0.8,0.6,0.4,0.2,0.0}
            };
            
            assertTrue("Missing row 1", reader.nextRow());
            
            assertArrayEquals("Headers 1 not correct", header1, reader.getRowCellsAsStringArray());

            assertTrue("Missing row 2", reader.nextRow());
            
            assertArrayEquals("Headers 2 not correct", header2, reader.getRowCellsAsStringArray());
            
            int i = 0;

            while (i < expected.length && reader.nextRow()) {
                assertArrayEquals("row " + i + " not equal", expected[i], reader.getRowCellsAsDoubleArray(), 0.0000000001);
                ++i;
            }

            assertFalse("Still rows to read!", reader.nextRow());

            reader.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            fail(e.getLocalizedMessage());
        }
    }
    
    @Test
    public void testReadCells2b() {
        try {
            
            TextFileRowReader reader = new TextFileRowReader(getClass().getResource("/double_table2.csv").getPath()) ;
            
            reader.setDelimiterString(TextFileHandler.COMMA);
            
            reader.setOptions(TextFileRowReader.REMOVE_WHITE_SPACE);
            
            assertTrue(reader.ready());
            
            String[] header1 = new String[]
                    {"NAME", "Alice", null,"Bob",null,null} ;
            
            String[] header2 = new String[]
                    {"ID", "acc-1", "acc-2",null,null,null} ;
            
            double[][] expected = new double[][] 
            {
                {0.0,0.2,0.4,0.6,0.8},
                {0.2,0.0,0.2,0.4,0.6},
                {0.4,0.2,0.0,0.1,0.4},
                {0.6,0.4,0.1,0.0,0.2},
                {0.8,0.6,0.4,0.2,0.0}
            };
            
            assertTrue("Missing row 1", reader.nextRow());
            
            assertArrayEquals("Headers 1 not correct", header1, reader.getRowCellsAsStringArray());

            assertTrue("Missing row 2", reader.nextRow());
            
            assertArrayEquals("Headers 2 not correct", header2, reader.getRowCellsAsStringArray());
            
            int i = 0;

            while (i < expected.length && reader.nextRow()) {
                assertArrayEquals("row " + i + " not equal", expected[i], reader.getRowCellsAsDoubleArray(), 0.0000000001);
                ++i;
            }

            assertFalse("Still rows to read!", reader.nextRow());

            reader.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            fail(e.getLocalizedMessage());
        }
    }
    
    @Test
    public void testReadCells3a() {
        try {
            
            TextFileRowReader reader = new TextFileRowReader(getClass().getResource("/double_table3.csv").getPath()) ;
            
            reader.setDelimiterString(TextFileHandler.COMMA);
                 
            assertTrue(reader.ready());
            
            String[] header1 = new String[]
                    {"NAME", "Alice", null, "Bob", null, null} ;
            
            String[] header2 = new String[]
                    {"ID", "1", "2", "3","4","5"} ;
            
            double[][] expected = new double[][] 
            {
                {0.0},
                {0.2,0.0},
                {0.4,0.2,0.0},
                {0.6,0.4,0.1,0.0},
                {0.8,0.6,0.4,0.2,0.0}
            };
            
            assertTrue("Missing row 1", reader.nextRow());
            
            assertArrayEquals("Headers 1 not correct", header1, reader.getRowCellsAsStringArray());

            assertTrue("Missing row 2", reader.nextRow());
            
            assertArrayEquals("Headers 2 not correct", header2, reader.getRowCellsAsStringArray());
            
            int i = 0;

            while (i < expected.length && reader.nextRow()) {
                assertArrayEquals("row " + i + " not equal", expected[i], reader.getRowCellsAsDoubleArray(), 0.0000000001);
                ++i;
            }

            assertFalse("Still rows to read!", reader.nextRow());

            reader.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            fail(e.getLocalizedMessage());
        }
    }
    
    @Test
    public void testReadCells3b() {
        try {
            
            TextFileRowReader reader = new TextFileRowReader(getClass().getResource("/double_table3.csv").getPath()) ;
            
            reader.setDelimiterString(TextFileHandler.COMMA);
            
            reader.setOptions(TextFileRowReader.REMOVE_WHITE_SPACE);
            
            assertTrue(reader.ready());
            
            String[] header1 = new String[]
                    {"NAME", "Alice", null, "Bob", null, null} ;
            
            String[] header2 = new String[]
                    {"ID", "1", "2", "3","4","5"} ;
            
            double[][] expected = new double[][] 
            {
                {0.0},
                {0.2,0.0},
                {0.4,0.2,0.0},
                {0.6,0.4,0.1,0.0},
                {0.8,0.6,0.4,0.2,0.0}
            };
            
            assertTrue("Missing row 1", reader.nextRow());
            
            assertArrayEquals("Headers 1 not correct", header1, reader.getRowCellsAsStringArray());

            assertTrue("Missing row 2", reader.nextRow());
            
            assertArrayEquals("Headers 2 not correct", header2, reader.getRowCellsAsStringArray());
            
            int i = 0;

            while (i < expected.length && reader.nextRow()) {
                assertArrayEquals("row " + i + " not equal", expected[i], reader.getRowCellsAsDoubleArray(), 0.0000000001);
                ++i;
            }

            assertFalse("Still rows to read!", reader.nextRow());

            reader.close();
        } catch (Exception e) {
            e.printStackTrace(System.err);
            fail(e.getLocalizedMessage());
        }
    }
}
