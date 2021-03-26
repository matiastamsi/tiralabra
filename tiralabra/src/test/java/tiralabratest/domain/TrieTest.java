
package tiralabratest.domain;

import org.junit.Test;
import static org.junit.Assert.*;
import tiralabra.domain.Trie;

/**
 *
 * @author tamsi
 */
public class TrieTest {

    Trie trie = new Trie();

    public TrieTest() {
        trie.createTrie("english_words_lowercase.txt");
    }

    @Test
    public void creatingTrieWithRootsWorks() {
        assertEquals(26, trie.getRoots().length);
    }

    @Test
    public void creatingTrieBasedOnFileOfWordsWorks() {
        boolean noException = true;
        try {
            trie.createTrie("english_words_lowercase.txt");
        } catch (Exception e) {
            noException = false;
        }
        assertEquals(true, noException);
    }

    @Test
    public void findingExistingEnglishWordWorks() {
        assertEquals(true, trie.findWord("bulldog"));
        assertEquals(true, trie.findWord("bull"));
        assertEquals(true, trie.findWord("cardiologist"));
        assertEquals(true, trie.findWord("unapproachable"));
        assertEquals(true, trie.findWord("a"));
    }

    @Test
    public void unrealWordNotFound() {
        assertEquals(false, trie.findWord("bulkhjsdhkjf"));
        assertEquals(false, trie.findWord("s"));
        assertEquals(false, trie.findWord("öword"));
        assertEquals(false, trie.findWord("bulldogg"));
    }

}
