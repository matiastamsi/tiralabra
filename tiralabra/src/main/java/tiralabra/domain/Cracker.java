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
    private final float[] frequenciesInEnglish;
    private final float[] frequenciesInCipher;
    private int differentOptionsToReplace;
    private int countOfLetters;

    /**
     * Cracker has knowledge of the letters in English and in a cipher. A trie
     * is given but other things cracker finds out later on when methods are
     * called.
     *
     * @param trie using the trie that is already been made.
     */
    public Cracker(Trie trie) {
        this.trie = trie;
        this.alphabets = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        this.cipher = "";
        // Source is https://en.wikipedia.org/wiki/Letter_frequency
        this.frequenciesInEnglish = loadFrequenciesInEnglish("frequencies.txt");
        this.frequenciesInCipher = new float[26];
        this.differentOptionsToReplace = 0;
        this.countOfLetters = 0;
    }

    /**
     * A method to load the frequencies from the file.
     *
     * @param path location of the file
     * @return array of floats
     */
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

    /**
     * When the cracker is been created it gets an empty cipher. This method
     * initializes those instances that have something to do with the cipher.
     *
     * @param cipher given as a string
     */
    public void giveCipher(String cipher) {
        this.cipher = cipher.toLowerCase();
        this.countOfLetters = countOfLetters(this.cipher);

        for (int i = 0; i < this.alphabets.length; i++) {
            char c = this.alphabets[i];
            int count = 0;
            for (int j = 0; j < cipher.length(); j++) {
                if (cipher.charAt(j) == c) {
                    count++;
                }
            }
            if (count > 0) {
                this.differentOptionsToReplace++;
            }
            this.frequenciesInCipher[i] = (float) count / this.countOfLetters;
        }
    }

    /**
     * A private method to count the letters so that the frequencies can be
     * solved.
     *
     * @param cipher the string that is been analysed
     * @return the count
     */
    private int countOfLetters(String cipher) {
        int count = 0;
        for (int i = 0; i < cipher.length(); i++) {
            if (cipher.charAt(i) >= 97 && cipher.charAt(i) <= 122) {
                count++;
            }
        }
        return count;
    }

    /**
     * A method that provides the frequencies and matching letters as a string.
     *
     * @return the answer as a string.
     */
    public String listFrequencies() {
        String answer = "[letter] / [freq. in English] / [freq. in the cipher]"
                + "\n";
        for (int i = 0; i < this.alphabets.length; i++) {
            answer += this.alphabets[i] + " / " + this.frequenciesInEnglish[i]
                    + " / " + this.frequenciesInCipher[i] + "\n";
        }
        return answer;
    }

    /**
     * This method launches the backtracking and provides the correct sentence
     * in English.
     *
     * @return solved pieces as a string
     */
    public String cracked() {
        Letter[] letters = setUpLetters();
        String[] cracked = crack(this.cipher.split(" "), "", "", letters, 0,
                this.differentOptionsToReplace);
        String answer = "";
        for (String s : cracked) {
            answer += s + " ";
        }
        return answer;
    }

    private String[] crack(String[] p, String r, String t, Letter[] l, int index, int options) {
        String replaced = r;
        String taken = t;
        String[] pieces = p;
        Letter[] letters = l;
        if (index < options) {
            for (char c : this.cipher.toCharArray()) {
                boolean alreadyReplaced = false;
                for (char x : replaced.toCharArray()) {
                    if (x == c) {
                        alreadyReplaced = true;
                        break;
                    }
                }
                if (!alreadyReplaced && c != 32) {
                    int indexOfChar = 0;
                    for (int i = 0; i < this.alphabets.length; i++) {
                        if (this.alphabets[i] == c) {
                            indexOfChar = i;
                        }
                    }
                    Letter letter = letters[indexOfChar];
                    char newChar = letter.getFirst();
                    boolean goNext = false;
                    for (char x : taken.toCharArray()) {
                        if (x == newChar) {
                            goNext = true;
                            break;
                        }
                    }
                    if (newChar != 0 && !goNext) {

                        Letter copyLetter = letter;
                        String copyReplaced = replaced;
                        String copyTaken = taken;
                        String[] copyPieces = pieces;
                        newChar = letter.pollFirst();
                        letters[indexOfChar] = letter;
                        taken += newChar;
                        for (int i = 0; i < pieces.length; i++) {
                            pieces[i] = pieces[i].replace(c, newChar);
                        }
                        replaced += c;
                        crack(pieces, replaced, taken, letters, index + 1, options);
                        replaced = copyReplaced;
                        pieces = copyPieces;
                        taken = copyTaken;
                        letters[indexOfChar] = copyLetter;
                        crack(pieces, replaced, taken, letters, index, options);
                    }
                }
            }
        } else {
            if (allCorrect(p)) {
                return p;
            }
        }
        return crack(pieces, "", "", letters, 0, options);
    }

    /**
     * Tells whether all pieces are words.
     *
     * @param pieces sentence's pieces
     * @return boolean value
     */
    private boolean allCorrect(String[] pieces) {
        for (String p : pieces) {
            System.out.println(p);
            if (!trie.findWord(p)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Creates an array of Letter objects.
     *
     * @return array
     */
    private Letter[] setUpLetters() {
        Letter[] letters = new Letter[26];
        for (int i = 0; i < 26; i++) {
            Letter letter = new Letter(this.alphabets[i], this.frequenciesInCipher[i]);
            letter.setUpQueue(this.frequenciesInEnglish);
            letters[i] = letter;
        }
        return letters;
    }
}
