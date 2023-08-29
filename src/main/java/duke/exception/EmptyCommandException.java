package duke.exception;

/**
 * Represents an exception that occurs when a user enters an empty command to the chatbot.
 */
public class EmptyCommandException extends DukeException {

    /**
     * Constructs an EmptyCommandException with an error message indicating an empty command.
     */
    public EmptyCommandException() {
        super(" ☹ OOPS!!! Please enter a valid command.");
    }
}
