package duke.exceptions;

/**
 * Custom exception class for empty event entries in the Duke application.
 * This exception is thrown when a user attempts to add an event task without a title, start date, or end date.
 */
public class EmptyEventException extends DukeException {

    /**
     * Constructs a new EmptyEventException with a predefined error message.
     * The error message informs the user about the correct format for adding an event.
     */
    public EmptyEventException() {
        super("I DON'T LIKE WHAT YOU'VE GOT! Missing entry. ENTER event (title) /from (from) /to (to) to"
                + " add an event");
    }
}
