
package tiralabra;

import tiralabra.domain.Trie;

/**
 * The main class of the application.
 * 
 * @author tamsi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Trie trie = new Trie();
        // Source of the words http://www.mieliestronk.com/corncob_lowercase.txt
        trie.createTrie("english_words_lowercase.txt");
    }
}
