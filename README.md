# Tiralabra

The project for the course [Data Structures Lab 2021 p4](https://tiralabra.github.io/2021_p4/en/) at the University of Helsinki.

The subject of this project is a cipher cracker that uses frequency analysis. This program can crack ciphers both Ceasar and substitution ciphers. This program assumes that there is spaces between words and the words in the cipher are correct English words (no misspellings etc.) See also, that to work there has to be either enough letters in the cipher to be able to do frequency analysis or the cipher has to be short and/or ideal to crack, for example, many same letters in it.

## Documentation
[Design document](https://github.com/matiastamsi/tiralabra/blob/main/documentation/design_document.md)

[Implementation document](https://github.com/matiastamsi/tiralabra/blob/main/documentation/implementation_document.md)

[Testing document](https://github.com/matiastamsi/tiralabra/blob/main/documentation/testing_document.md)

[User instructions](https://github.com/matiastamsi/tiralabra/blob/main/documentation/user_instructions.md)

## Further development ideas

- The array that saves permutations will eventially become out of memory. When this happens, the program just stops cracking otherwise leading to exception. In the future this being the case, there could be a good time for reducing permutations (leaving those out that have not correct English words at all or just keep those with higher rate of correct words).
- Performance testing automated and documented with different ciphers to see how many differences there can be in the frequencies and how deep can it go still finding a solution. This could be a whole new functionality and solution presented as a graph.
- Userinterface could be better to make the program easier to use.
- And much more!

## Weekly reports
[Week 1 report](https://github.com/matiastamsi/tiralabra/blob/main/documentation/week_1_report.md)

[Week 2 report](https://github.com/matiastamsi/tiralabra/blob/main/documentation/week_2_report.md)

[Week 3 report](https://github.com/matiastamsi/tiralabra/blob/main/documentation/week_3_report.md)

[Week 4 report](https://github.com/matiastamsi/tiralabra/blob/main/documentation/week_4_report.md)

[Week 5 report](https://github.com/matiastamsi/tiralabra/blob/main/documentation/week_5_report.md)

[Week 6 report](https://github.com/matiastamsi/tiralabra/blob/main/documentation/week_6_report.md)
