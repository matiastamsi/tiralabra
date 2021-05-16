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

- Creating a trie that is been used to check whether a string is an English word. Also, there is a Trie for tried permutations.

    -  The word trie is made of English words from [here](http://www.mieliestronk.com/corncob_lowercase.txt). The Trie object has an array of TrieNodes that represents letters a-z. When __adding__ a word to the trie, adding starts from first letter that will be found from the root array. Then take the second letter and check if the TrieNode has a child TrieNode that represents that letter. If there is, move to next and check again. When encountering a situation that there is no child to match the letter, then a new TrieNode is been made. When the word ends, mark this TrieNode to be end of some word. The __finding__ a word from the trie is like the previous but just going from a TrieNode to its child TrieNode. If there is a way to go through these relations and the last TrieNode (that represents the last letter of the word) is end of some word then that string is a English word. If previously mentioned won't happen, the string is not a English word.
    -  A trie is also used as a way to save already tried permutations so that no permutation is tried more than once. See that it is faster and less space consuming than keeping track of permutations on an array.

- Recursion to try out different permutations.

    - The recursion works as follows. First there is just one permutation (the cipher) and array of Letter objects. This permutation is manipulated so that this permutation creates so many new permutations that there is different letters in the cipher. This is implemented by going through the Letter objects of the cipher and increasing the pointer of the Letter object that points to the next char in Letter's queue. This new permutation is first checked whether it is correct solution and if not, then added to an array for the next round if the permutation is not already added. The pointer is decreased to next iteration where next Letter is been manipulated and so on. When all permutations have been checked, then the recursive crack method is again called but this time with all permuations that were added to array. This continues so long that solution will found. At some point the pointer of a letter is gone through all chars in the queue so then that permutation can be leaved out. Here is an example of the recursion: ![Picture of example of recursion](https://github.com/matiastamsi/tiralabra/blob/main/documentation/example_recursion.png)

## Time complexities

### Trie

It takes O(n*k) to __create__ a trie. The 'n' is number of the words (strings) and 'k' is the average length of the words (strings).

    while word in file
        addWord(word)
        
    addWord(word)
        node = findFirstNode(word.charAt(0))
        while i = 0 in word.length
            for child in node.getChildren
                if (word.charAt(i) == child.getNode)
                    goToNext = true
            if !goToNext
                // Create new node
            if i == word.length - 1
                // Mark as a end of the word
                  
     findFirstNode(c)
         for node in roots // 26 chars in roots
             if node.getNode == c
                 return node

Checking whether a string is a word - __finding__ - takes O(26 + l) ~ O(l) where 'l' is the length of the word. See that first the first letter is searched from the array with length of 26.

    findWord(word)
        node = findFirstNode(word.charAt(0))
        if node == null
            return false
        while i = 0 in word.length
            for child in node.getChildren
                if child.getNode == word.charAt(i)
                    node = child
                    found = true
            if !found or (i == word.length - 1 and !node.isEndOfWord)
                return false
        return true

### Recursion

The recursion takes O(26*n*m)

    crack(lettersArray)
        newLettersArray = lettersArray.length ^ 2
        for letters in lettersArray
            for i = 0 in 25
                ...
                replaceLettersInCipher(letters) // O(n)
                ...
        crack(newLettersArray)
            

## Space complexities

### Trie

It takes O(n*k) where 'n' is number of the words in a file and 'k' is the average length of the words.

    while word in file
        addWord(word)

### Recursion

The recursion creates on each call as many new permutations as there is different letters in the cipher and this continues as long as all letters in alphabets are gone through.

    crack(lettersArray)
    newLettersArray = lettersArray.length ^ 2
    for letters in lettersArray
        for i = 0 in 25
            ...
            replaceLettersInCipher(letters) // O(n)
            ...
    crack(newLettersArray)
