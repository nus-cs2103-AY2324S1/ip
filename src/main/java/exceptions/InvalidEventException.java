package exceptions;

public class InvalidEventException extends DukeException {
    public InvalidEventException() {
        super("Missing description/start/end for event task.\n" +
                "Format should be: event <task description> " +
                "/from <start date/time> /to <end date/time>");
    }
}
