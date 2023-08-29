package pogo.common;

/**
 * Messages displayed to the user.
 */
public class Messages {
    public static final String MESSAGE_INVALID_COMMAND = "Invalid command.";
    public static final String ADD_TASK_SUCCESS = "Got it. I've added this task:\n%1$s";
    public static final String INVALID_TASK = "Invalid task: %s";
    public static final String INVALID_INDEX = "Please enter a valid index.";
    public static final String TASK_LOAD_SUCCESS = "Loaded %1$s tasks from file.";
    public static final String TASK_LOAD_FAILURE = "Failed to load tasks from file.";
    public static final String HORIZONTAL_DIVIDER = "-".repeat(40);
    public static final String STARTUP_MESSAGE = HORIZONTAL_DIVIDER + System.lineSeparator()
            + "Hello! I'm Pogo." + System.lineSeparator()
            + "What can I do for you?" + System.lineSeparator()
            + HORIZONTAL_DIVIDER;
    public static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
}
