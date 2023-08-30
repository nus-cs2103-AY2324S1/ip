package duke.task;

public class DeadlineException extends TaskException {

    public DeadlineException() {
        super("Valid Format: deadline (description) /by (YYYY-MM-DD)\n");
    }

}
