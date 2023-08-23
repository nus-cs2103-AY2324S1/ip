/**
 * Encapsulates a DukeInvalidCommandException class. Raised when the user
 * does not provide a valid command to the chatbot.
 *
 * @author Tan Kerway
 */
public class DukeInvalidCommandException extends DukeException {
    // description of the issue
    private final String issue;

    /**
     * Constructor for the exception class.
     *
     * @author Tan Kerway
     */
    public DukeInvalidCommandException() {
        super();
        this.issue = "I'm sorry, but I don't know what that means :3";
    }

    /**
     * Returns the string representation of a DukeInvalidCommandException class.
     *
     * @author Tan Kerway
     * @return the string representation of a DukeInvalidCommandException class
     */
    @Override
    public String toString() {
        return super.toString() + " " + this.issue;
    }
}
