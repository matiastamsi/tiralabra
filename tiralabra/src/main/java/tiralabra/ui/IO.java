
package tiralabra.ui;

/**
 * The interface that defines input/output methods.
 * 
 * @author tamsi
 */
public interface IO {
    /**
     * Reads next line or other words the input.
     * @return user's input
     */
    String nextLine();
    /**
     * Prints the output.
     * @param output the string that is wanted to be printed.
     */
    void print(String output);
    /**
     * Check wether there is a line next.
     * @return true or false
     */
    boolean hasNextLine();
    /**
     * Getter method for outputs.
     * 
     * @return array of strings
     */
    String[] getOutputs();
}
