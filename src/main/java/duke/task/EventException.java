package duke.task;

public class EventException extends TaskException {

    public EventException() {
        super("event (description) /from (YYYY-MM-DD) /to (YYYY-MM-DD)");
    }
}
