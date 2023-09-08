package duke;

/**
 * Stores the string messages of the application.
 */
public class Messages {

    // Regular messages
    public static final String HORIZONTAL_LINE =
            "____________________________________________________________";
    public static final String GREETING_MESSAGE = " Hello! I'm ChatNat!\n What can I do for you?";

    public static final String LIST_MESSAGE = " Here are the tasks in your list:";

    public static final String FIND_MESSAGE = " Here are the matching tasks in your list:";

    public static final String INSERT_MESSAGE = " Got it. I've added this task:\n   %s\n Now you "
            + "have %d %s in the list.";
    public static final String MARKED_MESSAGE = " Nice! I've marked this task as done:\n   %s";


    public static final String UNMARKED_MESSAGE = " OK, I've marked this task as not done yet:\n "
            + "  %s";

    public static final String DELETE_MESSAGE = " Noted. I've removed this task:\n   %s\n Now you"
            + " have %d tasks in the list.";
    public static final String EXIT_MESSAGE = " Bye. Hope to see you again soon!";

    // Error messages
    public static final String ERROR_PREFIX = " ☹ OOPS!!! %s";
    public static final String INSUFFICIENT_ARGUMENTS_ERROR_MESSAGE = "The %s of a %s cannot be "
            + "empty.";
    public static final String UNKNOWN_COMMAND_ERROR_MESSAGE = "I'm sorry, but I don't know what "
            + "that means :-(";
    public static final String FAILED_TO_CREATE_FILE_ERROR_MESSAGE = "We couldn't create a new "
            + "data/duke.txt file.";
    public static final String FAILED_TO_WRITE_FILE_ERROR_MESSAGE = "We couldn't write to the "
            + "data/duke.txt file.";
    public static final String FAILED_TO_CREATE_FOLDER_ERROR_MESSAGE = "We couldn't create a new "
            + "data/duke.txt file.";
    public static final String INVALID_DATE_TIME_FORMAT = "\"%s\" is not a valid date time format.";
}
