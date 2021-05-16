# User instructions

This project can be downloaded via terminal by giving the command:

    git clone git@github.com:matiastamsi/tiralabra.git
    
or downloading the jar file. If planning to try the program, I suggest downloading the jar file because it is easier to run the program with different flags needed to increase java's heap space. Also, because there are files required to run the program, I suggest to clone the project (as previously mentioned) and then running the jar file located '/tiralabra/tiralabra/'. This might be the easiest way.

## Running gradle

If you want to run the cloned project, go to project's root folder _tiralabra_ and then into the gradle project folder _tiralabra_. Running the program happens by:

        gradle run
        
and now program should be running.

## Running jar

If you want to run the jar file, give a command:

    java -jar tiralabra.jar
    
Due to the nature of this project, you might want to use flag to increase the heap space to avoid OutOfMemoryError. If encountering any, use following instead of previous (the default size wasn't enough). For example:

    java -Xmx1g -jar tiralabra.jar
    
which allocates maximum size for the heap to be 1 GB. So, just increasing that number 2,3,4,... can help as long as there is enough physical memory on your computer.

__Remember that if running the jar file the program assumes that it is in same folder as frequencies.txt and english_words_lowercase.txt.__

## Using the program

When the program is running there is options __exit__ (that just stops the program) and __crack__ that is the command that leads you to the action.

After feeding __crack__ there is a decision to be made. If you have a cipher ready (English text transformed as a cipher) then press anything. If you want to feed the text as a real English text, then feed command __g__ that leads you to generating the cipher. It will produce two additional steps. First, provide alphabets as a string that includes 26 chars. Like, the normal alphabets are _abcdefghijklmnopqrstuvwxyz_ so give those reordered by your preferences. Second step is to give the text. The program knows to drop letters that are not wanted, so just be careful providing correct English no misspellings etc.

Now that the program has your cipher, it lists frequencies and letters' queues (queue saves the order of replacing letters by another).

Then just wait if the cracker can crack the cipher. Remember that if you get OutOfMemoryError increase the heap space (see Running jar).
