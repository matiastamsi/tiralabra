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
    private String pileC;
    private String pileE;

    /**
     * Cracker has knowledge of the letters in English and in a cipher. A trie
     * is given but other things cracker finds out later on when methods are
     * called.
     *
     * @param trie using the trie that is already been made.
     */
    public Cracker(Trie trie) {
        this.trie = trie;
        this.alphabets = trie.getAlphabets();
        this.sentence = null;
        this.cipher = "";
        // Source is https://en.wikipedia.org/wiki/Letter_frequency
        this.frequenciesInEnglish = loadFrequenciesInEnglish("frequencies.txt");
        this.frequenciesInCipher = new float[26];
        this.pileC = "";
        this.pileE = "";
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
        order(); // Order the frequencies to match after getting the cipher.
    }

    /**
     * A helper method that calls 26 times to find the letters with closest
     * frequencies and add those to the piles.
     */
    private void order() {
        int countOfHandledOnes = 0;
        while (countOfHandledOnes < 26) {
            this.pileE += next(this.frequenciesInEnglish, pileE);
            this.pileC += next(this.frequenciesInCipher, pileC);
            countOfHandledOnes++;
        }
    }

    /**
     * A helper method that checks which letter has the highest frequency and
     * returns it if it is not already added to handled letters.
     *
     * @param freq array of frequencies
     * @param handledLetters string of handled chars
     * @return a char that has currently the highest frequency.
     */
    private char next(float[] freq, String handledLetters) {
        float biggest = 0;
        char c = 0;
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > biggest) {
                boolean handled = false;
                for (int j = 0; j < handledLetters.length(); j++) {
                    if (handledLetters.charAt(j) == this.alphabets[i]) {
                        handled = true;
                        break;
                    }
                }
                if (!handled) {
                    biggest = freq[i];
                    c = this.alphabets[i];
                }
            }
        }
        return c;
    }

    /**
     * A private method that is used when provided the cipher as a string. It
     * splits the sentence into pieces and creates Sentence object.
     *
     * @param str the cipher as a string
     * @return the sentence object from that string
     */
    private Sentence createSentence(String str) {
        String[] splitted = str.split(" ");
        String[] pieces = new String[splitted.length];
        for (int i = 0; i < pieces.length; i++) {
            pieces[i] = splitted[i];
        }
        return new Sentence(pieces);
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
            if (cipher.charAt(i) != 32) { // The space isn't a letter
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
        answer += "Matching pairs:\n" + pileE + "\n" + pileC;
        return answer;
    }

    /**
     * This method launches the backtracking and provides the correct sentence
     * in English.
     *
     * @return sentence as a string
     */
    public String cracked() {
        Sentence s = crack(this.sentence, 0, 0);
        return s.toString();
    }

    /**
     * The method that recursively tries to find matching letters from the
     * cipher and from English.
     *
     * @param s sentence
     * @param indexC is the index in the cipher's ordered letter pile.
     * @param indexE is the index in the ordered pile of English letters.
     * @return Sentence object.
     */
    private Sentence crack(Sentence s, int indexC, int indexE) {

        System.out.println(s.toString());

        if (allCorrect(s.getPieces())) {
            return s;
        }
        for (int i = indexC; i < pileC.length(); i++) {
            for (int j = indexE; j < pileE.length(); j++) {
                Sentence sentence = s;
                char oldChar = pileC.charAt(i);
                char newChar = pileE.charAt(j);
                String copy = sentence.toString();
                sentence.replace(oldChar, newChar);
                if (!sentence.toString().equals(copy)) {
                crack(sentence, i, j + 1);
                }
                sentence.replace(newChar, oldChar);
            }
        }
        return sentence;
    }

    /**
     * Tells whether all pieces are words.
     *
     * @param pieces sentence's pieces
     * @return boolean value
     */
    private boolean allCorrect(String[] pieces) {
        for (String p : pieces) {
            if (!trie.findWord(p)) {
                return false;
            }
        }
        return true;
    }
}
