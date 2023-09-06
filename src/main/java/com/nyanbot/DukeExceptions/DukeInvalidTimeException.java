package DukeExceptions;

/**
 * Encapsulates a DukeExceptions.DukeInvalidTimeException. Raised when the user provides an invalid date.
 *
 * @author Tan Kerway
 */
public class DukeInvalidTimeException extends DukeException {

    /**
     * Constructor for the exception class.
     *
     * @author Tan Kerway
     * @param details additional details to add to the error message
     */
    public DukeInvalidTimeException(String details) {
        super(" Please specify a valid " + details + " date!");
    }

    /**
     * Returns the String representation of a DukeExceptions.DukeInvalidTimeException class.
     *
     * @author Tan Kerway
     * @return the String representation of a DukeExceptions.DukeInvalidTimeException class
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
