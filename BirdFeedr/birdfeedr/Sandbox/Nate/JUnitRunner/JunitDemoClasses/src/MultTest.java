import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;

public class MultTest {
    
    Multiplier multiplier;
    
    @Before
    public void setUp(){
        multiplier = new Multiplier();
    }
    
    @Test
    public void testAdd(){
        assertEquals("mult(2, 3) = 6?", multiplier.mult(2, 3), 6);
    }
}
