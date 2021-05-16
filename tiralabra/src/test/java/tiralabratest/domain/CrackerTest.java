package tiralabratest.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.domain.Cracker;
import tiralabra.domain.Letter;
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
        assertEquals(0.08167, f[0], 0.000001);
        assertEquals(0.00772, f[10], 0.000001);
        assertEquals(0.05987, f[17], 0.000001);
        assertEquals(0.00074, f[25], 0.000001);
    }

    @Test
    public void givingCipherInitializesEverythingThatNeeded() {
        c.giveCipher("aaaaa bbbcc");
        c.order();
        c.createLetterArrays();
        c.setUpLetters();
        assertEquals(""
                + "### FREQUENCIES ### \n"
                + " [letter] / [f. in English] / [f. in the cipher]\n"
                + "a / 0.08167 / 0.5\n"
                + "b / 0.01492 / 0.3\n"
                + "c / 0.02782 / 0.2\n"
                + "d / 0.04253 / 0.0\n"
                + "e / 0.12702 / 0.0\n"
                + "f / 0.02228 / 0.0\n"
                + "g / 0.02015 / 0.0\n"
                + "h / 0.06094 / 0.0\n"
                + "i / 0.06966 / 0.0\n"
                + "j / 0.00153 / 0.0\n"
                + "k / 0.00772 / 0.0\n"
                + "l / 0.04025 / 0.0\n"
                + "m / 0.02406 / 0.0\n"
                + "n / 0.06749 / 0.0\n"
                + "o / 0.07507 / 0.0\n"
                + "p / 0.01929 / 0.0\n"
                + "q / 9.5E-4 / 0.0\n"
                + "r / 0.05987 / 0.0\n"
                + "s / 0.06327 / 0.0\n"
                + "t / 0.09056 / 0.0\n"
                + "u / 0.02758 / 0.0\n"
                + "v / 0.00978 / 0.0\n"
                + "w / 0.0236 / 0.0\n"
                + "x / 0.0015 / 0.0\n"
                + "y / 0.01974 / 0.0\n"
                + "z / 7.4E-4 / 0.0\n"
                + "\n"
                + "### ORDER TO REPLACE LETTER WITH ANOTHER ### \n"
                + " [letter] / [letter's queue (the best guesses)]\n"
                + "a / etaoinshrdlcumwfgypbvkjxqz\n"
                + "b / etaoinshrdlcumwfgypbvkjxqz\n"
                + "c / etaoinshrdlcumwfgypbvkjxqz\n"
                + "d / zqxjkvbpygfwmucldrhsnioate\n"
                + "e / zqxjkvbpygfwmucldrhsnioate\n"
                + "f / zqxjkvbpygfwmucldrhsnioate\n"
                + "g / zqxjkvbpygfwmucldrhsnioate\n"
                + "h / zqxjkvbpygfwmucldrhsnioate\n"
                + "i / zqxjkvbpygfwmucldrhsnioate\n"
                + "j / zqxjkvbpygfwmucldrhsnioate\n"
                + "k / zqxjkvbpygfwmucldrhsnioate\n"
                + "l / zqxjkvbpygfwmucldrhsnioate\n"
                + "m / zqxjkvbpygfwmucldrhsnioate\n"
                + "n / zqxjkvbpygfwmucldrhsnioate\n"
                + "o / zqxjkvbpygfwmucldrhsnioate\n"
                + "p / zqxjkvbpygfwmucldrhsnioate\n"
                + "q / zqxjkvbpygfwmucldrhsnioate\n"
                + "r / zqxjkvbpygfwmucldrhsnioate\n"
                + "s / zqxjkvbpygfwmucldrhsnioate\n"
                + "t / zqxjkvbpygfwmucldrhsnioate\n"
                + "u / zqxjkvbpygfwmucldrhsnioate\n"
                + "v / zqxjkvbpygfwmucldrhsnioate\n"
                + "w / zqxjkvbpygfwmucldrhsnioate\n"
                + "x / zqxjkvbpygfwmucldrhsnioate\n"
                + "y / zqxjkvbpygfwmucldrhsnioate\n"
                + "z / zqxjkvbpygfwmucldrhsnioate\n"
                + "", c.listData());
    }

    @Test
    public void creatingCopyOfLetterArrayWorks() {
        Letter[] letterArray = new Letter[1];
        letterArray[0] = new Letter((char) 97, (float) 1.0, 0);
        Letter[] copy = c.createCopy(letterArray);
        assertEquals(97, copy[0].getChar());
    }
}
