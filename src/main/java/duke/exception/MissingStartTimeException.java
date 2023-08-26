package duke.exception;

public class MissingStartTimeException extends MissingInformationException {
    public MissingStartTimeException() {
        super("☹ OOPS!!! Start time is required for task to be created. Denote start time with a /from.");
    }
}
