package duke.templates;

/**
 * Represents the MessageTemplates.
 */
public class MessageTemplates {
    public static final String MESSAGE_LINE = "__________________________________________________________________";

    // menu
    public static final String[] MESSAGE_MENU = new String[] {
        MessageTemplates.MESSAGE_INVALID_COMMAND,
        "Here are the list of commands:",
        "list: shows list of tasks",
        "mark {task number}: mark task as done",
        "unmark {task number}: unmark task as not done",
        "todo {task name}: create a task",
        "deadline {task name} /by {deadline}: create task with deadline",
        "event {event name} /from {start date} /to {end date}: create event",
        "delete {task number}: delete a task",
        "find {keyword}: find tasks with keyword",
        "bye: close application"
    };

    // errors
    public static final String MESSAGE_INVALID_DATETIME = "Invalid DateTime format. Example: 2023-01-01 1200";
    public static final String MESSAGE_INVALID_EVENT_PERIOD = "Invalid event duration. /from must be before /to";
    public static final String MESSAGE_INVALID_TODO = "☹ OOPS!!! The description of a todo cannot be empty.";
    public static final String MESSAGE_INVALID_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    public static final String MESSAGE_INVALID_INDEX = "Task number is not in range!";
    public static final String MESSAGE_FILE_NOT_FOUND = "File not found at data/duke.txt. Creating a new file.";
    public static final String MESSAGE_SAVE_DATA_ERROR = "Unable to save data to file.";

    // notifications
    public static final String MESSAGE_ADDED_TASK = "Got it. I've added this task:";
    public static final String MESSAGE_REMOVED_TASK = "Noted. I've removed this task:";
    public static final String MESSAGE_NUMBER_OF_TASKS = "Now you have %d tasks in the list.";
    public static final String MESSAGE_MARK_DONE = "Nice! I've marked this task as done:";
    public static final String MESSAGE_MARK_NOT_DONE = "OK, I've marked this task as not done yet:";
    public static final String MESSAGE_FOUND_TASKS = "Here are the matching tasks in your list:";
    public static final String MESSAGE_HI = "Hello! I'm ChatGPA 5.0\nWhat can I do for you?";
    public static final String MESSAGE_BYE = "Bye. Hope to see you again soon!";
}
