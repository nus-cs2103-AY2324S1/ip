package duke;

/**
 * Class storing all the messages of Duke.
 */
public class Messages {
    public static final String GREETING_MESSAGE = "Hello! I'm EnPassant\n"
            + "What can I do for you?\n";
    public static final String EXIT_MESSAGE = "Bye! Hope to see you again soon!\n";
    public static final String LIST_MESSAGE = "Here are the tasks in your list:\n";
    public static final String NEW_TASK_ADDED_MESSAGE = "New task just dropped!\n";
    public static final String TASK_DELETED_MESSAGE = "Task went on vacation, never came back.\n";
    public static final String TOTAL_TASK_COUNT_MESSAGE = "You now have %d tasks in the list!\n";
    public static final String MARK_DONE_MESSAGE = "Great success! I have marked this task as done:\n";
    public static final String MARK_UNDONE_MESSAGE = "Very nice! I have marked this task as not done yet:\n";
    public static final String INVALID_INDEX_MESSAGE = "Great heavens! You have entered an invalid index!\n";
    public static final String INVALID_INPUT_MESSAGE = "Great heavens! You have entered an invalid input!\n"
            + "Here are the valid commands:\n"
            + "  bye\n"
            + "  list\n"
            + "  mark|unmark <index>\n"
            + "  delete <index>\n"
            + "  todo <description>\n"
            + "  deadline <description> /by <date>\n"
            + "  event <description> /from <start> /to <end>\n";
    public static final String INVALID_MARK_MESSAGE = "Great heavens! The index of mark cannot be empty!\n"
            + "Usage: mark <index>\n";
    public static final String INVALID_UNMARK_MESSAGE = "Great heavens! The index of unmark cannot be empty!\n"
            + "Usage: unmark <index>\n";
    public static final String INVALID_TODO_MESSAGE = "Great heavens! The description of todo cannot be empty!\n"
            + "Usage: todo <description>\n";
    public static final String INVALID_DEADLINE_MESSAGE = "Great heavens! Invalid usage of deadline!\n"
            + "Usage: deadline <description> /by <yyyy-mm-dd HHmm> (24h format)\n";
    public static final String INVALID_EVENT_MESSAGE = "Great heavens! Invalid usage of event!\n"
            + "Usage: event <description> /from <start> /to <end>\n";
    public static final String INVALID_DELETE_MESSAGE = "Great heavens! The index of delete cannot be empty!\n"
            + "Usage: delete <index>\n";
    public static final String CORRUPT_FILE_MESSAGE = "Great heavens! The data file is corrupted!\n"
            + "Starting with new task list...\n";
}
