package duke.helper;

public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return ":( Oh no! " + super.getMessage();
    }

    @Override
    public String toString()
    {
        return ":( Oh no! " + super.getMessage();
    }
}