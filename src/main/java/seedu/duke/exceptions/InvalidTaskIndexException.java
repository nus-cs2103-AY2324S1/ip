package seedu.duke.exceptions;

/**
 * The InvalidTaskIndexException is thrown when a mark/ unmark command is called
 * on a task that does not exists.
 */
public class InvalidTaskIndexException extends LemonException {
    public InvalidTaskIndexException(String message) {
        super(":( OPPS!!! There are no tasks associated with this task number!");
    }
}
