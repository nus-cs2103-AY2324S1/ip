package duke.task;

public class ToDoException extends Exception {
    private static String HORIZONTAL_LINE = "____________________________________________________________\n";

    public ToDoException() {
        super(HORIZONTAL_LINE + "Oops! The description of a todo cannot be empty.\n"
                + "Valid Format: todo (description)\n"
                + HORIZONTAL_LINE);
    }
}