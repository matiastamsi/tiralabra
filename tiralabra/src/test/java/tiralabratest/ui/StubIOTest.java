package tiralabratest.ui;

import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.ui.StubIO;

/**
 * The class that tests how the IO functions. There is an interface called IO
 * and StubIO implements it.
 *
 * @author tamsi
 */
public class StubIOTest {

    StubIO s;

    @Test
    public void nextLineWorksCorrectly() {
        String[] inputs = new String[2];
        inputs[0] = "first";
        inputs[1] = "second";
        s = new StubIO(inputs);
        assertEquals("first", s.nextLine());
        assertEquals("second", s.nextLine());
        assertEquals("", s.nextLine());
    }

    @Test
    public void nextLineWorksWhenNoInputs() {
        String[] inputs = new String[1];
        s = new StubIO(inputs);
        assertEquals(null, s.nextLine());
    }

    @Test
    public void printingWorks() {
        String[] outputs = new String[0];
        s = new StubIO(outputs);
        s.print("print");
        assertEquals("print", s.getOutputs()[0]);
        s.print("print again");
        assertEquals("print again", s.getOutputs()[1]);
    }

    @Test
    public void hasNextLineWorks() {
        String[] inputs = new String[2];
        inputs[0] = "first";
        inputs[1] = "second";
        s = new StubIO(inputs);
        assertEquals("first", s.nextLine());
        assertTrue(s.hasNextLine());
        assertEquals("second", s.nextLine());
        assertFalse(s.hasNextLine());
    }
}
