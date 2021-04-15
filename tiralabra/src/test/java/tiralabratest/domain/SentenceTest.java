package tiralabratest.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.domain.Sentence;

/**
 * The class that tests the Sentence object.
 *
 * @author tamsi
 */
public class SentenceTest {

    @Test
    public void sentenceAsStringIsCorrect() {
        String[] pieces = new String[1];
        pieces[0] = "example";
        Sentence s = new Sentence(pieces);
        assertEquals("example ", s.toString());
        pieces = new String[2];
        pieces[0] = "two";
        pieces[1] = "parts";
        s = new Sentence(pieces);
        assertEquals("two parts ", s.toString());
    }

    @Test
    public void gettingPiecesOutWorks() {
        String[] pieces = new String[1];
        pieces[0] = "get";
        Sentence s = new Sentence(pieces);
        assertEquals(pieces.length, s.getPieces().length);
        assertEquals("get", s.getPieces()[0]);
    }

    @Test
    public void replacingLettersWorksIfLetterWontExist() {
        String[] pieces = new String[4];
        pieces[0] = "change";
        pieces[1] = "is";
        pieces[2] = "always";
        pieces[3] = "good";
        Sentence s = new Sentence(pieces);
        assertEquals("change is always good ", s.toString());
        s.replace("a".charAt(0), "b".charAt(0));
        assertEquals("chbnge is blwbys good ", s.toString());
    }

    @Test
    public void replacingLettersSkipsIfLetterExists() {
        String[] pieces = new String[4];
        pieces[0] = "change";
        pieces[1] = "is";
        pieces[2] = "always";
        pieces[3] = "good";
        Sentence s = new Sentence(pieces);
        assertEquals("change is always good ", s.toString());
        s.replace("a".charAt(0), "c".charAt(0));
        assertEquals("change is always good ", s.toString());
    }
}
