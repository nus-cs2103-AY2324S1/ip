package duke.task;

public class DeadlineException extends Exception {
    private static String HORIZONTAL_LINE = "____________________________________________________________\n";

    public DeadlineException() {
        super(HORIZONTAL_LINE + "Oops! Invalid input for your duke.task.Deadline task.\n"
                + "Valid Format: deadline (description) /by (date-time)\n"
                + HORIZONTAL_LINE);
    }

}
