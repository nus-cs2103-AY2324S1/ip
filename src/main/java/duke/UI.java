package duke;

import java.util.Scanner;

/**
 * UI class to handle UI methods.
 */
public class UI {

    static Scanner reader;

    /**
     * Constructor for the UI class.
     * Initializes a scanner to read user input.
     */
    public UI() {
        this.reader = new Scanner(System.in);
    }

    /**
     * Reads the next line of user input.
     *
     * @return User input in a string format.
     */
    public String readCommand() {
        String action = reader.nextLine().toString();
        return action;
    }

    /**
     * Prints out the welcome statement.
     */
    public void showWelcome() {
        String logo = "YONG";
        System.out.println("Hello I'm " + logo + "\n" +
                "What can I do for you? \n"
        );
    }

    /**
     * Prints out a line to delineate actions.
     */
    public void showLine() {
        System.out.println("_________________________________");
    }

    /**
     * Prints out the error message.
     * @param errorMessage Error message to be printed.
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
