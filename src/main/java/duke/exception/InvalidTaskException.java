package duke.exception;

public class InvalidTaskException extends DukeException {
    public InvalidTaskException() {
        super("Seems like Bobi cannot find the task you want!");
    }
}
