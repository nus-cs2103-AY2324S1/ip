package slay.exception;

/**
 * Signals that the input index to retrieve a tong.task.Task is invalid.
 */
public class InvalidTaskIndexException extends IndexOutOfBoundsException {
    public InvalidTaskIndexException(String message) {
        super(message);
    }
}
