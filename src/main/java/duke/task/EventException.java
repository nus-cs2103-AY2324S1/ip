package duke.task;

public class EventException extends Exception {
    private static String HORIZONTAL_LINE = "____________________________________________________________\n";

    public EventException() {
        super(HORIZONTAL_LINE + "Oops! Invalid input for your duke.task.Event task.\n"
                + "Valid Format: event (description) /from (date-time) /to (date-time)\n"
                + HORIZONTAL_LINE);
    }
}
