package exceptions;

public class InvalidEventException extends DukeException {
    public InvalidEventException() {
        super("Missing description/start/end for event task.\n"
                + "Format should be: event <task description> "
                + "/from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
    }
}
