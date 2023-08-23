/**
 * Encapsulates a DukeEmptyInputException class. Raised when the user provides a command
 * without a task.
 *
 * @author Tan Kerway
 */
public class DukeEmptyInputException extends DukeException {
    // description of the issue
    private final String issue;

    /**
     * Constructor for the exception class.
     *
     * @author Tan Kerway
     * @param command the command that the user typed in
     */
    public DukeEmptyInputException(String command) {
        super();
        this.issue = "The description of a " + command + " cannot be empty.";
    }

    /**
     * Returns the string representation of a DukeEmptyInputException class.
     *
     * @author Tan Kerway
     * @return the string representation of a DukeEmptyInputException class
     */
    @Override
    public String toString() {
        return super.toString() + " " + this.issue;
    }
}
