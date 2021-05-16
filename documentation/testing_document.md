# Testing document

## Unit tests

There are unit tests for the project that can be executed by:

    gradle test
    
The Jacoco test report can be generated after that by givin command:

    gradle jacocoTestReport
    

... and opening the html-page from location "/tiralabra/build/reports/jacoco/test/html/index.html"


## Performance testing

Unfortunately, I didn't have time to do automated performance testing and draw a graph out of it.

I did however add time recording to see how much time is spent for creating the trie and how much it takes to do cracking in total and just the recursion.

Here is an example story that has two frequencies not matching frequencies in English but the correct letters comes right after those false ones. I did not have time to make a story with no difference in frequencies (I think there could be a potential new project to make such a generator). And as can be spotted, the language in the story is poor in every way but it is due to trying to get frequencies to match. Here it comes and I suggest to try it and do changes to it to see how long the cracking works!

_Hey. This is a secret message. If the enemy breaks this cipher then it is over! If you are possessing this information and getting caught with the cipher, then I demand you with the order of the emperor to destroy this letter and to take your life away if needed to guarantee the will of the gods. The attack is at dawn and the plan is unamended regardless of minor changes. Previous battle was a superior victory and we gained more men and nice resources. I hope the next battle will be such superior. When the sun rises, attack the town. A half of our soldiers on foot come down the mountain with my lead and rest including archers follow, keeping an optimal distance to cover us. The hill elicits arrows to hit the enemy but the enemy can not reach and it is wise so do this also! Defence of the enemy is strong and huge but we have more soldiers. There exists a secret exit for the king and his family but we have solid information where that might be. If encountering any royal, especially the king, kill immediately. Save just potential slaves and any leader that might gain crucial information. Let us decide after the battle whether to burn the town down so tell your men not to do harm before we check what is potentially useful. Especially do seek for ammo. We also have catapults that should come before the attack. Use yours wisely. Demand is huge and it takes about a month to get more from the capitol depending on different things. Our alliances come from the south so do focus on the north. There is no other option than victory and we sacrifice for the gods tonight so the gods are pleased. They do see us! Soldiers will be awarded when the war is over. Tell them that they can enjoy the glory when coming back and everyone is also rewarded by special quality wine and a gold coin. Also, after the combat there are plenty of quality fruits such as bananas and pineapples that we robbed from the previous village. When receiving this, start preparing so it is done at dawn. Decide who are too injured and define an answer soon so that I know the situation there before dawn. Long live the emperor and may the gods be pleased to us!__

When I did run the program with alphabets bcdefghijklmnopqrstuvwxyza and this input it took:

    Creating a trie based on 58,112 English words took 129 milliseconds.
    ...
    Total time spent processing: 107 ms.
    Time spent cracking: 61 ms.
    ...
