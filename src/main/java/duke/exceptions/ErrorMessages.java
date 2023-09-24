package duke.exceptions;

/**
 * Contains constants that represent various error messages which can be shown to the user.
 * This centralized approach ensures consistency and easy modifications of error messages.
 */
public class ErrorMessages {
    public static final String EMPTY_DEADLINE_ERROR = "Oops! A deadline task description is required.";
    public static final String EMPTY_EVENT_ERROR = "Oops! An event task description is required.";
    public static final String EMPTY_TODO_ERROR = "Oops! A todo task description is required.";

    public static final String EMPTY_DESCRIPTION_ERROR = "Oops! Description is mandatory.";

    public static final String INVALID_INDEX_ERROR = "Oops! Please enter an index between [1, %d].";
    public static final String INVALID_TASK_INDEX_ERROR = "Oops! That's not a valid task number. Please enter the right task index.";

    public static final String INVALID_DATE_ERROR = "Oops! That date format isn't correct.";
    public static final String INVALID_DATETIME_ERROR = "Oops! That date-time format isn't right.";
    public static final String INVALID_DEADLINE_FORMAT_ERROR = "Oops! Use the correct deadline format. "
            + "For example: deadline Quiz 1 /by 01/09/2023 2030";
    public static final String INVALID_EVENT_FORMAT_ERROR = "Oops! Use the right event format. "
            + "For example: event meeting /from 01/09/2023 2030 /to 01/09/2023 2230";

    public static final String EMPTY_ERROR = "Oops! You didn't provide any instruction.";
    public static final String STORAGE_ERROR = "Oops! There was an issue accessing the storage list.";
    public static final String TASK_LIST_EMPTY_ERROR = "Oops! You don't have any tasks in your list right now.";
    public static final String UNRECOGNIZED_ERROR = "Sorry, I'm not sure about that command. Type \"help\" for available commands.";
    public static final String ADD_TASKS = "Consider adding more tasks.";
    public static final String INVALID_HELP_COMMAND_ERROR = "Oops! That's not a recognized help command. You can try:\n"
            + "- help\n- help date\n- help time";
    public static final String FILEIO_ERROR = "OOPS!!! Errors in handling the file ...\n";
}

