# Implementation document

## Structure

### Packages
- tiralabra
- tiralabra.domain
- tiralabra.ui

### Classes
- tiralabra
  - Main.java
- tiralabra.domain
  - Cracker.java
  - Letter.java
  - Trie.java
  - TrieNode.java
- tiralabra.ui
  - ConsoleIO.java
  - IO.java
  - StubIO.java
  - UI.java


## Functionalities

There is three different main areas:

- Gathering, analyzing and saving frequencies to be used when trying out permutations.

    - The frequencies in English are from [Letter frequency in Wikipedia](https://en.wikipedia.org/wiki/Letter_frequency) and those are in a file named _frequencies.txt_ in program's root folder. The frequencies in the cipher are calculated when the user gives the cipher. Using these frequencies the Letter objects are been made for the different letters in the cipher. The Letter object creates a queue of chars in increasing order of the absolute value of the difference between a char's frequency in the cipher and each English char's frequency. In other words, there is a order for each letter in cipher which letter is been tried out next based on the similarity of frequencies.

- Creating a trie that is been used to check whether a string is an English word.

    -  A trie is made of English words from [here](http://www.mieliestronk.com/corncob_lowercase.txt). The Trie object has an array of TrieNodes that represents letters a-z. When __adding__ a word to the trie, adding starts from first letter that will be found from the root array. Then take the second letter and check if the TrieNode has a child TrieNode that represents that letter. If there is, move to next and check again. When encountering a situation that there is no child to match the letter, then a new TrieNode is been made. When the word ends, mark this TrieNode to be end of some word. The __finding__ a word from the trie is like the previous but just going from a TrieNode to its child TrieNode. If there is a way to go through these relations and the last TrieNode (that represents the last letter of the word) is end of some word then that string is a English word. If previously mentioned won't happen, the string is not a English word.

- Recursion to try out different permutations.

    - The recursion works as follows. First there is just one permutation (the cipher) and array of Letter objects. This permutation is manipulated so that this permutation creates so many new permutations that there is different letters in the cipher. This is implemented by going through the Letter objects of the cipher and increasing the pointer of the Letter object that points to the next char in Letter's queue. This new permutation is first checked whether it is correct solution and if not, then added to an array for the next round. The pointer is decreased to next iteration where next Letter is been manipulated and so on. When all permutations have been checked, then the recursive crack method is again called but this time with all permuations that were added to array. This continues so long that solution will found. Althought, new permutation is not added if it already exists in the array to terminate same branches. Also, at some point the pointer of a letter is gone through the queue so then that permutation can be leaved out. Here is an example of the recursion: ![Picture of example of recursion](https://github.com/matiastamsi/tiralabra/blob/main/documentation/example_recursion.png)
