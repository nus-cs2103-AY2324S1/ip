public class InvalidParametersException extends DukeException {
    public InvalidParametersException(String errorMessage) {
        super("InvalidParametersException: " + errorMessage);
    }
}
