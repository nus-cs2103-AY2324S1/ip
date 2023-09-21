package duke.exception;

public class DukeException extends Throwable {

    public DukeException(String error) {
        super("\n" + error);
    }
}
