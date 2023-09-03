package duke.main;

import java.util.Scanner;

/**
 * Ui deals with interactions with the user
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Initializes Ui object.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Prints welcome message.
     */
    public void showWelcome() {
        showLine();
        System.out.println("     GREETINGS HUMAN! I AM QLATZ! □ \n"
                + "     I AM NOW A LISTMAKER\n");
        showLine();
    }

    /**
     * Prints out message with string wrapper.
     *
     * @param message Message to be printed.
     */
    public void showMessage(String message) {
        System.out.println("     " + message);
    }

    /**
     * Prints out error message with string wrapper.
     *
     * @param error Error message to be printed.
     */
    public void showError(String error) {
        System.err.println("     " + error);
    }

    /**
     * Prints out a line.
     */
    public void showLine() {
        System.out.println("   ----------------------");
    }

    /**
     * Prints out loading error.
     */
    public void showLoadingError() {
        System.out.println("Error loading file, creating an empty list.");
    }

    /**
     * Prints out list of commands.
     */
    public void showCommands() {
        showMessage("Commands: ");
        showMessage("bye, list, mark, unmark, todo, deadline, event");
    }

    /**
     * Reads command from user input.
     *
     * @return User input.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
