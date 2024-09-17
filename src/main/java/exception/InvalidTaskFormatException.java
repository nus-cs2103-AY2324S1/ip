package exception;

public class InvalidTaskFormatException extends DukeException {
    public InvalidTaskFormatException() {
        super("Invalid format entered for the given task. See 'help' for the list of valid task formats.");
    }
}

