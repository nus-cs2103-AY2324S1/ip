package duke.exceptions;

public class InvalidDeadlineException extends DukeException {
    public InvalidDeadlineException() {
        super("(・´з`・) Uh oh... make sure your deadline has a description and date");
    }
}