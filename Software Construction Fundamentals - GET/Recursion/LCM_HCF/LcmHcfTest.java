import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class LcmHcfTest {

    LcmHcfRecursion LcmHcm = new LcmHcfRecursion();

    @Test
    public void Test(){
        int n1= 5;
        int n2=90;
        assertEquals(90, LcmHcm.lcm(n1, n2));
        assertEquals(5, LcmHcm.hcf(n1, n2));


        int n3=80 ;
        int n4=50;
        assertEquals(400, LcmHcm.lcm(n3, n4));
        assertEquals(10, LcmHcm.hcf(n3, n4));


        int n5=65;
        int n6=34;
        assertEquals(2210, LcmHcm.lcm(n5, n6));
        assertEquals(1, LcmHcm.hcf(n5, n6));


  
    }
}