# Tiralabra

The project for the course [Data Structures Lab 2021 p4](https://tiralabra.github.io/2021_p4/en/) at the University of Helsinki.

The subject of this project is a cipher cracker that uses frequency analysis. More information about it is e.g. in [Wikipedia](https://en.wikipedia.org/wiki/Frequency_analysis)

First, the program creates a datastructure called "trie" based on word list (txt-file) located in the root of the program (current words are taken from: http://www.mieliestronk.com/corncob_lowercase.txt). The trie can answer to question whether a word exists in the file that represents words of a language.

To work correctly, this program expects that the cipher is breakable with frequency analysis (alphabets are just reordered) and there are spaces between words. There is also a list (txt-file) of frequencies of the language ([letter frequency in Wikipedia](https://en.wikipedia.org/wiki/Letter_frequency)). When a cipher is given, then frequencies of letters are been analyzed (later these results are been compared to the frequencies of the language).

The program then tries to crack the given cipher with backtracking that is recursive method to try out the combinations. To be efficient, the frequencies are been used, so that the most possible combination is been tried out next.

## Documentation
[Design document](https://github.com/matiastamsi/tiralabra/blob/main/documentation/design_document.md)

[Implementation document](https://github.com/matiastamsi/tiralabra/blob/main/documentation/implementation_document.md)

[Testing document](https://github.com/matiastamsi/tiralabra/blob/main/documentation/testing_document.md)

## Weekly reports
[Week 1 report](https://github.com/matiastamsi/tiralabra/blob/main/documentation/week_1_report.md)

[Week 2 report](https://github.com/matiastamsi/tiralabra/blob/main/documentation/week_2_report.md)

[Week 3 report](https://github.com/matiastamsi/tiralabra/blob/main/documentation/week_3_report.md)

[Week 4 report](https://github.com/matiastamsi/tiralabra/blob/main/documentation/week_4_report.md)
