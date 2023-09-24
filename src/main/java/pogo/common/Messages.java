package pogo.common;

/**
 * Messages displayed to the user.
 */
public class Messages {
    public static final String MESSAGE_INVALID_COMMAND = "Invalid command.";
    public static final String ADD_TASK_SUCCESS = "Got it. I've added this task:\n%1$s";
    public static final String INVALID_TASK = "Please enter a valid task:";
    public static final String INVALID_INDEX = "Please enter a valid index.";
    public static final String TASK_LOAD_SUCCESS = "Loaded %1$s tasks from file.";
    public static final String TASK_LOAD_FAILURE = "Failed to load tasks from file.";
    public static final String STARTUP_MESSAGE = "Hello! I'm Pogo." + System.lineSeparator()
            + "What can I do for you?" + System.lineSeparator();
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String INVALID_DATE_RANGE = "Please enter a valid date range.";
}
