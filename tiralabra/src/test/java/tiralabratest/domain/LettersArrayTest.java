package tiralabratest.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.domain.Letter;
import tiralabra.domain.Letters;
import tiralabra.domain.LettersArray;

/**
 * Test LettersArray object.
 *
 * @author tamsi
 */
public class LettersArrayTest {

    LettersArray lA;

    @Test
    public void creatingLettersArrayWorks() {
        lA = new LettersArray(14);
        assertEquals(14, lA.getLettersAsArray().length);
    }

    @Test
    public void addingNewLettersWorks() {
        lA = new LettersArray(1);
        lA.addLetters(new Letters(new Letter[1], "w"));
        assertEquals(lA.getLettersAsArray()[0].getPermutation(), "w");
    }

    @Test
    public void compressingWorks() {
        lA = new LettersArray(2);
        lA.addLetters(new Letters(new Letter[1], "x"));
        lA.compress();
        assertEquals(1, lA.getLettersAsArray().length);
    }

    @Test
    public void checkingIfPermutationExistsWorks() {
        lA = new LettersArray(5);
        lA.addLetters(new Letters(new Letter[1], "exists"));
        assertTrue(lA.permutationExists("exists"));
        assertFalse(lA.permutationExists("no"));
    }

}
