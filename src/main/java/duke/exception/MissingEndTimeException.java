package duke.exception;

public class MissingEndTimeException extends MissingInformationException {
    public MissingEndTimeException() {
        super("☹ OOPS!!! end time is required for task to be created. Denote end time with a /to.");
    }
}
