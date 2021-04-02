
package tiralabra.ui;

/**
 * The IO class that is been used for testing purposes.
 * @author tamsi
 */
public class StubIO implements IO {
    private int pointer;
    private final String[] inputs;
    private String[] outputs;
    
    /**
     * Constructor initializes input and output arrays and the pointer.
     * @param inputs array of strings that are test inputs
     */
    public StubIO(final String[] inputs) {
        this.inputs = inputs;
        this.pointer = 0;
        this.outputs = new String[inputs.length];
    }
    
    /**
     * Go through the array as it was a user giving inputs.
     * @return String that is an 'input'
     */
    @Override
    public String nextLine() {
        if (this.pointer < inputs.length) {
            String line = this.inputs[pointer];
            this.pointer++;
            return line;
        }
        return "";
    }
    
    /**
     * Add an output to the array (create also larger array for that)
     * @param output is a String that is added to outputs.
     */
    @Override
    public void print(final String output) {
        String[] newArray = new String[this.outputs.length + 1];
        for (int i = 0; i < newArray.length - 2; i++) {
            newArray[i] = this.outputs[i];
        }
        newArray[newArray.length - 1] = output;
        this.outputs = newArray;
    }
    
    @Override
    public boolean hasNextLine() {
        return pointer < inputs.length;
    }
    
    /**
     * Getter method to get outputs
     * @return array of outputs as strings
     */
    public String[] getOutputs() {
        return this.outputs;
    }
}
