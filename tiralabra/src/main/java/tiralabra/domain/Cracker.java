package tiralabra.domain;

import java.nio.file.Paths;
import java.util.Scanner;

/**
 * The object that tries to crack/solve what's written into the cipher.
 *
 * @author tamsi
 */
public final class Cracker {

    private final Trie trie;
    private final char[] alphabets;
    private String cipher;
    private Sentence sentence;
    private final float[] frequenciesInEnglish;
    private final float[] frequenciesInCipher;

    public Cracker(Trie trie) {
        this.trie = trie;
        this.alphabets = trie.getAlphabets();
        this.sentence = null;
        this.cipher = "";
        // Source is https://en.wikipedia.org/wiki/Letter_frequency
        this.frequenciesInEnglish = loadFrequenciesInEnglish("frequencies.txt");
        this.frequenciesInCipher = new float[26];
    }

    public float[] loadFrequenciesInEnglish(String path) {
        float[] array = new float[26];
        int i = 0;
        try (Scanner scanner = new Scanner(Paths.get(path))) {
            while (scanner.hasNextFloat()) {
                float f = scanner.nextFloat();
                array[i] = f;
                i++;
            }
        } catch (Exception e) {
            System.out.println("Error while reading the file: "
                    + e.getMessage());
        }
        return array;
    }

    public void giveCipher(String cipher) {
        this.cipher = cipher.toLowerCase();
        this.sentence = createSentence(this.cipher);

        int countOfLetters = countOfLetters(this.cipher);

        for (int i = 0; i < this.alphabets.length; i++) {
            char c = this.alphabets[i];
            int count = 0;
            for (int j = 0; j < cipher.length(); j++) {
                if (cipher.charAt(j) == c) {
                    count++;
                }
            }
            this.frequenciesInCipher[i] = (float) count / countOfLetters;
        }
    }

    public Sentence createSentence(String str) {
        String[] splitted = str.split(" ");
        Piece[] pieces = new Piece[splitted.length];
        for (int i = 0; i < pieces.length; i++) {
            pieces[i] = new Piece(splitted[i]);
        }
        return new Sentence(pieces, this.alphabets);
    }

    private int countOfLetters(String cipher) {
        int count = 0;
        for (int i = 0; i < cipher.length(); i++) {
            if (cipher.charAt(i) != 32) {
                count++;
            }
        }
        return count;
    }

    public String listFrequencies() {
        String answer = "[letter] / [freq. in English] / [freq. in the cipher]"
                + "\n";
        for (int i = 0; i < this.alphabets.length; i++) {
            answer += this.alphabets[i] + " / " + this.frequenciesInEnglish[i]
                    + " / " + this.frequenciesInCipher[i] + "\n";
        }
        return answer;
    }

    public String cracked() {
        String answer = "";
        Sentence s = crack(this.sentence);
        for (Piece p : s.getPieces()) {
            answer += p.getString() + " ";
        }
        return answer;
    }

    private Sentence crack(Sentence s) {
        Sentence sentence = s;
        while (!sentence.allCorrect()) {
            sentence = crackPieces(sentence);
        }

        return sentence;
    }

    private Sentence crackPieces(Sentence s) {
        Sentence sentence = s;
        for (Piece p : sentence.getPieces()) {

            Piece solvedPiece = crackPiece(p, sentence, 0);
            for (int i = 0; i < p.getLength(); i++) {
                if (p.getCharAt(i) != solvedPiece.getCharAt(i)) {
                    sentence.setEquivalent(p.getCharAt(i), solvedPiece.getCharAt(i));
                }
            }
            p = solvedPiece;
            sentence.increaseCountOfCorrectOnes();

        }
        return sentence;
    }

    private Piece crackPiece(Piece p, Sentence s, int i) {

        if (trie.findWord(p.getString())) {
            return p;
        }

        if (i == p.getLength()) {
            return crackPiece(p, s, 0);
        }

        int index = i;
        Piece piece = p;
        String willFound = piece.getString().substring(0, index + 1);
        while (trie.findWord(willFound)) {
            willFound += piece.getCharAt(index + 1);
            index++;
        }
        char currentChar = p.getCharAt(index);
        char newChar = s.getEquivalent(currentChar);
        if (newChar != 0) {
            String replace = piece.getString().replace(currentChar, newChar);
            piece.setString(replace);
            piece.setTriedChars(index, newChar);
            return crackPiece(piece, s, index + 1);
        }
        newChar = findNextToTest(piece, currentChar);
        String replace = piece.getString().replace(currentChar, newChar);
        piece.setString(replace);
        piece.setTriedChars(index, newChar);
        return crackPiece(piece, s, index + 1);
    }

    private char findNextToTest(Piece p, char c) {
        float freq = 0;
        for (int i = 0; i < this.alphabets.length; i++) {
            if (this.alphabets[i] == c) {
                freq = this.frequenciesInCipher[i];
                break;
            }
        }
        float smallestDifference = Math.abs((float) (freq - this.frequenciesInEnglish[0]));
        char closest = this.alphabets[0];
        for (int i = 1; i < this.frequenciesInEnglish.length; i++) {
            float diff = Math.abs((float) freq - this.frequenciesInEnglish[i]);
            if (smallestDifference >= diff) {
                smallestDifference = diff;
                if (!p.isTried(this.alphabets[i])) {
                    closest = this.alphabets[i];
                }
            }
        }
        return closest;
    }
}
