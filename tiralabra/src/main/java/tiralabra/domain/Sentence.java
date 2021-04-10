package tiralabra.domain;

/**
 * Object that
 *
 * @author tamsi
 */
public class Sentence {

    private Piece[] pieces;
    private char[][] equivalentLetters;
    private int countOfCorrectOnes;

    public Sentence(Piece[] pieces, char[] alphabets) {
        this.pieces = pieces;
        this.countOfCorrectOnes = 0;
        this.equivalentLetters = new char[26][2];
        for (int i = 0; i < 26; i++) {
            this.equivalentLetters[i][0] = alphabets[i];
            this.equivalentLetters[i][1] = 0;
        }
    }

    public Piece[] getPieces() {
        return this.pieces;
    }

    public void increaseCountOfCorrectOnes() {
        this.countOfCorrectOnes++;
    }

    public boolean allCorrect() {
        return this.countOfCorrectOnes == this.pieces.length;
    }

    public void setEquivalent(char c1, char c2) {
        for (int i = 0; i < 26; i++) {
            if (this.equivalentLetters[i][0] == c1) {
                this.equivalentLetters[i][1] = c2;
            }
        }
    }

    public char getEquivalent(char c) {
        for (int i = 0; i < 26; i++) {
            if (this.equivalentLetters[i][1] == c) {
                return this.equivalentLetters[i][0];
            }
        }
        return 0;
    }

}
