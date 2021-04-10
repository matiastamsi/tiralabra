package tiralabra.domain;

/**
 *
 * @author tamsi
 */
public class Piece {

    private String str;
    private int length;
    private char[] letters;
    private String[] tried;
    private boolean correct;

    public Piece(String str) {
        this.str = str;
        this.length = str.length();
        this.correct = false;
        this.letters = new char[this.length];
        this.tried = new String[this.length];
        for (int i = 0; i < this.length; i++) {
            this.letters[i] = str.charAt(i);
            this.tried[i] = "" + str.charAt(i);
        }
    }

    public void setCorrect() {
        this.correct = true;
    }

    public void setString(String s) {
        this.str = s;
    }

    public boolean isCorrect() {
        return this.correct;
    }

    public String getString() {
        return this.str;
    }

    public int getLength() {
        return this.length;
    }

    public char getCharAt(int i) {
        return this.letters[i];
    }

    public boolean isTried(char c) {
        for (String s : tried) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == c) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setTriedChars(int i, char c) {
        this.tried[i] += c;
    }
}
