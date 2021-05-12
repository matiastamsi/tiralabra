
package tiralabratest.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.domain.Letter;
import tiralabra.domain.Letters;

/**
 * Test Letters object
 * 
 * @author tamsi
 */
public class LettersTest {
    Letters letters;

    @Test
    public void LettersWorksAsIntended() {
        letters = new Letters(new Letter[1], "x");
        assertEquals(1, letters.getLetters().length);
        assertEquals("x", letters.getPermutation());
    }
}
