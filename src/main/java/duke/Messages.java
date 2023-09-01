package duke;

/**
 * Class storing all the messages of Duke.
 */
public class Messages {
    public static final String MESSAGE_GREETING = "Hello! I'm EnPassant\n"
            + "What can I do for you?\n";
    public static final String MESSAGE_EXIT = "Bye! Hope to see you again soon!\n";
    public static final String MESSAGE_LIST = "Here are the tasks in your list:\n";
    public static final String MESSAGE_NEW_TASK_ADDED = "New task just dropped!\n";
    public static final String MESSAGE_TASK_DELETED = "Task went on vacation, never came back.\n";
    public static final String MESSAGE_TOTAL_TASK_COUNT = "You now have %d tasks in the list!\n";
    public static final String MESSAGE_MARK_DONE = "Great success! I have marked this task as done:\n";
    public static final String MESSAGE_MARK_UNDONE = "Very nice! I have marked this task as not done yet:\n";
    public static final String MESSAGE_INVALID_INDEX = "Great heavens! You have entered an invalid index!\n";
    public static final String MESSAGE_INVALID_INPUT = "Great heavens! You have entered an invalid input!\n"
            + "Here are the valid commands:\n"
            + "  bye\n"
            + "  list\n"
            + "  mark|unmark <index>\n"
            + "  delete <index>\n"
            + "  todo <description>\n"
            + "  deadline <description> /by <date>\n"
            + "  event <description> /from <start> /to <end>\n"
            + "  find <query>\n";
    public static final String MESSAGE_INVALID_MARK = "Great heavens! The index of mark cannot be empty!\n"
            + "Usage: mark <index>\n";
    public static final String MESSAGE_INVALID_UNMARK = "Great heavens! The index of unmark cannot be empty!\n"
            + "Usage: unmark <index>\n";
    public static final String MESSAGE_INVALID_TODO = "Great heavens! The description of todo cannot be empty!\n"
            + "Usage: todo <description>\n";
    public static final String MESSAGE_INVALID_DEADLINE = "Great heavens! Invalid usage of deadline!\n"
            + "Usage: deadline <description> /by <yyyy-mm-dd HHmm> (24h format)\n";
    public static final String MESSAGE_INVALID_EVENT = "Great heavens! Invalid usage of event!\n"
            + "Usage: event <description> /from <start> /to <end>\n";
    public static final String MESSAGE_INVALID_DELETE = "Great heavens! The index of delete cannot be empty!\n"
            + "Usage: delete <index>\n";
    public static final String MESSAGE_INVALID_FIND = "Great heavens! Invalid usage of find!\n"
            + "Usage: find <query>\n";
    public static final String MESSAGE_NO_TASKS_FOUND = "Great heavens! There are no tasks matching your query!\n";
    public static final String MESSAGE_TASKS_FOUND = "Very nice! Here are the tasks matching your query:\n";
    public static final String MESSAGE_CORRUPT_FILE = "Great heavens! The data file is corrupted!\n"
            + "Starting with new task list...\n";
}
