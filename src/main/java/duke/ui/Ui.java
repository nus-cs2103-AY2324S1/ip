package duke.ui;

import java.util.Scanner;

import duke.exceptions.DukeException;

/**
 * A UI to interact with the user.
 */
public class Ui {

    // Read user input
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Prints the welcome message.
     */
    public static void printWelcomeMessage() {
        System.out.println("Hello! I'm Max!");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints the goodbye message.
     */
    public static void printGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Print the error message of a DukeException.
     *
     * @param e The DukeException whose error message will be printed.
     */
    public static void printErrorMessage(DukeException e) {
        System.out.printf("[ERROR] %s%n", e.getMessage());
    }

    /**
     * Returns the input from the user.
     *
     * @return The input from the user as a String.
     */
    public static String getInput() {
        return sc.nextLine();
    }

    /**
     * Print an arbitrary message.
     *
     * @param message The message to be printed.
     */
    public static void printMessage(String message) {
        System.out.println(message);
    }
}
