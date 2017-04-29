package uno.informatics.common.io.text;

import static org.junit.Assert.fail;

import org.junit.Test;

public class TextFileRowWriterTest {

    @Test
    public void testSetOptions() {
        
        String FILE = "target/test.csv";
        
        // good options
        try {
            TextFileRowWriter reader = new TextFileRowWriter(FILE);

            reader.setOptions(TextFileRowWriter.ADD_DOUBLE_QUOTES);
            reader.setOptions(TextFileRowWriter.ADD_SINGLE_QUOTES);

            reader.close(); 
        } catch (Exception e) {
            e.printStackTrace(System.err);
            fail(e.getLocalizedMessage());
        }
        
        // bad options
        try {
            TextFileRowWriter reader = new TextFileRowWriter(this.getClass().getResource(FILE).getPath());

            reader.setOptions(10000);

            reader.close();
            
            fail("should have failed on this option!") ;
        } catch (Exception e) {

        }
    }

}
