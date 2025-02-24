package tasket.commons;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final String MESSAGE_WELCOME = "Hello, I'm Tasket\nWhat can I do for you?";
    public static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_ADDED = "Got it. We have added this task:";
    public static final String MESSAGE_LIST = "Here are the tasks in your list:";
    public static final String MESSAGE_TASK_LENGTH = "Now you have %d tasks in the list";
    public static final String MESSAGE_MARKED_TASK = "Nice! I've marked this task as done:";
    public static final String MESSAGE_UNMARKED_TASK = "Ok, I've marked this task as undone:";
    public static final String MESSAGE_DELETED_TASK = "Noted, I've deleted this task:";
    public static final String MESSAGE_MATCHING_TASKS = "Here are the matching tasks in your list:";
    public static final String MESSAGE_ERROR = "OOPS!!! ";

    public static final String MESSAGE_UNKNOWN_COMMAND = "I'm sorry, but I don't know what it means :(";
    public static final String MESSAGE_EMPTY_DESCRIPTION = "The description of %s cannot be empty";
    public static final String MESSAGE_EMPTY_DEADLINE = "The deadline cannot be empty";
    public static final String MESSAGE_EMPTY_START = "The start time cannot be empty";
    public static final String MESSAGE_EMPTY_END = "The end time cannot be empty";
    public static final String MESSAGE_EMPTY_INDEX = "The task index cannot be empty";
    public static final String MESSAGE_LESS_THAN_ONE = "The task index cannot be less than 1";
    public static final String MESSAGE_EXCEEDS_LIST = "The task index cannot exceed the list";
    public static final String MESSAGE_NOT_NUMBER = "The task index must be a number";

    public static final String MESSAGE_FILE_NOT_FOUND = "File not found";
    public static final String MESSAGE_UNABLE_RETRIEVE_TASK =
            "Error occurred while retrieving task. The task will not be loaded";
    public static final String MESSAGE_UNABLE_SAVE_TASK =
            "Error occurred while saving the task. The task will not be saved";
    public static final String MESSAGE_UNABLE_SAVE_FILE = "Error occurred while saving the file";
    public static final String MESSAGE_UNABLE_RETRIEVE_FILE =
            "Unable to retrieve save file. This session will not be saved";


}
