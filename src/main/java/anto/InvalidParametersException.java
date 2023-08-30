package anto;

public class InvalidParametersException extends DukeException {
    public InvalidParametersException(String errorMessage) {
        super("anto.InvalidParametersException: " + errorMessage);
    }
}
