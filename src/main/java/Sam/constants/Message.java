package sam.constants;

/**
 * Container for user visible messages.
 */
public class Message {

//    Message for Start
    public static final String WELCOME = "Hello! I'm Sam, your personal AI chatbot, ready to serve you today\n" +
            "What can I do for you?";

//    Messages for response to commands
    public static final String BYE = "Bye. Hope to see you again soon!\nRemember, the universe is vast, but " +
            "I'm always here for you :D";
    public static final String LIST_TASKS = "Here are the tasks in your list:";
    public static final String ADD_TASKS = "Got it. I've added this task:";
    public static final String MARK_TASK = "Nice! I've marked this task as done:";
    public static final String DELETE_TASK = "Noted. I've removed this task:";
    public static final String UNMARK_TASK = "OK, I've marked this task as not done yet:";

//    Messages for errors
    public static final String ERROR = "☹ OOPS!!!";
    public static final String TASK_LIST_EMPTY = "The task list is empty.";
    public static final String TASK_LIST_INVALID_INDEX = "Invalid task index.";
    public static final String INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String CORRUPTED_FILE_ERROR = "Saved data not found due to corruption. \n Corrupted task : ";
    public static final String FAILED_TO_SAVE = "Failed to save ";
    public static final String SEARCH_TASK_SUCCESS = "Here are the matching tasks in your list:";
    public static final String TASK_NOT_FOUND = "No tasks found.";

}
