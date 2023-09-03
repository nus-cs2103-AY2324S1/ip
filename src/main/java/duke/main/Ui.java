package duke.main;

import java.util.Scanner;

/**
 * duke.main.Ui deals with interactions with the user
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);
    public Ui() {}

    /**
     *
     */
    public void showWelcome() {
        showLine();
        System.out.println("     GREETINGS HUMAN! I AM QLATZ! □ \n"
                + "     I AM NOW A LISTMAKER\n");
        showLine();
    }

    public void showMessage(String message) {
        System.out.println("     " + message);
    }

    /**
     *
     * @param error jk
     */
    public void showError(String error) {
        System.err.println("     " + error);
    }

    /**
     *
     */
    public void showLine() {
        System.out.println("   ----------------------");
    }

    /**
     *
     */
    public void showLoadingError() {
        System.out.println("Error loading file, creating an empty list.");
    }

    public void showCommands() {
        showMessage("Commands: ");
        showMessage("bye, list, find, mark, unmark, todo, deadline, event");
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
