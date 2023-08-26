package duke.exception;

public class MissingDeadlineException extends MissingInformationException {
    public MissingDeadlineException() {
        super("☹ OOPS!!! Deadline is required for task to be created. Denote deadline with a /by.");
    }
}
