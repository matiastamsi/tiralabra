# Implementation document

## About the structure of the project

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


All the most relevant things for this project (considering this is a course about data structures and algorithms) is in the domain package.

Trie is the data structure that is used when checking if some string is in the language. Trie is build with TrieNodes that saves the information about the relations to other TrieNodes (letters). There is a txt-file with 58,110 words (English) that are added..

Cracker is the class that has the most of the logic. It counts the frequencies of the cipher and downloads the frequencies in English from txt-file (in root). The Cracker also does the recursive backtracking. It is been launched from the cracked-method and the crack-method is the recursive method.

So, what happens in the recursion? Different permutations will be tested how to replace the cipher letter by letter from English. To be efficient algorithm, we don't afford to do this just by with brute force because there is huge amount of permutations. This is why the replacing is been done by guessing which letter has the closest frequency with the letter from the cipher.

The Letter object stores information about the letter but also about its frequency in the cipher and it holds track about a queue where is order of letters that will be tried out next (the smallest difference).

To make the backtracking to work there is many things to be taken concern off.

- When all letters have been changed to do the checking if this permutation is correct.
- Which letters are taken in that iteration.
- Some letter might have been already replaced so don't touch it.
- But things back if it won't produce good outcome.
- Order of doing this all etc...
