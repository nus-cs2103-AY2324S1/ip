/**
 * Encapsulates a DukeInvalidTimeException. Raised when the user provides an invalid date.
 *
 * @author Tan Kerway
 */
public class DukeInvalidTimeException extends DukeException {
    // description string for invalid time given
    private final String description;

    /**
     * Constructor for the exception class.
     *
     * @author Tan Kerway
     */
    public DukeInvalidTimeException(String details) {
        super();
        this.description = " Please specify a valid " + details + " date!";
    }

    /**
     * Returns the String representation of a DukeInvalidTimeException class.
     *
     * @author Tan Kerway
     * @return the String representation of a DukeInvalidTimeException class
     */
    @Override
    public String toString() {
        return super.toString() + this.description;
    }
}
