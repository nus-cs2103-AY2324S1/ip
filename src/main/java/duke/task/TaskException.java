package duke.task;

public class TaskException extends Exception {
    private static String HORIZONTAL_LINE = "____________________________________________________________\n";

    public TaskException() {
        super(HORIZONTAL_LINE + "You have entered an invalid task number.\n" + HORIZONTAL_LINE);
    }
}
