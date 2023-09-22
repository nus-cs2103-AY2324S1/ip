package duke.exception;
/**
 * Error when given task number is outside tasklist range.
 */
public class InvalidTaskNumberException extends DukeException {
    /**
     * Constructor for an InvalidTaskNumberException.
     * @param listSize current size of the list
     */
    public InvalidTaskNumberException(int listSize) {
        super("Error! Task Number given is outside range of current list size of " + listSize);
    }

}
