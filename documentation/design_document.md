# Design document

__Disclaimer:__ This document is written before an actual implementation. To see updated information and correct analyzing, see the [Implementation document](https://github.com/matiastamsi/tiralabra/blob/main/documentation/implementation_document.md)

I'm implementing a substitution cipher solver that tries to solve a cipher by backtracking while using the frequencies of each letter in English and comparing possible combinations to a trie that is formed based on a dictionary.

The program needs a wide English dictionary that it uses while running. It takes a cipher as an input.

It is hard to say what could be the time complexity. It will be corrected when the project goes on. But for backtracking it is O(n^m) ([Tirakirja](https://www.cs.helsinki.fi/u/ahslaaks/tirakirja/)) and now n = 26 (the number of the letters in modern English alphabets) so we get O(26^m) where the m is the length of the cipher. The time complexity for the trie is linear so O(m) ([Wikipedia](https://en.wikipedia.org/wiki/Trie)). The space complexity for the trie is O(n*m) (each letter from the cipher has space for 26 letters) and for the backtracking it is at most O(n^m) = O(26^m).

I'm doing this project as a part of my studies in bachelor’s in science (bSc) (candidate).

This project is planned to be fully in English. The coding language is Java.
