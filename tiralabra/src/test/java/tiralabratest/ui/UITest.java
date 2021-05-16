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
            ui = new UI(new StubIO(new String[1]), new Cracker(new Trie()), 1);
        } catch (Exception e) {
            b = false;
        }
        assertTrue(b);
    }

    @Test
    public void generatingCipherWorks() {
        ui = new UI(new StubIO(new String[1]), new Cracker(new Trie()), 1);
        String s = ui.generate("abcdefghijklmnopqrstuvwxyz",
                "bcdefghijklmnopqrstuvwxyza", "this is an example");
        assertEquals("uijt jt bo fybnqmf", s);
    }

    @Test
    public void droppingUnwantedCharsWorks() {
        ui = new UI(new StubIO(new String[1]), new Cracker(new Trie()), 1);
        String s = ui.dropEverythingElseThanAlphabetsAndSpaces(""
                + "This sentence includes elements that we do not want!!!");
        assertEquals("this sentence includes elements that we do not want", s);
    }

    @Test
    public void runningUiWorks() {
        String[] inputs = new String[2];
        inputs[0] = "incorrect";
        inputs[1] = "exit";
        StubIO io = new StubIO(inputs);
        ui = new UI(io, new Cracker(new Trie()), 1);
        ui.run();
        assertEquals("Bye!", ui.io.getOutputs()[6]);
        assertEquals("Give a command from the following:"
                + "\nexit\ncrack\n", ui.io.getOutputs()[5]);
        assertEquals("The command incorrect not found\n",
                ui.io.getOutputs()[4]);
        assertEquals("Give a command from the following:"
                + "\nexit\ncrack\n", ui.io.getOutputs()[3]);
    }

    @Test
    public void crackingWorksWhenGenerating() {
        String[] inputs = new String[5];
        inputs[0] = "crack";
        inputs[1] = "g";
        inputs[2] = "bcdefghijklmnopqrstuvwxyza";
        inputs[3] = "a";
        inputs[4] = "exit";
        StubIO io = new StubIO(inputs);
        ui = new UI(io, new Cracker(new Trie()), 1);
        ui.run();
        assertEquals("Bye!", ui.io.getOutputs()[15]);
        assertEquals("Give a command from the following:"
                + "\nexit\ncrack\n", ui.io.getOutputs()[14]);
        assertTrue(ui.io.getOutputs()[13].contains("e"));
        assertEquals("### FREQUENCIES ### \n"
                + " [letter] / [f. in English] / [f. in the cipher]\n"
                + "a / 0.08167 / 0.0\n"
                + "b / 0.01492 / 1.0\n"
                + "c / 0.02782 / 0.0\n"
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
                + "a / zqxjkvbpygfwmucldrhsnioate\n"
                + "b / etaoinshrdlcumwfgypbvkjxqz\n"
                + "c / zqxjkvbpygfwmucldrhsnioate\n"
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
                + "z / zqxjkvbpygfwmucldrhsnioate\n", ui.io.getOutputs()[11]);
        assertEquals("The cipher is:\nb\n", ui.io.getOutputs()[10]);
        assertEquals("\nGive something to be changed:\n", ui.io.getOutputs()[9]);
        assertEquals("\nGive alphabets:\n"
                + "abcdefghijklmnopqrstuvwxyz\n", ui.io.getOutputs()[8]);
        assertEquals("\nLet's do some cracking!\n"
                + "If you want to generate a cipher by first giving the \n"
                + "manipulated alphabets, then press 'g'.\n"
                + "If you want to give the cipher straight,\n"
                + "then press anything!\n", ui.io.getOutputs()[7]);
        assertEquals("Give a command from the following:"
                + "\nexit\ncrack\n", ui.io.getOutputs()[6]);
    }

    @Test
    public void crackingWorksWhenGivingCipherStraight() {
        String[] inputs = new String[5];
        inputs[0] = "crack";
        inputs[1] = "m";
        inputs[2] = "m";
        inputs[3] = "exit";
        StubIO io = new StubIO(inputs);
        ui = new UI(io, new Cracker(new Trie()), 1);
        ui.run();
        assertEquals("Bye!", ui.io.getOutputs()[13]);
        assertEquals("Give a command from the following:"
                + "\nexit\ncrack\n", ui.io.getOutputs()[12]);
        assertTrue(ui.io.getOutputs()[11].contains("e"));
        assertEquals("### FREQUENCIES ### \n"
                + " [letter] / [f. in English] / [f. in the cipher]\n"
                + "a / 0.08167 / 0.0\n"
                + "b / 0.01492 / 0.0\n"
                + "c / 0.02782 / 0.0\n"
                + "d / 0.04253 / 0.0\n"
                + "e / 0.12702 / 0.0\n"
                + "f / 0.02228 / 0.0\n"
                + "g / 0.02015 / 0.0\n"
                + "h / 0.06094 / 0.0\n"
                + "i / 0.06966 / 0.0\n"
                + "j / 0.00153 / 0.0\n"
                + "k / 0.00772 / 0.0\n"
                + "l / 0.04025 / 0.0\n"
                + "m / 0.02406 / 1.0\n"
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
                + "a / zqxjkvbpygfwmucldrhsnioate\n"
                + "b / zqxjkvbpygfwmucldrhsnioate\n"
                + "c / zqxjkvbpygfwmucldrhsnioate\n"
                + "d / zqxjkvbpygfwmucldrhsnioate\n"
                + "e / zqxjkvbpygfwmucldrhsnioate\n"
                + "f / zqxjkvbpygfwmucldrhsnioate\n"
                + "g / zqxjkvbpygfwmucldrhsnioate\n"
                + "h / zqxjkvbpygfwmucldrhsnioate\n"
                + "i / zqxjkvbpygfwmucldrhsnioate\n"
                + "j / zqxjkvbpygfwmucldrhsnioate\n"
                + "k / zqxjkvbpygfwmucldrhsnioate\n"
                + "l / zqxjkvbpygfwmucldrhsnioate\n"
                + "m / etaoinshrdlcumwfgypbvkjxqz\n"
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
                + "z / zqxjkvbpygfwmucldrhsnioate\n", ui.io.getOutputs()[9]);
        assertEquals("\nGive a cipher:\n", ui.io.getOutputs()[8]);
        assertEquals("\nLet's do some cracking!\n"
                + "If you want to generate a cipher by first giving the \n"
                + "manipulated alphabets, then press 'g'.\n"
                + "If you want to give the cipher straight,\n"
                + "then press anything!\n", ui.io.getOutputs()[7]);
        assertEquals("Give a command from the following:"
                + "\nexit\ncrack\n", ui.io.getOutputs()[6]);
    }
}
