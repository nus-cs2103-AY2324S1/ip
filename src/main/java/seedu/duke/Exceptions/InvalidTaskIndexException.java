package seedu.duke.Exceptions;

public class InvalidTaskIndexException extends LemonException {
    public InvalidTaskIndexException(String message) {
        super(":( OPPS!!! There are no tasks associated with this task number!");
    }
}
