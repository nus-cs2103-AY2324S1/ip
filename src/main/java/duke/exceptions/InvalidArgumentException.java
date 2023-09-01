package duke.exceptions;

public class InvalidArgumentException extends DukeException{
    String message;

    public InvalidArgumentException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + message;
    }
}
