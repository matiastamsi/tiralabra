package tiralabra.ui;

import java.util.Scanner;
import tiralabra.domain.Trie;

/**
 *
 * @author tamsi
 */
public class UI {

    private IO io;
    private Trie trie;

    public UI(final IO io, final Trie trie) {
        this.io = io;
        this.trie = trie;
    }

    public void run() {
        boolean keepGoing = true;
        while (keepGoing) {
            io.print("Give a command from:\nexit");
            String command = io.nextLine();
            switch (command) {
                case "exit":
                    io.print("Bye!");
                    keepGoing = false;
                    break;
            }
        }
    }
}
