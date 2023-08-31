package duke.data.exception;

public class DukeException extends Exception {

    public DukeException(String errorMessage) {
        super(errorMessage);
    }

    public DukeException(DukeExceptionType exceptionType) {
        super(exceptionType.getMessage());
    }
}