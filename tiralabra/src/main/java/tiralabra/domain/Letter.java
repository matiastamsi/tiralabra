/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.domain;

/**
 * An object that is been used while backtracking and finding
 * best guesses to do.
 * 
 * @author tamsi
 */
public class Letter implements Comparable<Letter> {
    private float freqEnglish;
    private float freqCipher;
    private int index;
    
    public Letter(float fE, float fC, int i) {
        this.freqEnglish = fE;
        this.freqCipher = fC;
        this.index = i;
    }
    
    public float distance(Letter l) {
        return Math.abs(this.freqEnglish - this.freqCipher);
    }

    @Override
    public int compareTo(Letter other) {
        if (distance(this) < distance(other)) {
            return 1;
        } else if (distance(this) > distance(other)) {
            return -1;
        } else {
            return 0;
        }
    }
}
