/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.domain;

/**
 * An object that is been used while backtracking and finding best guesses to
 * do.
 *
 * @author tamsi
 */
public class Letter {

    private final char c;
    private final float frequency;
    private String queue;
    private int pointer;
    private final String alphabets;
    private int indexInAlphabets;

    /**
     *
     * @param c char from cipher
     * @param f frequency of the char
     * @param i index in alphabets
     */
    public Letter(char c, float f, int i) {
        this.c = c;
        this.frequency = f;
        this.queue = "";
        this.pointer = 0;
        this.alphabets = "abcdefghijklmnopqrstuvwxyz";
        this.indexInAlphabets = i;
    }

    public float getFrequency() {
        return this.frequency;
    }

    public char getChar() {
        return this.c;
    }
    
    public String getQueue() {
        return this.queue;
    }
    
    public int getIndexInAlphabets() {
        return this.indexInAlphabets;
    }

    public void setUpQueue(float[] frequenciesInEnglish) {
        int index = 0;
        while (index < alphabets.length()) {
            float smallestDifference = 100;
            char charWithSmallestDifference = 0;
            for (int i = 0; i < alphabets.length(); i++) {
                if (Math.abs(this.frequency - frequenciesInEnglish[i])
                        < smallestDifference) {
                    boolean alreadyAdded = false;
                    for (char q : this.queue.toCharArray()) {
                        if (q == alphabets.charAt(i)) {
                            alreadyAdded = true;
                            break;
                        }
                    }
                    if (!alreadyAdded) {
                        smallestDifference
                                = Math.abs(this.frequency - frequenciesInEnglish[i]);
                        charWithSmallestDifference = alphabets.charAt(i);
                    }
                }
            }
            this.queue += charWithSmallestDifference;
            index++;
        }
    }

    public char next() {
        return this.queue.charAt(this.pointer);
    }
    
    public int popFirst() {
        this.pointer++;
        return this.pointer;
    }
}
