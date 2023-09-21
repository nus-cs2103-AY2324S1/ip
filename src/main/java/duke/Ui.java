package duke;

import java.util.Scanner;

/**
 * Class to handle user interactions
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a new Ui object
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints the welcome message of the bot
     */
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Meep.");
        System.out.println("What can I do for you?");
    }

    /**
     * Reads the user input from the command line
     * @return String containing the user input
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Prints the response to the user input
     * @param message Response to user input
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Prints goodbye message
     */
    public void showGoodbyeMessage() {
        System.out.println("Bye! Hope to see you again soon.");
    }

    public void closeScanner() {
        scanner.close();
    }
}

