package duke.exceptions;

/**
 * Custom exception class for missing "from" date entries in the Duke application.
 * This exception is thrown when a user attempts to add an event task without specifying the start date.
 */
public class MissingFromException extends DukeException {

    /**
     * Constructs a new MissingFromException with a predefined error message.
     * The error message informs the user about the correct format for adding an event.
     */
    public MissingFromException() {
        super("I DON'T LIKE WHAT YOU'VE GOT! Missing event start date. ENTER event (title) /from (from)"
                + " /to (to) to add an event");
    }
}

