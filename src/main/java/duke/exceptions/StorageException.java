package duke.exceptions;

public class StorageException extends DukeException {
    String message;

    public StorageException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + message;
    }
}
