package tiralabra.domain;

import java.nio.file.Paths;
import java.util.Scanner;

/**
 * This object is a data structure which can represent words. The main structure
 * is that there is every alphabet from English alphabets as a root. Then these
 * nodes can have children and so on. At the end, when trying to search a word
 * from this "dictionary" it can be confirmed by going from a parent node to a
 * child node.
 *
 * @author tamsi
 */
public class Trie {

    private final TrieNode[] roots;
    private final char[] alphabets;

    /**
     * The trie contains 26 TrieNodes. This constructor creates first TrieNodes
     * that can form an English word (all alphabets).
     */
    public Trie() {
        this.roots = new TrieNode[26];
        this.alphabets = new char[26];
        int a = 97; // The ACSII code for a is 97.
        // Fill the array 'roots' using ACSII codes from a-z (97-122).
        for (int i = 0; i < 26; i++) {
            this.roots[i] = new TrieNode((char) a);
            this.alphabets[i] = (char) a;
            a++;
        }
    }

    /**
     * A method that creates a trie based on a file that contains words. See
     * that to function correctly, the file must contain words on a separate
     * lines.
     *
     * @param path is a location of the file
     */
    public void createTrie(String path) {
        try (Scanner scanner = new Scanner(Paths.get(path))) {
            while (scanner.hasNextLine()) {
                String word = scanner.nextLine();
                addWord(word);
            }
        } catch (Exception e) {
            System.out.println("Error while reading the file: "
                    + e.getMessage());
        }
    }

    /**
     * A method that adds a word to the trie.
     *
     * @param word is a string from the file.
     */
    private void addWord(String word) {
        TrieNode node = findFirstNode(word.charAt(0));
        int i = 1;
        while (i < word.length()) {
            boolean goToNext = false;
            char c = word.charAt(i);
            for (TrieNode child : node.getChildren()) {
                if (child.getNode() == c) {
                    node = child;
                    goToNext = true;
                    break;
                }
            }
            if (!goToNext) {
                TrieNode child = new TrieNode(c);
                child.setParent(node);
                node.setChild(child);
                node = child;
            }
            if (i == word.length() - 1) {
                node.setToBeEndOfWord();
            }
            i++;
        }
    }

    /**
     * Tell if word is in a trie.
     *
     * @param word a string that is been questioned
     * @return Boolean value wether the word is in a trie.
     */
    public boolean findWord(String word) {
        char f = word.charAt(0);
        TrieNode node = findFirstNode(f);
        if (node == null) {
            return false;
        }
        int i = 1;
        int length = word.length();
        while (i < length) {
            boolean found = false;
            for (TrieNode child : node.getChildren()) {
                if (child.getNode() == word.charAt(i)) {
                    node = child;
                    found = true;
                    break;
                }
            }
            if (!found || (i == length - 1 && !node.isEndOfWord())) {
                return false;
            }
            i++;
        }
        return true;
    }

    /**
     * Helper method to do the searching of the node that is the first letter.
     *
     * @param c char that is the first letter.
     * @return a TrieNode
     */
    private TrieNode findFirstNode(Character c) {
        for (TrieNode node : this.roots) {
            if (node.getNode() == c) {
                return node;
            }
        }
        return null;
    }

    /**
     * Getter method to return the array. Mainly for testing purposes.
     *
     * @return the array of roots (first letters)
     */
    public TrieNode[] getRoots() {
        return this.roots;
    }

    /**
     * Getter method for alphabets.
     *
     * @return array of letters that form the English alphabets.
     */
    public char[] getAlphabets() {
        return this.alphabets;
    }
}
