/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.domain;

/**
 *
 * @author tamsi
 */
public class LettersArray {
    private Letters[] lettersAsArray;
    private int pointer;
    
    public LettersArray(int size) {
        this.lettersAsArray = new Letters[size];
        this.pointer = 0;
    }
    
    public Letters[] getLettersAsArray() {
        return this.lettersAsArray;
    }
    
    public void addLetters(Letters l) {
        this.lettersAsArray[pointer] = l;
        this.pointer++;
    }
    
    public void compress() {
        int empty = this.lettersAsArray.length - pointer;
        Letters[] smaller = new Letters[this.lettersAsArray.length - empty];
        for (int i = 0; i < smaller.length; i++) {
            smaller[i] = this.lettersAsArray[i];
        }
        this.lettersAsArray = smaller;
    }
}
