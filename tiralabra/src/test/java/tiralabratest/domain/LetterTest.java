package tiralabratest.domain;

import java.nio.file.Paths;
import java.util.Scanner;
import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.domain.Letter;

/**
 * Test class for Letter object.
 *
 * @author tamsi
 */
public class LetterTest {

    Letter l;

    @Test
    public void creatingLetterWorks() {
        l = new Letter((char) 97, (float) 0.01, 0);
        assertEquals(97, l.getChar());
        assertEquals(0.01, l.getFrequency(), 0.001);
        assertEquals(0, l.getIndexInAlphabets());
    }

    @Test
    public void creatingQueueWorks() {
        l = new Letter((char) 97, (float) 0.00, 0);
        float[] array = new float[26];
        for (int i = 0; i < array.length; i++) {
            array[i] = (float) 0.01 * i;
        }
        l.setUpQueue(array);
        assertEquals("abcdefghijklmnopqrstuvwxyz", l.getQueue());
    }

    @Test
    public void queueFunctionsWorkCorrectly() {
        float[] array = new float[26];
        int i = 0;
        try (Scanner scanner = new Scanner(Paths.get("frequencies.txt"))) {
            while (scanner.hasNextFloat()) {
                float f = scanner.nextFloat();
                array[i] = f;
                i++;
            }
        } catch (Exception e) {
            System.out.println("Error while reading the file: "
                    + e.getMessage());
        }
        l = new Letter((char) 100, (float) 0.3, 3);
        l.setUpQueue(array);
        assertEquals("etaoinshrdlucwmfygpbvkxjqz", l.getQueue());
        l = new Letter((char) 100, (float) 0.00, 3);
        l.setUpQueue(array);
        assertEquals("zqxjkvbpygfwmucldrhsnioate", l.getQueue());
        assertEquals("z".toCharArray()[0], l.next());
        l.increasePointer();
        assertEquals("q".toCharArray()[0], l.next());
        l.decreasePointer();
        assertEquals("z".toCharArray()[0], l.next());
    }
    
    @Test
    public void settingQueueAndPointerWorks() {
        l = new Letter((char) 97, (float) 0.5, 0);
        l.setQueue("works", 3);
        assertEquals(l.getQueue(), "works");
        assertEquals(l.getPointer(), 3);
    }

}
