package duke;

import java.util.Scanner;

/**
 * Text UI of the application
 */
public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(" -------------------------------------------------------------------");
    }

    /**
     * Generates and prints the welcome message upon the start of the application
     */
    public void showWelcome() {
        showLine();
        System.out.println("  Hello! I'm Handsome!\n  What can I do for you?");
        showLine();
    }

    /**
     * Reads the next line from the user
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Generates an error message
     */
    public void showError(String errorMessage) {
        System.out.println(" Error: " + errorMessage);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Generates and prints the goodbye message upon exiting the application
     */
    public void showGoodbye() {
        System.out.println("  Bye. Hope to see you again soon!");
    }
}
