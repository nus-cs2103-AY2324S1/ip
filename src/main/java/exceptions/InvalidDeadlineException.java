package exceptions;

public class InvalidDeadlineException extends DukeException {
    public InvalidDeadlineException() {
        super("Missing description/end for deadline task.\n" +
                "Format should be: deadline <task description> /by <end date/time>");
    }
}
