package duke.common;

/**
 * Represents a message.
 */
public abstract class Message {
    public static final String GREET = "Hello from Duke!";
    public static final String FAREWELL = "Bye. Hope to see you again soon! \uD83D\uDD19 \uD83D\uDD1B \uD83D\uDD1D";
    public static final String LIST_TASKS = "Here are the tasks in your list:\n";
    public static final String UNMARK_TASK = "OK, I've marked this task as not done yet:\n";
    public static final String MARK_TASK = "Nice! I've marked this task as done:\n";
    public static final String ADD_TASK = "Got it. I've added this task:\n";
    public static final String DELETE_TASK = "Noted. I've removed this task:\n";
    public static final String FIND_TASKS = "Here are the matching tasks in your list:\n";
    public static final String INVALID_COMMAND = "I'm sorry, but I don't know what that means :-(";
    public static final String TASK_STATUS = "\nNow you have %d tasks in the list.";
    public static final String TAG_TASK = "Got it. I've tagged this task:\n";
}
