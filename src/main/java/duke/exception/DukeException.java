package duke.exception;

public class DukeException extends Exception {

    public static final String WRONG_DATETIME_MESSAGE = "Wrong DateTime format!! Please use 'dd-MM-yyyy HHmm'.";

    public DukeException() {
        super();
    }

    public DukeException(String errorMsg) {
        super(errorMsg);
    }
}
