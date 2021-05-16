package tiralabra;

import tiralabra.domain.Cracker;
import tiralabra.domain.Trie;
import tiralabra.ui.ConsoleIO;
import tiralabra.ui.UI;

/**
 * The main class of the application.
 *
 * @author tamsi
 */
public class Main {

    /**
     * The main method.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Trie trie = new Trie();
        // Source of the words but I have added some to the file:
        // http://www.mieliestronk.com/corncob_lowercase.txt
        long start = System.currentTimeMillis();
        trie.createTrie("english_words_lowercase.txt");
        long timeToCreateTrie = System.currentTimeMillis() - start;
        Cracker cracker = new Cracker(trie);
        UI ui = new UI(new ConsoleIO(), cracker, timeToCreateTrie);
        ui.run();
    }
}
