package duke.exceptions;

public class InvalidDeadlineException extends DukeException {
    public InvalidDeadlineException() {
        super("Please enter a deadline with the format '<<message>> /by dd/mm/yyyy HH:mm' format");
    }
}
