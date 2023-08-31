package duke.exceptions;

public class DukeException extends Throwable {
    public DukeException(String message) {

        super(message);
    }

    @Override
    public String getMessage() {

        return String.format("OOPS!!! %s", super.getMessage());
    }
}
