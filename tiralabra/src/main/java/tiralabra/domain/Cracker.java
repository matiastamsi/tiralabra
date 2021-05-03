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
    private int countOfLetters;
    private int countOfDifferentLettersInCipher;
    private String pileC;
    private String pileE;
    private Letter[] differentLettersInCipher;
    private Letter[] cipherAsLetterArray;

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
        this.countOfLetters = 0;
        this.countOfDifferentLettersInCipher = 0;
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
        this.countOfLetters = countOfLetters(this.cipher);

        for (int i = 0; i < this.alphabets.length; i++) {
            char c = this.alphabets[i];
            int count = 0;
            for (int j = 0; j < cipher.length(); j++) {
                if (cipher.charAt(j) == c) {
                    count++;
                }
            }
            this.frequenciesInCipher[i] = (float) count / this.countOfLetters;
        }

        order();
        createLetterArrays();
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

        // Fill missing letters by assuming based on pileE.
        for (int i = 0; i < pileE.length(); i++) {
            char cE = pileE.charAt(i);
            boolean found = false;
            for (int j = 0; j < pileC.length(); j++) {
                if (pileC.charAt(j) == cE) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                pileC += cE;
            }
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

    private void createLetterArrays() {
        this.cipherAsLetterArray = new Letter[this.cipher.length()];
        this.differentLettersInCipher = new Letter[26];
        String handled = "";
        int indexCountingDiffLetters = 0;
        for (int i = 0; i < this.cipher.length(); i++) {
            char c = this.cipher.charAt(i);
            if (c > 122 || c < 97) {
                continue;
            }

            int index = 0;
            for (index = 0; index < 26; index++) {
                if (this.alphabets[index] == c) {
                    break;
                }
            }
            boolean alreadyHandled = false;
            if (indexCountingDiffLetters <= 26) {
                for (char h : handled.toCharArray()) {
                    if (h == c) {
                        alreadyHandled = true;
                        break;
                    }
                }
            }
            Letter letter = new Letter(c, this.frequenciesInCipher[index], index);
            if (!alreadyHandled) {
                this.differentLettersInCipher[indexCountingDiffLetters] = letter;
                indexCountingDiffLetters++;
            }
            this.cipherAsLetterArray[i] = letter;
        }
        this.countOfDifferentLettersInCipher = indexCountingDiffLetters;
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
        return crack(letters, 0);
    }

    private String crack(Letter[] l, int i) {
        String replaced = replaceLettersInCipher(l);
        if (allCorrect(replaced)) {
            return replaced;
        }
        int index = i;
        if (index == this.countOfDifferentLettersInCipher) {
            index = 0;
        }
        Letter letterInCipher = this.differentLettersInCipher[index];
        int indexInAlphabets = letterInCipher.getIndexInAlphabets();
        Letter[] copy = l;
        Letter letterToPop = copy[indexInAlphabets];
        int pointer = letterToPop.popFirst();
        copy[indexInAlphabets] = letterToPop;

        return crack(copy, index + 1);

    }

    /**
     * Tells whether all pieces are words.
     *
     * @param pieces sentence's pieces
     * @return boolean value
     */
    private boolean allCorrect(String pieces) {
        System.out.println(pieces);
        for (String p : pieces.split(" ")) {
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
            Letter letter = new Letter(this.alphabets[i], this.frequenciesInCipher[i], i);
            letter.setUpQueue(this.frequenciesInEnglish);
            letters[i] = letter;
        }
        return letters;
    }

    private String replaceLettersInCipher(Letter[] letters) {
        String newString = "";
        for (Letter letter : this.cipherAsLetterArray) {
            newString += letters[letter.getIndexInAlphabets()].next();
        }
        return newString;
    }
}
