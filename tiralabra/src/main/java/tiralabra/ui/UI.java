package tiralabra.ui;

import tiralabra.domain.Cracker;

/**
 * The user interface that handles the interacting with the user.
 *
 * @author tamsi
 */
public class UI {

    private final IO io;
    private final Cracker cracker;

    /**
     * The constructor creates a user interface
     *
     * @param io Input/Output object
     * @param cracker to crack/break/solve the cipher
     */
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
                + "If you want to generate a cipher by first giving the \n"
                + "manipulated alphabets, then press 'g'.\n"
                + "If you want to give the cipher straight,\n"
                + "then press enter!\n");
        String answer = io.nextLine();
        String cipher;
        if (answer.equals("g")) {
            io.print("Give alphabets:\nabcdefghijklmnopqrstuvwxyz\n");
            String manipulatedAlphabets = io.nextLine();
            io.print("Give something to be changed:\n");
            String normalText = io.nextLine();
            cipher = cracker.generate("abcdefghijklmnopqrstuvwxyz",
                    manipulatedAlphabets, normalText);
            io.print("The cipher is:\n" + cipher + "\n");
        } else {
            io.print("Give a cipher:\n");
            cipher = io.nextLine();
        }
        cracker.giveCipher(cipher);
        io.print("Do you want to see the frequencies"
                + " of each letter? (y/n):");
        answer = io.nextLine();
        if (answer.equals("y")) {
            io.print(cracker.listFrequencies());
        }
        io.print(cracker.cracked() + "\n");
    }

}
