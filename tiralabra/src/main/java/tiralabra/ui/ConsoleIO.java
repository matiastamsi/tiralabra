package tiralabra.ui;

import java.util.Scanner;

/**
 * The IO class that handles the input/output that is going on in the console.
 *
 * @author tamsi
 */
public class ConsoleIO implements IO {

    private Scanner scanner;

    /**
     * Constructor creates a Scanner object.
     */
    public ConsoleIO() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Take an input.
     *
     * @return input from the user
     */
    @Override
    public String nextLine() {
        return this.scanner.nextLine();
    }

    /**
     * Print output to the console.
     *
     * @param output String that is wanted to print.
     */
    @Override
    public void print(final String output) {
        System.out.println(output);
    }

    @Override
    public boolean hasNextLine() {
        return this.scanner.hasNextLine();
    }
}
