package uno.informatics.data.tests.pojo;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import org.junit.Test;

import uno.informatics.data.pojo.SimpleEntityPojo;

public class SimpleEntityPojoTest {

    @Test
    public void testSimpleEntityPojoString() {
        SimpleEntityPojo pojo = new SimpleEntityPojo("name") ;
        
        assertEquals("Id not correct!", "name", pojo.getUniqueIdentifier()) ;
        assertEquals("Name not correct!", "name", pojo.getName()) ;
    }

    @Test
    public void testSimpleEntityPojoStringString() {
        SimpleEntityPojo pojo = new SimpleEntityPojo("id", "name") ;
        
        assertEquals("Id not correct!", "id", pojo.getUniqueIdentifier()) ;
        assertEquals("Name not correct!", "name", pojo.getName()) ;
    }

    @Test
    public void testSimpleEntityPojoSimpleEntity() {
       SimpleEntityPojo pojo = new SimpleEntityPojo(new SimpleEntityPojo("id", "name")) ;
        
        assertEquals("Id not correct!", "id", pojo.getUniqueIdentifier()) ;
        assertEquals("Name not correct!", "name", pojo.getName()) ;
    }

    @Test
    public void testSetUniqueIdentifier() {
        
        SimpleEntityPojo pojo = new SimpleEntityPojo("id", "name") ;
        
        pojo.setUniqueIdentifier("id2");
        
        assertEquals("Id not correct!", "id2", pojo.getUniqueIdentifier()) ;
        assertEquals("Name not correct!", "name", pojo.getName()) ;
    }

    @Test
    public void testSetName() {
        
        SimpleEntityPojo pojo = new SimpleEntityPojo("id", "name") ;
        
        pojo.setName("name2");
        
        assertEquals("Id not correct!", "id", pojo.getUniqueIdentifier()) ;
        assertEquals("Name not correct!", "name2", pojo.getName()) ;
    }
    
    @Test
    public void testSetNameAfterSerialization() {
        
        SimpleEntityPojo pojo = new SimpleEntityPojo("id", "name") ;
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] bytes  = null ;
        
        ObjectOutput out = null;
        try {
          out = new ObjectOutputStream(bos);   
          out.writeObject(pojo);
          out.flush();
          bytes = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage()) ;
        } finally {
          try {
            bos.close();
          } catch (IOException ex) {
            // ignore close exception
          }
        }

        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        ObjectInput in = null;
        try {
          in = new ObjectInputStream(bis);
          pojo = (SimpleEntityPojo)in.readObject(); 

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            fail(e.getLocalizedMessage()) ;
        } finally {
          try {
            if (in != null) {
              in.close();
            }
          } catch (IOException ex) {
            // ignore close exception
          }
        }
        
        assertEquals("Id not correct!", "id", pojo.getUniqueIdentifier()) ;
        assertEquals("Name not correct!", "name", pojo.getName()) ;
        
        // checks to see if property change support was intiliased 
        pojo.setName("name2");
        
        assertEquals("Id not correct!", "id", pojo.getUniqueIdentifier()) ;
        assertEquals("Name not correct!", "name2", pojo.getName()) ;
    }
}
