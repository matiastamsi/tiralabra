
package tiralabra.domain;

/**
 * An object that represents a node of
 * the Trie object. It is simply a node
 * that has a char value (alphabet) and information about
 * its parent and child/children if has any.
 *
 * @author tamsi
 */
public class TrieNode {

    private char alphabet;
    private TrieNode parent;
    private TrieNode[] children;
    
    /**
     * As the object is part of a word, it has a char value,
     * a parent (if not the first letter) and child/children
     * (if not the last letter).
     * 
     * @param alphabet is a char that is given for the node.
     */
    public TrieNode(char alphabet) {
        this.alphabet = alphabet;
        this.parent = null;
        this.children = new TrieNode[0];
    }

    /**
     * Set a parent that is a letter before this one.
     * @param parent is a TrieNode
     */
    public void setParent(TrieNode parent) {
        this.parent = parent;
    }

    /**
     * Set a child to the array of nodes children.
     * 
     * @param child is a TrieNode
     */
    public void setChild(TrieNode child) {
        int length = this.children.length;
        TrieNode[] newArray = new TrieNode[length + 1];
        for (int i = 0; i < length; i++) {
            newArray[i] = this.children[i];
        }
        newArray[length] = child;
        this.children = newArray;
    }

    /**
     * A getter method that returns the char value.
     * 
     * @return the alphabet that is a char.
     */
    public char getNode() {
        return this.alphabet;
    }

    /**
     * A getter method to return the parent.
     * @return a parent node
     */
    public TrieNode getParent() {
        return this.parent;
    }

    /**
     * A getter method to return children nodes.
     * @return a array of TrieNodes
     */
    public TrieNode[] getChildren() {
        return this.children;
    }
}
