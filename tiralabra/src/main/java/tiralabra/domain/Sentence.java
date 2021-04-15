package tiralabra.domain;

/**
 * An object that represents a sentence that is actually
 * an array of strings. The sentence name doesn't mean that
 * it is readable.
 *
 * @author tamsi
 */
public class Sentence {

    private String[] pieces;

    public Sentence(String[] pieces) {
        this.pieces = pieces;
    }

    public String[] getPieces() {
        return this.pieces;
    }

    public void replace(char x, char y) {

        if (!letterExists(y)) {
            for (int i = 0; i < this.pieces.length; i++) {
                this.pieces[i] = this.pieces[i].replace(x, y);
            }
        }
    }

    private boolean letterExists(char c) {
        for (String p : this.pieces) {
            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) == c) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String str = "";
        for (String p : this.pieces) {
            str += p + " ";
        }
        return str;
    }
}
