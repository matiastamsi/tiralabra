/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabratest.ui;

import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.domain.Cracker;
import tiralabra.domain.Trie;
import tiralabra.ui.StubIO;
import tiralabra.ui.UI;

/**
 * Tests the user interface.
 *
 * @author tamsi
 */
public class UITest {

    UI ui;

    @Test
    public void settingUpUIWorks() {
        boolean b = true;
        try {
            ui = new UI(new StubIO(new String[1]), new Cracker(new Trie()));
        } catch (Exception e) {
            b = false;
        }
        assertTrue(b);
    }

}
