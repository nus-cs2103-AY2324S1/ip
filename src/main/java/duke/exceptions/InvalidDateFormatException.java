package duke.exceptions;

/**
 * The <code>InvalidDateFormatException</code> class represents an exception that is
 * thrown when an invalid date format is encountered in Duke's task input. This exception
 * is typically thrown when dates for 'by', 'from', or 'to' fields are not in the
 * expected format: "dd/MM/yyyy HH:mm".
 *
 * @author [Your Name]
 * @version [Version Number]
 */
public class InvalidDateFormatException extends DukeException {

    /**
     * Constructs an <code>InvalidDateFormatException</code> with a default error message.
     * The default error message informs the user about the expected date format.
     */
    public InvalidDateFormatException() {
        super("Invalid date format! Input dates for 'by', 'from', or 'to' should use the following format:"
                + " dd/MM/yyyy HH:mm");
    }
}

