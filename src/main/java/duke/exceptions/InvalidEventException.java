package duke.exceptions;

/**
 * Signals an invalid event due to invalid format given by the user.
 */
public class InvalidEventException extends DukeException {
    /**
     * Public constructor for InvalidEventException
     */
    public InvalidEventException() {
        super("Please enter an event with the format '<<message>> /from dd/mm/yyyy HH:mm /to dd/mm/yyyy HH:mm' format");
    }
}
