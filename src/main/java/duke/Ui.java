package duke;

import duke.exception.DukeException;

/**
 * This class represents the UI that interacts with the user.
 */
public class Ui {
    private final String line = "____________________________________________________________";

    /**
     * Prints the greeting message.
     */
    public String greet() {
        String output = "Hello! I'm Fong!\n";
        output += "What can I do for you?\n";
        return output;
    }

    /**
     * Prints the goodbye message.
     */
    public String bye() {
        String output = "Bye. Hope to see you again soon!";
        return output;
    }

    /**
     * Prints the exception message.
     */
    public String explainException(DukeException e) {
        return e.getMessage();
    }

    /**
     * Prints the invalid task message.
     */
    public String handleInvalid() {
        String output = "You did not enter a valid command.";
        return output;
    }
}
