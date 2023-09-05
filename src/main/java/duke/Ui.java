package duke;

import java.io.PrintStream;

import exception.DukeException;

/**
 * Handles user interaction with Chat Bot.
 */
public class Ui {

    private static final String NAME = "Obi-wan Kenobi";
    private final PrintStream out;

    /**
     * Constructs a duke.Ui object. duke.Ui object will handle user input and prints
     * information to the user.
     */
    public Ui() {
        this.out = System.out;
    }

    /**
     * Generates and prints welcome message when Chat Bot is started.
     */
    public String startBot() {
        return "Hello There! I am " + NAME + "\nWhat can I do for you?\n";
    }

    /**
     * Generates and prints closing message when Chat Bot is stopped.
     */
    public String endBot() {
        return "Bye. May the force be with you!";
    }

    /**
     * Prints the error messages to the user.
     *
     * @param e If user inputs invalid commands to the Chat Bot.
     */
    public String showErrorMessage(DukeException e) {
        return e.getMessage();
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
