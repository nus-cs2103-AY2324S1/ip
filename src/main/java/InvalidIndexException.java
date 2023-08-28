public class InvalidIndexException extends DukeException {
    public InvalidIndexException(String errorMessage) {
        super("InvalidIndexException: " + errorMessage);
    }
}
