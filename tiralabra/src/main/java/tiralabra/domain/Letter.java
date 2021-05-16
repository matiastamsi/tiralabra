package tiralabra.domain;

/**
 * An object that represents a letter (character) in the cipher.
 *
 * @author tamsi
 */
public class Letter {

    private final char c;
    private final float frequency;
    private final int indexInAlphabets;
    private final String alphabets;
    private String queue;
    private int pointer;

    /**
     * A letter has not just a char value but the frequency in the cipher, index
     * in the alphabets (a-z), knowledge of other letter char values, a queue
     * for next best guesses based on the similarity of frequencies and a
     * pointer to move in this queue.
     *
     * @param c char
     * @param f frequency in the cipher
     * @param i index int the alphabets
     */
    public Letter(char c, float f, int i) {
        this.c = c;
        this.frequency = f;
        this.indexInAlphabets = i;
        this.alphabets = "abcdefghijklmnopqrstuvwxyz";
        this.queue = "";
        this.pointer = 0;
    }

    /**
     * Calculate the order for queue. The queue has all the letters from the
     * alphabets but in the order of smallest difference between this Letter's
     * frequency and a letter (char) in English. So, the chars are ordered in
     * increasing order based on the difference. In the ideal case the first one
     * in the queue is correct substitution (in this case frequency in cipher
     * matches to the frequency in English).
     *
     * @param frequenciesInEnglish
     */
    public void setUpQueue(float[] frequenciesInEnglish) {
        int index = 0;
        while (index < alphabets.length()) {
            float smallestDifference = 100;
            char charWithSmallestDifference = 0;
            for (int i = 0; i < alphabets.length(); i++) {
                if (Math.abs(this.frequency - frequenciesInEnglish[i])
                        <= smallestDifference) {
                    boolean alreadyAdded = false;
                    for (char q : this.queue.toCharArray()) {
                        if (q == alphabets.charAt(i)) {
                            alreadyAdded = true;
                            break;
                        }
                    }
                    if (!alreadyAdded) {
                        smallestDifference = Math.abs(
                                this.frequency - frequenciesInEnglish[i]);
                        charWithSmallestDifference = alphabets.charAt(i);
                    }
                }
            }
            this.queue += charWithSmallestDifference;
            index++;
        }
    }

    /**
     * Tells what is next best guess.
     *
     * @return char from the queue at index where pointer points.
     */
    public char next() {
        return this.queue.charAt(this.pointer);
    }

    /**
     * Method to increase the pointer.
     */
    public void increasePointer() {
        this.pointer++;
    }

    /**
     * Method to decrease the pointer.
     */
    public void decreasePointer() {
        this.pointer--;
    }

    /**
     * The setter method for queue. When copying Letter it is better to set
     * queue instead of counting it again. Also, the state of the pointer is
     * required (to tell the state of the process / moving in the queue).
     *
     * @param q queue
     * @param p pointer
     */
    public void setQueue(String q, int p) {
        this.queue = q;
        this.pointer = p;
    }

    /**
     * The getter method for the frequency in the cipher.
     *
     * @return float
     */
    public float getFrequency() {
        return this.frequency;
    }

    /**
     * The getter method for the char value of the letter.
     *
     * @return character
     */
    public char getChar() {
        return this.c;
    }

    /**
     * The getter for the pointer
     *
     * @return integer value of the pointer
     */
    public int getPointer() {
        return this.pointer;
    }

    /**
     * The getter for the queue.
     *
     * @return queue as a string (chars in the specified order)
     */
    public String getQueue() {
        return this.queue;
    }

    /**
     * The getter for the index in the alphabets (a-z)
     *
     * @return index as a integer
     */
    public int getIndexInAlphabets() {
        return this.indexInAlphabets;
    }
}
