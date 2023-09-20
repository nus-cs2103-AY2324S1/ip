package anya.messages;

/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String DIVIDER = "____________________________________________________________";

    /** A platform independent line separator. */
    public static final String LS = System.lineSeparator();
    public static final String MESSAGE_GREETING = "Hello! I'm Anya Forger.\n"
                                                + "What can I do for you?\n"
                                                + "(try typing \"help\")";
    public static final String MESSAGE_ERROR_LOADING = "Error loading file.";
    public static final String MESSAGE_ERROR_INIT = "Error initializing Bot.";
    public static final String MESSAGE_EXIT = "Bye! Hope to see you again.";
    public static final String MESSAGE_SUCCESS_LOADING = "Error loading file.";
    public static final String MESSAGE_SUCCESS_ADD = "I've added this task:\n";
    public static final String MESSAGE_SUCCESS_DELETE = "I've deleted this task:\n";
    public static final String MESSAGE_SUCCESS_MARK = "I've marked this task as 'DONE':\n";
    public static final String MESSAGE_SUCCESS_UNMARK = "I've marked this task as 'NOT DONE':\n";
    public static final String MESSAGE_TASK_SIZE = "Now you have tasks.size() tasks in the list!";
    public static final String MESSAGE_TASK_LIST = "Here are the tasks in your list:\n";
    public static final String MESSAGE_TASK_FOUND = "Here are the matching tasks in your list:\n";
    public static final String MESSAGE_TASK_NOT_FOUND = "There are no matching tasks found\n";
    public static final String MESSAGE_ERROR_PREFIX = "Oh no!";
}
