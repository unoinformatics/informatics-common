package uno.informatics.common.io.text;

import static org.junit.Assert.fail;

import org.junit.Test;

public class TextFileRowReaderTest {

    @Test
    public void testSetOptions() {
        
        String FILE = "/read/boolean_table_with_gaps.csv";
        
        // good options
        try {
            TextFileRowReader reader = new TextFileRowReader(this.getClass().getResource(FILE).getPath());

            reader.setOptions(TextFileRowReader.IGNORE_EMPTY_LINES);
            reader.setOptions(TextFileRowReader.PARSE_EMPTY_STRINGS);
            reader.setOptions(TextFileRowReader.CONVERT_VALUES);
            reader.setOptions(TextFileRowReader.IGNORE_MULTIPLE_DELIMITERS);
            reader.setOptions(TextFileRowReader.ROWS_SAME_SIZE_AS_FIRST);
            reader.setOptions(TextFileRowReader.REMOVE_WHITE_SPACE);
            reader.setOptions(TextFileRowReader.REMOVE_QUOTES);

            reader.close(); 
        } catch (Exception e) {
            e.printStackTrace(System.err);
            fail(e.getLocalizedMessage());
        }
        
        // bad options
        try {
            TextFileRowReader reader = new TextFileRowReader(this.getClass().getResource(FILE).getPath());

            reader.setOptions(10000);

            reader.close();
            
            fail("should have failed on this option!") ;
        } catch (Exception e) {

        }
    }

}
