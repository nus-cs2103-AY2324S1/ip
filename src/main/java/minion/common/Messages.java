package minion.common;

/**
 * Represents the messages that the chatbot displays to the user.
 */
public class Messages {
    // General messages
    public static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_WELCOME = "Hello! I'm Minion!\n" + "What can I do for you?";

    // File error
    public static final String MESSAGE_FILE_NOT_FOUND = "Unable to locate the file at /data/tasks.txt. "
            + "Creating a new one now.";

    // general command errors
    public static final String MESSAGE_MISSING_COMMAND = ":( OOPS!!! I'm sorry, please input a legit command. :-(";
    public static final String MESSAGE_NO_UNDERSTAND = ":( OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String MESSAGE_IO_EXCEPTION = "IO exception occurred. Please try again.";

    // todo
    public static final String MESSAGE_TODO_DESCRIPTION_ERROR = ":( OOPS!!! The description of a todo cannot be empty.";

    // deadline
    public static final String MESSAGE_DEADLINE_DESCRIPTION_ERROR = ":( OOPS!!! The description of a deadline "
        + "cannot be empty.";
    public static final String MESSAGE_DEADLINE_BY_ERROR = ":( OOPS!!! The date of a deadline cannot be empty.";

    // event
    public static final String MESSAGE_EVENT_DESCRIPTION_ERROR = ":( OOPS!!! The description of an event cannot"
            + "be empty.";
    public static final String MESSAGE_EVENT_FROM_ERROR = ":( OOPS!!! The from date of an event cannot be empty.";
    public static final String MESSAGE_EVENT_TO_ERROR = ":( OOPS!!! The to date of an event cannot be empty.";

    // parse failures
    public static final String MESSAGE_FAIL_PARSE_DATETIME = "Unable to parse date. Ensure it is of the form "
            + "dd/mm/yyyy HHmm!";

    // task related error
    public static final String MESSAGE_INVALID_TASK_INDEX = ":( OOPS!!! Please enter a valid task number.";
    public static final String MESSAGE_TASK_NOT_FOUND = ":( OOPS!!! Cannot find what you are looking for!";

    // find
    public static final String MESSAGE_FIND_MISSING_ARG = "Find needs to have an argument. Try again.";
}
