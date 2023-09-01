package ui;

import java.util.Scanner;

/**
 * User interface class for interaction with users.
 *
 * @author Ho Khee Wei
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object with an initialized scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);

    }

    /**
     * Reads a user command from the console and returns it as a string.
     *
     * @return The user-entered command.
     */
    public String readCommand() {
        System.out.print(">> ");
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Prints a message to the console with indentation for a cleaner user
     * interface.
     *
     * @param message The message to be displayed to the user.
     */
    public void print(String message) {
        System.out.println("    " + message);
    }
}
