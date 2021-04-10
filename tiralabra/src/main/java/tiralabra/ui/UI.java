package tiralabra.ui;

import tiralabra.domain.Cracker;

/**
 * The user interface that handles the interacting with the user.
 *
 * @author tamsi
 */
public class UI {

    private IO io;
    private Cracker cracker;

    public UI(final IO io, final Cracker cracker) {
        this.io = io;
        this.cracker = cracker;
    }

    /**
     * The method that runs the program.
     */
    public void run() {
        boolean keepGoing = true;
        while (keepGoing) {
            io.print("Give a command from the following:"
                    + "\nexit\ncrack");
            String command = io.nextLine();
            switch (command) {
                case "exit":
                    io.print("Bye!");
                    keepGoing = false;
                    break;
                case "crack":
                    crack();
                    break;
                default:
                    io.print("The command " + command + " not found\n");
            }
        }
    }

    /**
     * Actions when user chooses to crack a cipher.
     */
    private void crack() {
        io.print("Let's do some cracking!\n"
                + "Give a cipher to be cracked: ");
        String cipher = io.nextLine();
        cracker.giveCipher(cipher);
        io.print("Do you want to see the frequencies"
                + " of each letter? (y/n):");
        String answer = io.nextLine();
        if (answer.equals("y")) {
            io.print(cracker.listFrequencies());
        }
        io.print(cracker.cracked() + "\n");
    }
}
