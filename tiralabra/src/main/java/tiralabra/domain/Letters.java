package tiralabra.domain;

/**
 * An object that could be replaced with just the array of Letter objects.
 * However, to make the cracking faster, this object saves the permutation so
 * that it isn't necessary to be created every time when wanted to know.
 *
 * @author tamsi
 */
public class Letters {

    private final Letter[] letters;
    private final String permutation;

    /**
     * Letters is made of array of Letter objects and permutation that is the
     * string if each Letter would give next best guess as a char.
     *
     * @param l letterArray
     * @param p permutation
     */
    public Letters(Letter[] l, String p) {
        this.letters = l;
        this.permutation = p;
    }

    /**
     * Getter method to return the array.
     *
     * @return array of Letter objects.
     */
    public Letter[] getLetters() {
        return this.letters;
    }

    /**
     * Getter method to return the permutation that is been made of each
     * Letter's next best guess.
     *
     * @return permutation that is a string
     */
    public String getPermutation() {
        return this.permutation;
    }
}
