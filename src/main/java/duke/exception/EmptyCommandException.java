package duke.exception;

public class EmptyCommandException extends DukeException {

    public EmptyCommandException() {
        super(" ☹ OOPS!!! Please enter a valid duke.command.");
    }
}
