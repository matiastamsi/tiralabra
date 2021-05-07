package tiralabratest.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.domain.Cracker;
import tiralabra.domain.Trie;

/**
 * The class that tests the Cracker object.
 *
 * @author tamsi
 */
public class CrackerTest {

    Cracker c;

    public CrackerTest() {
        c = new Cracker(new Trie());
    }

    @Test
    public void loadingEnglishFrequenciesFromFileWorks() {
        float[] f = c.loadFrequenciesInEnglish("frequencies.txt");
        assertEquals(0.082, f[0], 0.0001);
        assertEquals(0.0077, f[10], 0.00001);
        assertEquals(0.06, f[17], 0.001);
        assertEquals(0.00074, f[25], 0.000001);
    }

    @Test
    public void givingCipherInitializesEverythingThatNeeded() {
        c.giveCipher("aaaaa bbbcc");
        assertEquals("aaaaa bbbcc", c.cipher);
        assertEquals(""
                + "[letter] / [freq. in English] / [freq. in the cipher]\n"
                + "a / 0.082 / 0.5\n"
                + "b / 0.015 / 0.3\n"
                + "c / 0.028 / 0.2\n"
                + "d / 0.043 / 0.0\n"
                + "e / 0.13 / 0.0\n"
                + "f / 0.022 / 0.0\n"
                + "g / 0.02 / 0.0\n"
                + "h / 0.061 / 0.0\n"
                + "i / 0.07 / 0.0\n"
                + "j / 0.0015 / 0.0\n"
                + "k / 0.0077 / 0.0\n"
                + "l / 0.04 / 0.0\n"
                + "m / 0.024 / 0.0\n"
                + "n / 0.067 / 0.0\n"
                + "o / 0.075 / 0.0\n"
                + "p / 0.019 / 0.0\n"
                + "q / 9.5E-4 / 0.0\n"
                + "r / 0.06 / 0.0\n"
                + "s / 0.063 / 0.0\n"
                + "t / 0.091 / 0.0\n"
                + "u / 0.028 / 0.0\n"
                + "v / 0.0098 / 0.0\n"
                + "w / 0.024 / 0.0\n"
                + "x / 0.0015 / 0.0\n"
                + "y / 0.02 / 0.0\n"
                + "z / 7.4E-4 / 0.0\n"
                + "", c.listFrequencies());
    }

}
