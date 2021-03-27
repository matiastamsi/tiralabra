
package tiralabratest.domain;

import tiralabra.domain.TrieNode;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test TrieNode object.
 * 
 * @author tamsi
 */
public class TrieNodeTest {
    
    TrieNode node;
    
    @Test
    public void creatingTrieNodeSuccess() {
        node = new TrieNode('a');
        assertEquals('a', node.getNode());
    }
    
    @Test
    public void settingParentWorks() {
        node = new TrieNode('d');
        node.setParent(new TrieNode('b'));
        assertEquals('b', node.getParent().getNode());
    }
    
    @Test
    public void settingChildWorks() {
         node = new TrieNode('s');
         node.setChild(new TrieNode('e'));
         node.setChild(new TrieNode('o'));
         assertEquals('e', node.getChildren()[0].getNode());
         assertEquals('o', node.getChildren()[1].getNode());
    }
}
