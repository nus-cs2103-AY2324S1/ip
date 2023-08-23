/**
 * Encapsulates a DukeInvalidIndexException class. Raised when invalid index is provided.
 *
 * @author Tan Kerway
 */
public class DukeInvalidIndexException extends DukeException {
    // description
    private final String details;

    /**
     * Constructor for the DukeInvalidIndexException exception.
     *
     * @author Tan Kerway
     */
    public DukeInvalidIndexException() {
        super();
        this.details = " Please enter a valid index!";
    }

    /**
     * Returns the String representation of an invalid index.
     *
     * @author Tan Kerway
     * @return the String representation of an invalid index
     */
    @Override
    public String toString() {
        return super.toString() + this.details;
    }
}
