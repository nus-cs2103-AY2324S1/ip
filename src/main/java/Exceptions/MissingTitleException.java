package Exceptions;

/**
 * Custom exception class for missing title entries in the Duke application.
 * This exception is thrown when a user attempts to add a task without providing a title.
 */
public class MissingTitleException extends DukeException {

    /**
     * Constructs a new MissingTitleException with a predefined error message.
     * The error message informs the user about the importance of providing a title for tasks.
     */
    public MissingTitleException() {
        super("I DON'T LIKE WHAT YOU'VE GOT! MISSING TITLE!");
    }
}
