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

    private char c;
    private float frequency;
    private String queue;
    private int pointer;
    private String alphabets;

    /**
     *
     * @param c char from cipher
     * @param f frequency of the char
     */
    public Letter(char c, float f) {
        this.c = c;
        this.frequency = f;
        this.queue = "";
        this.pointer = 0;
        this.alphabets = "abcdefghijklmnopqrstuvwxyz";
    }

    public float getFrequency() {
        return this.frequency;
    }

    public char getChar() {
        return this.c;
    }

    public void setUpQueue(float[] frequenciesInEnglish) {
        int index = 0;
        while (index < alphabets.length()) {
            float smallestDifference = 100;
            char charWithSmallestDifference = 0;
            for (int i = 0; i < alphabets.length(); i++) {
                if (Math.abs(this.frequency - frequenciesInEnglish[i]) 
                        <= smallestDifference) {
                    boolean alreadyAdded = false;
                    for (char c : this.queue.toCharArray()) {
                        if (c == alphabets.charAt(i)) {
                            alreadyAdded = true;
                            break;
                        }
                    }
                    if (!alreadyAdded) {
                        charWithSmallestDifference = alphabets.charAt(i);
                    }
                }
            }
            this.queue += charWithSmallestDifference;
            index++;
        }
    }

    public char pollFirst() {
        if (pointer == 26) {
            return 0;
        }
        char first = queue.charAt(pointer);
        pointer++;
        return first;
    }
    
    public char getFirst() {
        if (pointer == 26) {
            return 0;
        }
        char first = queue.charAt(pointer);
        return first;
    }
}
