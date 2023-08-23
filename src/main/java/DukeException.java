/**
 * Encapsulates a general Exception class for Duke.
 *
 * @author Tan Kerway
 */
public class DukeException extends Task {
    // common error description
    private final String description;

    /**
     * Constructor for the DukeException class.
     *
     * @author Tan Kerway
     */
    public DukeException() {
        super("");
        this.description = "OOPS!!!";
    }

    /**
     * Returns the string representation of a DukeException.
     *
     * @author Tan Kerway
     * @return the string representation of a DukeException
     */
    @Override
    public String toString() {
        return this.description;
    }
}
