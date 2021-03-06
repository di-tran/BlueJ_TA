 

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Before;

/**
 * Placeholder class for the actaul text exersizeclass to be swapped with
 */
public class TestClass {
   
    UserClass userClass;
    
    @Before
    public void setUp() {
        userClass = new UserClass();
    }
    
    @Test
    public void testA() {
        assertEquals("add(1, 2) = 3?", userClass.add(1,2), 3);
    }
    
    @Test
    public void testB() {
        assertEquals("add(1, 2) = 3?", userClass.add(1,2), 2);
    }
    
    @Test
    public void testC() {
        assertTrue("add(1, 2) = 3?", userClass.add(1,2) == 1);
    }
}
