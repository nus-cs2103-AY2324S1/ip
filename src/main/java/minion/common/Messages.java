package minion.common;

/**
 * Represents the messages that the chatbot displays to the user.
 */
public class Messages {
    public static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    public static final String[] MESSAGE_WELCOME = new String[]{"Hello! I'm minion.Minion!", "What can I do for you?"};
    public static final String MESSAGE_FILE_NOT_FOUND = "Unable to locate the file at /data/tasks.txt. "
            + "Creating a new one now.";
    public static final String MESSAGE_MISSING_COMMAND = ":( OOPS!!! I'm sorry, please input a legit command. :-(";
    public static final String MESSAGE_NO_UNDERSTAND = ":( OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_TODO_DESCRIPTION_ERROR = ":( OOPS!!! The description of a todo cannot be empty.";
    public static final String MESSAGE_DEADLINE_DESCRIPTION_ERROR = ":( OOPS!!! The description of a deadline "
            + "cannot be empty.";
    public static final String MESSAGE_DEADLINE_BY_ERROR = ":( OOPS!!! The date of a deadline cannot be empty.";
    public static final String MESSAGE_EVENT_DESCRIPTION_ERROR = ":( OOPS!!! The description of an event cannot"
            + "be empty.";
    public static final String MESSAGE_EVENT_FROM_ERROR = ":( OOPS!!! The from date of an event cannot be empty.";
    public static final String MESSAGE_EVENT_TO_ERROR = ":( OOPS!!! The to date of an event cannot be empty.";
    public static final String MESSAGE_FAIL_PARSE_DATE = "Unable to parse date. Ensure it is of the form dd/mm/yyyy!";
    public static final String MESSAGE_FAIL_PARSE_TIME = "Unable to parse time. Ensure it is form HHmm!";
    public static final String MESSAGE_IO_EXCEPTION = "IO exception occurred. Please try again.";
    public static final String MESSAGE_INVALID_TASK_INDEX = ":( OOPS!!! Please enter a valid task number.";
    public static final String MESSAGE_TASK_NOT_FOUND = ":( OOPS!!! Cannot find what you are looking for!";
}
