
package tiralabratest.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.domain.Letter;

/**
 *
 * @author tamsi
 */
public class LetterTest {
    
    @Test
    public void creatingLetterWorks() {
        Letter l = new Letter((char) 97, (float) 0.01, 0);
        assertEquals(97, l.getChar());
        assertEquals(0.01, l.getFrequency(), 0.001);
    }

}
