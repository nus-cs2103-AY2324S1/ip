package duke;

import java.io.PrintStream;
import java.util.Scanner;

import exception.DukeException;

/**
 * Handles user interaction with Chat Bot.
 */
public class Ui {

    private static final String NAME = "Obi-wan Kenobi";
    private static final String DIVIDER = "_____________________________________";
    private final Scanner in;
    private final PrintStream out;

    /**
     * Constructs a duke.Ui object. duke.Ui object will handle user input and prints
     * information to the user.
     */
    public Ui() {
        this.in = new Scanner(System.in);
        this.out = System.out;
    }

    /**
     * Generates and prints welcome message when Chat Bot is started.
     */
    public void startBot() {
        System.out.println("Hello There! I am " + NAME);
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);
    }

    /**
     * Generates and prints closing message when Chat Bot is stopped.
     */
    public void endBot() {
        System.out.println("Bye. May the force be with you!");
    }

    /**
     * Prints the response to the user's request. Prints a description of the
     * action being executed by the user's command.
     *
     * @param response The description of the command executed.
     */
    public void respondUser(String response) {
        out.println(response);
    }

    /**
     * Gets user's input with a Scanner object.
     */
    public String getUserInput() {
        return in.nextLine();
    }

    /**
     * Prints the error messages to the user.
     *
     * @param e If user inputs invalid commands to the Chat Bot.
     */
    public void showErrorMessage(DukeException e) {
        out.println(e.getMessage());
    }

    /**
     * Prints a divider between each user's commands.
     */
    public void showLine() {
        out.println(DIVIDER);
    }

    /**
     * Prints error message when file path is not found.
     */
    public void showLoadingError() {
        out.println("Invalid file path given");
    }

    /**
     * Print error message when writing to file.
     */
    public void showWritingError() {
        out.println("Unable to write to file");
    }

}
