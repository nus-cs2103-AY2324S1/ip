package duke;

import java.io.PrintStream;

import exception.DukeException;

/**
 * Handles user interaction with Chat Bot.
 */
public class Ui {

    private static final String NAME = "Obi-wan Kenobi";

    private static final String DIVIDER = "-----------------------";
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
        String greeting = "Hello There! I am " + NAME + "\nWhat can I do for you?\n";
        out.println(greeting + DIVIDER);
        return greeting;
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
     * Prints error message when writing to file.
     */
    public String showWritingError() {
        String error = "Unable to write to file";
        out.println(error);
        return error;
    }

    /**
     * Prints to user the action of command executed.
     *
     * @param input The description of command executed.
     */
    public void printAction(String input) {
        out.println(input);
        out.println(DIVIDER);
    }

}
