package uno.informatics.data.tests.pojo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import uno.informatics.data.SimpleEntity;
import uno.informatics.data.pojo.DataPojo;
import uno.informatics.data.pojo.SimpleEntityPojo;

public class DataPojoTest {

    private static final String HEADER_ID_FORMAT = "Header%d";
    private static final String HEADER_NAME_FORMAT = "Header %d";
    
    private static final String DEFAULT_HEADER_ID_FORMAT = "entry%d";
    private static final String DEFAULT_HEADER_NAME_FORMAT = "Entry %d";

    @Test
    public void testUpdateOrCreateHeadersDefault() {
        
        SimpleEntity[] headers = DataPojo.updateOrCreateHeaders(
                new SimpleEntity[] {
                        new SimpleEntityPojo(String.format(DEFAULT_HEADER_ID_FORMAT, 1), String.format(DEFAULT_HEADER_NAME_FORMAT, 1)), 
                        new SimpleEntityPojo(String.format(DEFAULT_HEADER_ID_FORMAT, 2), String.format(DEFAULT_HEADER_NAME_FORMAT, 2)),
                        new SimpleEntityPojo(String.format(DEFAULT_HEADER_ID_FORMAT, 3), String.format(DEFAULT_HEADER_NAME_FORMAT, 3)), 
                        new SimpleEntityPojo(String.format(DEFAULT_HEADER_ID_FORMAT, 4), String.format(DEFAULT_HEADER_NAME_FORMAT, 4)),
                        new SimpleEntityPojo(String.format(DEFAULT_HEADER_ID_FORMAT, 5), String.format(DEFAULT_HEADER_NAME_FORMAT, 5))}
                
               , 5) ;
        
        checkHeaders(headers, DEFAULT_HEADER_ID_FORMAT, DEFAULT_HEADER_NAME_FORMAT) ;
        
        headers = DataPojo.updateOrCreateHeaders(
                new SimpleEntity[] {
                        new SimpleEntityPojo(String.format(DEFAULT_HEADER_ID_FORMAT, 1), String.format(DEFAULT_HEADER_NAME_FORMAT, 1)), 
                        new SimpleEntityPojo(String.format(DEFAULT_HEADER_ID_FORMAT, 2), String.format(DEFAULT_HEADER_NAME_FORMAT, 2)),
                        new SimpleEntityPojo(String.format(DEFAULT_HEADER_ID_FORMAT, 3), String.format(DEFAULT_HEADER_NAME_FORMAT, 3)), 
                        new SimpleEntityPojo(String.format(DEFAULT_HEADER_ID_FORMAT, 4), String.format(DEFAULT_HEADER_NAME_FORMAT, 4)) }
                
               , 5) ;
        
        checkHeaders(headers, DEFAULT_HEADER_ID_FORMAT, DEFAULT_HEADER_NAME_FORMAT) ;
        
        headers = DataPojo.updateOrCreateHeaders(
                new SimpleEntity[] {
                        new SimpleEntityPojo(String.format(DEFAULT_HEADER_ID_FORMAT, 1), String.format(DEFAULT_HEADER_NAME_FORMAT, 1)), 
                        null,
                        new SimpleEntityPojo(String.format(DEFAULT_HEADER_ID_FORMAT, 3), String.format(DEFAULT_HEADER_NAME_FORMAT, 3)), 
                        new SimpleEntityPojo(String.format(DEFAULT_HEADER_ID_FORMAT, 4), String.format(DEFAULT_HEADER_NAME_FORMAT, 4)),
                        null }
                
               , 5) ;
        
        checkHeaders(headers, DEFAULT_HEADER_ID_FORMAT, DEFAULT_HEADER_NAME_FORMAT) ;
        
        headers = DataPojo.updateOrCreateHeaders(
                new SimpleEntity[] {
                        new SimpleEntityPojo(String.format(DEFAULT_HEADER_ID_FORMAT, 1), String.format(DEFAULT_HEADER_NAME_FORMAT, 1)), 
                        new SimpleEntityPojo(String.format(DEFAULT_HEADER_ID_FORMAT, 2), String.format(DEFAULT_HEADER_NAME_FORMAT, 2)),
                        new SimpleEntityPojo(String.format(DEFAULT_HEADER_ID_FORMAT, 3), String.format(DEFAULT_HEADER_NAME_FORMAT, 3)), 
                        new SimpleEntityPojo(String.format(DEFAULT_HEADER_ID_FORMAT, 4), String.format(DEFAULT_HEADER_NAME_FORMAT, 4)),
                        new SimpleEntityPojo(String.format(DEFAULT_HEADER_ID_FORMAT, 5), String.format(DEFAULT_HEADER_NAME_FORMAT, 5)),
                        new SimpleEntityPojo(String.format(DEFAULT_HEADER_ID_FORMAT, 6), String.format(DEFAULT_HEADER_NAME_FORMAT, 6))}
                
               , 5) ;
        
        checkHeaders(headers, DEFAULT_HEADER_ID_FORMAT, DEFAULT_HEADER_NAME_FORMAT) ;
        
        headers = DataPojo.updateOrCreateHeaders(null, 5) ;
        
        checkHeaders(headers, DEFAULT_HEADER_ID_FORMAT, DEFAULT_HEADER_NAME_FORMAT) ;
    }
    
    private void checkHeaders(SimpleEntity[] headers, String headerIdFormat, String headerNameFormat) {
        
        assertNotNull("Header are null", headers) ;
        assertEquals("Header not correct size", 5, headers.length) ;
        
        for (int i = 0 ; i < headers.length; ++i) {
            if (headers[i] != null)  {
                assertEquals(String.format("header %d id is incorrect!", i+1), String.format(headerIdFormat, i+1), headers[i].getUniqueIdentifier()) ;
                assertEquals(String.format("header %d id is incorrect!", i+1), String.format(headerNameFormat, i+1), headers[i].getName()) ;
            } else {
               assertNull(String.format("header %d id should be null!", i+1), headers[i]) ;
            }
        }
    }

    @Test
    public void testUpdateOrCreateHeaders() {
        
        SimpleEntity[] headers = DataPojo.updateOrCreateHeaders(
                new SimpleEntity[] {
                        new SimpleEntityPojo(String.format(HEADER_ID_FORMAT, 1), String.format(HEADER_NAME_FORMAT, 1)), 
                        new SimpleEntityPojo(String.format(HEADER_ID_FORMAT, 2), String.format(HEADER_NAME_FORMAT, 2)),
                        new SimpleEntityPojo(String.format(HEADER_ID_FORMAT, 3), String.format(HEADER_NAME_FORMAT, 3)), 
                        new SimpleEntityPojo(String.format(HEADER_ID_FORMAT, 4), String.format(HEADER_NAME_FORMAT, 4)),
                        new SimpleEntityPojo(String.format(HEADER_ID_FORMAT, 5), String.format(HEADER_NAME_FORMAT, 5))}
                
               , 5, HEADER_ID_FORMAT, HEADER_NAME_FORMAT) ;
        
        checkHeaders(headers, HEADER_ID_FORMAT, HEADER_NAME_FORMAT) ;
        
        headers = DataPojo.updateOrCreateHeaders(
                new SimpleEntity[] {
                        new SimpleEntityPojo(String.format(HEADER_ID_FORMAT, 1), String.format(HEADER_NAME_FORMAT, 1)), 
                        new SimpleEntityPojo(String.format(HEADER_ID_FORMAT, 2), String.format(HEADER_NAME_FORMAT, 2)),
                        new SimpleEntityPojo(String.format(HEADER_ID_FORMAT, 3), String.format(HEADER_NAME_FORMAT, 3)), 
                        new SimpleEntityPojo(String.format(HEADER_ID_FORMAT, 4), String.format(HEADER_NAME_FORMAT, 4)) }
                
               , 5, HEADER_ID_FORMAT, HEADER_NAME_FORMAT) ;
        
        checkHeaders(headers, HEADER_ID_FORMAT, HEADER_NAME_FORMAT) ;
        
        headers = DataPojo.updateOrCreateHeaders(
                new SimpleEntity[] {
                        new SimpleEntityPojo(String.format(HEADER_ID_FORMAT, 1), String.format(HEADER_NAME_FORMAT, 1)), 
                        null,
                        new SimpleEntityPojo(String.format(HEADER_ID_FORMAT, 3), String.format(HEADER_NAME_FORMAT, 3)), 
                        new SimpleEntityPojo(String.format(HEADER_ID_FORMAT, 4), String.format(HEADER_NAME_FORMAT, 4)),
                        null }
                
               , 5, HEADER_ID_FORMAT, HEADER_NAME_FORMAT) ;
        
        checkHeaders(headers, HEADER_ID_FORMAT, HEADER_NAME_FORMAT) ;
        
        headers = DataPojo.updateOrCreateHeaders(
                new SimpleEntity[] {
                        new SimpleEntityPojo(String.format(HEADER_ID_FORMAT, 1), String.format(HEADER_NAME_FORMAT, 1)), 
                        new SimpleEntityPojo(String.format(HEADER_ID_FORMAT, 2), String.format(HEADER_NAME_FORMAT, 2)),
                        new SimpleEntityPojo(String.format(HEADER_ID_FORMAT, 3), String.format(HEADER_NAME_FORMAT, 3)), 
                        new SimpleEntityPojo(String.format(HEADER_ID_FORMAT, 4), String.format(HEADER_NAME_FORMAT, 4)),
                        new SimpleEntityPojo(String.format(HEADER_ID_FORMAT, 5), String.format(HEADER_NAME_FORMAT, 5)),
                        new SimpleEntityPojo(String.format(HEADER_ID_FORMAT, 6), String.format(HEADER_NAME_FORMAT, 6))}
                
               , 5, HEADER_ID_FORMAT, HEADER_NAME_FORMAT) ;
        
        checkHeaders(headers, HEADER_ID_FORMAT, HEADER_NAME_FORMAT) ;
        
        headers = DataPojo.updateOrCreateHeaders(null, 5, HEADER_ID_FORMAT, HEADER_NAME_FORMAT) ;
        
        checkHeaders(headers, HEADER_ID_FORMAT, HEADER_NAME_FORMAT) ;
        
    }

}
