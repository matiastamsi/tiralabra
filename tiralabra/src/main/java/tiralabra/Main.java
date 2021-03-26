
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
        trie.createTrie("english_words_lowercase.txt");
        trie.findWord("s");
    }
}
