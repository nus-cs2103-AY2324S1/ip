package seedu.duke.exceptions;

/**
 * The InvalidTaskException is thrown when a command is invalid.
 */
public class InvalidTaskException extends LemonException {
    /**
     * Constructor of InvalidTaskException with the invalid command by user's input.
     * @param message the invalid command input.
     */
    public InvalidTaskException(String message) {
        super(":( OPPS!!! I'm sorry, but I don't know what"
                + message + "means :-( \n Please add todo/ deadline "
                + "/ event before your task description~ or other commands like mark, unmark & delete");
    }

}
