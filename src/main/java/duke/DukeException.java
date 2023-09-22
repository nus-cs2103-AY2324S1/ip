package duke;

public class DukeException extends RuntimeException {
    private final String errorCode;

    public DukeException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
