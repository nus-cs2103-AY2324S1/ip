package dukeapp;

/**
 * Stores constants of the application, such as string messages.
 */
public class DukeConstants {

    // Regular messages
    public static final String HORIZONTAL_LINE =
            "____________________________________________________________";
    public static final String GREETING_MESSAGE = "\t" +
            HORIZONTAL_LINE +
            "\n\t Hello! I'm ChatNat!\n" +
            "\t What can I do for you?\n\t" +
            HORIZONTAL_LINE;

    public static final String LIST_MESSAGE = "\t" +
            " Here are the tasks in your list:";
    public static final String MARKED_MESSAGE = "\t" +
            " Nice! I've marked this task as done:\n" +
            "\t   %s\n\t" +
            HORIZONTAL_LINE;

    public static final String INSERT_MESSAGE = "\t Got it. I've added this " +
            "task:\n" +
            "\t   %s\n\t Now you have %d %s in the list.\n\t" + HORIZONTAL_LINE;
    public static final String UNMARKED_MESSAGE = "\t" +
            " OK, I've marked this task as not done yet:\n" +
            "\t   %s\n\t" +
            HORIZONTAL_LINE;

    public static final String DELETE_MESSAGE = "\t" +
            " Noted. I've removed this task:\n" +
            "\t   %s\n\t" +
            " Now you have %d tasks in the list.\n\t" +
            HORIZONTAL_LINE;
    public static final String EXIT_MESSAGE =
            "\t Bye. Hope to see you again soon!\n\t" + HORIZONTAL_LINE;

    // Error messages
    public static final String ERROR_MESSAGE =
            "\t ☹ OOPS!!! %s\n\t" + HORIZONTAL_LINE;
    public static final String INSUFFICIENT_ARGUMENTS_ERROR_MESSAGE = "The %s" +
            " of a %s cannot be empty.";
    public static final String UNKNOWN_COMMAND_ERROR_MESSAGE = "I'm sorry, " +
            "but I don't know what that means :-(";
    public static final String FAILED_TO_CREATE_FILE_ERROR_MESSAGE =
            "We couldn't create a new data/duke.txt file.";
    public static final String FAILED_TO_WRITE_FILE_ERROR_MESSAGE =
            "We couldn't write to the data/duke.txt file.";
    public static final String FAILED_TO_CREATE_FOLDER_ERROR_MESSAGE =
            "We couldn't create a new data/duke.txt file.";
    public static final String FILE_NOT_FOUND_ERROR_MESSAGE =
            "We couldn't find the data/duke.txt file.";
    public static final String INVALID_DATE_TIME_FORMAT =
            "\"%s\" is not a valid date time format.";
}
