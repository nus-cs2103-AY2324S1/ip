package duke.task;

public class TaskException extends Exception {

    public TaskException(String format) {
        super("Oops! Invalid input for your task.\nValid Format: " + format);
    }
}
