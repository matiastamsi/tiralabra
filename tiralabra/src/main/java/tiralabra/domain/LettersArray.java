package tiralabra.domain;

/**
 * An array that is been used for saving Letters objects.
 *
 * @author tamsi
 */
public class LettersArray {

    private Letters[] array;
    private int pointer;

    /**
     * LettersArray has a structure of an array where is Letter objects.
     *
     * @param length of the array
     */
    public LettersArray(int length) {
        this.array = new Letters[length];
        this.pointer = 0;
    }

    /**
     * Getter method for the array
     *
     * @return
     */
    public Letters[] getLettersAsArray() {
        return this.array;
    }

    /**
     * A method that adds Letters object to the array. The pointer keeps track
     * of the index that the new object is been put.
     *
     * @param l Letters object
     */
    public void addLetters(Letters l) {
        this.array[pointer] = l;
        this.pointer++;
    }

    /**
     * Remove empty space in the array by creating a smaller one. Otherwise the
     * empty space would increase exponentially.
     */
    public void compress() {
        int empty = this.array.length - pointer;
        Letters[] smaller = new Letters[this.array.length - empty];
        for (int i = 0; i < smaller.length; i++) {
            smaller[i] = this.array[i];
        }
        this.array = smaller;
    }

    /**
     * The method that goes through the array to seek if there is already a
     * Letters object that forms same permutation. This is how to terminate all
     * the same branches.
     *
     * @param p permutation
     * @return Boolean value
     */
    public boolean permutationExists(String p) {
        for (Letters letters : this.array) {
            if (letters != null) {
                if (letters.getPermutation().equals(p)) {
                    return true;
                }
            }
        }
        return false;
    }
}
