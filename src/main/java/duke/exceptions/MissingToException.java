package duke.exceptions;

/**
 * Custom exception class for missing "to" date entries in the Duke application.
 * This exception is thrown when a user attempts to add an event task without specifying the end date.
 */
public class MissingToException extends DukeException {

    /**
     * Constructs a new MissingToException with a predefined error message.
     * The error message informs the user about the correct format for adding an event.
     */
    public MissingToException() {
        super("I DON'T LIKE WHAT YOU'VE GOT! Missing event end date. ENTER event (title) /from (from) /to"
                + " (to) to add an event");
    }
}
