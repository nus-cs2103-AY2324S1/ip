package chad.io;

import java.util.Scanner;

/**
 * Implements the IOHandler interface for console-based input and output.
 */
public class ConsoleIO implements IOHandler {

    private Scanner sc;

    /**
     * Initializes a new Scanner for console input.
     */
    public ConsoleIO() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Greets the user upon program startup.
     */
    @Override
    public void greet() {
        System.out.println("Hello! I'm Chad!\nWhat do you want?");
        System.out.println("______________________________________");
    }

    /**
     * Bids farewell to the user.
     */
    @Override
    public void sayGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Reads a line of input from the user.
     * @return the line as a string.
     */
    @Override
    public String readInput() {
        return sc.nextLine();
    }

    /**
     * Writes a line of output to the user.
     * @param output the string to be outputted.
     */
    @Override
    public void writeOutput(String output) {
        System.out.println(output);
    }
}
