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
public class Letters {

    private Letter[] letters;
    private String permutation;

    public Letters(Letter[] l, String p) {
        this.letters = l;
        this.permutation = p;
    }

    public Letter[] getLetters() {
        return this.letters;
    }
    
    public String getPermutation() {
        return this.permutation;
    }
}
