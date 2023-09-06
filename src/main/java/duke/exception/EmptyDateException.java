package duke.exception;

/**
 * The duke.exception.EmptyDateException class represents a custom exception used in the duke.Duke class.
 * It extends the duke.exception.DukeException class.
 * It is thrown when the date of a deadline or event task is empty.
 */
public class EmptyDateException extends DukeException {
    /**
     * Constructor for duke.exception.EmptyDateException class.
     *
     * @param msg The type of task for which the date is left empty.
     */
    public EmptyDateException(String msg) {
        super("☹ OOPS!!! The date of a " + msg + " cannot be empty.");
    }
}
