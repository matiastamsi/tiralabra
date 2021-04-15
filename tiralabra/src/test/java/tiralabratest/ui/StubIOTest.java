
package tiralabratest.ui;

import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.ui.StubIO;

/**
 * The class that tests how the IO functions. There is
 * an interface called IO and StubIO implements it.
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
    }
    
    @Test
    public void nextLineWorksWhenNoInputs() {
        String[] inputs = new String[1];
        s = new StubIO(inputs);
        assertEquals(null, s.nextLine());
    }

    @Test
    public void printingWorks() {
        String[] inputs = new String[1];
        s = new StubIO(inputs);
        assertTrue(s.getOutputs()[0] == null);
        s.print("print");
        assertEquals("print", s.getOutputs()[s.getOutputs().length - 1]);
    }
}

