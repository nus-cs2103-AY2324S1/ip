/**
 * Encapsulates a DukeEmptyTaskList. Raised when the user wants to delete
 * from an empty tasklist.
 *
 * @author Tan Kerway
 */
public class DukeEmptyTaskListException extends DukeException {
    // details to print to the user
    private final String details;

    /**
     * Constructor for the DukeEmptyTaskList class.
     *
     * @author Tan Kerway
     */
    public DukeEmptyTaskListException() {
        super();
        this.details = " The tasks list is empty!";
    }

    /**
     * Returns the String representation of a DukeEmptyTaskListException.
     *
     * @author Tan Kerway
     */
    @Override
    public String toString() {
        return super.toString() + this.details;
    }
}
