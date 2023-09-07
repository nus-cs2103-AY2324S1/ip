package Exceptions;

public class InvalidTaskException extends DukeException {
    public InvalidTaskException() {
        super("Invalid Task Id entered, see 'list' for the list of tasks");
    }
}
