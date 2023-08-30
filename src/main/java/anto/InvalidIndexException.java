package anto;

public class InvalidIndexException extends DukeException {
    public InvalidIndexException(String errorMessage) {
        super("anto.InvalidIndexException: " + errorMessage);
    }
}
