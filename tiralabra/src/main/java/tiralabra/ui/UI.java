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
        io.print("\nLet's do some cracking!\n"
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
            cipher = generate("abcdefghijklmnopqrstuvwxyz",
                    manipulatedAlphabets, normalText);
            io.print("The cipher is:\n" + cipher + "\n");
        } else {
            io.print("Give a cipher:\n");
            cipher = io.nextLine();
        }
        cracker.giveCipher(cipher);
        cracker.order();
        cracker.createLetterArrays();
        io.print("Do you want to see the frequencies"
                + " of each letter? (y/n):\n");
        answer = io.nextLine();
        if (answer.equals("y")) {
            io.print(cracker.listFrequencies());
        }
        io.print(cracker.cracked() + "\n");
    }

    /**
     * This method is used to generate a cipher based on manipulated alphabets
     * (reordered) and known text.
     *
     * @param o old chars
     * @param n new chars
     * @param orginal text
     * @return text where old chars are replaced with the pairs
     */
    private String generate(String o, String n, String orginal) {
        String str = "";
        for (int i = 0; i < orginal.length(); i++) {
            char c = orginal.charAt(i);
            if (c <= 122 && c >= 97) {
                for (int j = 0; j < o.length(); j++) {
                    if (o.charAt(j) == c) {
                        str += n.charAt(j);
                    }
                }
            } else if (c == 32) {
                str += " ";
            }
        }
        return str;
    }

}
