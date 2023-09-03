package skye.data.exception;

/**
 * Represents the various types of exceptions that appear in the program.
 * It contains the error message for each type.
 */
public enum DukeExceptionType {

    DEADLINE_NO_DESCRIPTION(
    "The description of a deadline cannot be empty.\n"
            + "Correct usage: deadline <description> /by <date>"
    ),
    EVENT_NO_DESCRIPTION(
    "The description of an event cannot be empty.\n"
            + "Correct usage: event <description> /from <start> /to <end>"
    ),
    TODO_NO_DESCRIPTION(
    "The description of a todo cannot be empty.\n"
            + "Correct usage: todo <description>"
    ),
    DUE_NO_DATE(
    "Missing due date\n"
            + "Correct usage: due <dd-mm-yyyy>"
    ),
    FIND_NO_KEYWORD("No keyword specified for finding tasks."),
    INVALID_DEADLINE_FORMAT(
    "Invalid deadline format!\n"
            + "Correct usage: deadline <description> /by <dd-mm-yyyy hh:mm>"
    ),
    INVALID_DEADLINE_SAVE_FORMAT(
            "Invalid deadline save format!\n"
            + "Example: D | 0 | book report | 04-09-2023 23:59"
    ),
    INVALID_EVENT_FORMAT(
    "Invalid event format!\n"
            + "Correct usage: event <description> /from <dd-mm-yyyy hh:mm> /to <dd-mm-yyyy hh:mm>"
    ),
    INVALID_EVENT_SAVE_FORMAT(
    "Invalid event save format!\n"
            + "E | 1 | Meeting | 01-09-2023 08:00 | 01-09-2023 11:00"
    ),
    INVALID_EVENT_ARGUMENT("Invalid Argument: /from date time cannot be after /to date time"),
    INVALID_TODO_SAVE_FORMAT(
    "Invalid event save format!\n"
            + "T | 0 | read book"
    ),
    INVALID_RANGE("The task number you've entered is out of the valid range."),
    NO_TASK_NUMBER("Please enter the task number."),
    TASK_ALREADY_MARKED("The task number you've entered is already marked as complete."),
    TASK_ALREADY_UNMARKED("The task number you've entered is already unmarked."),
    UNKNOWN_COMMAND("I'm sorry, I don't know what that means :-(\n"
            + "Try typing 'help' to see a list of available commands."),
    UNKNOWN_TASK_TYPE("Unknown task type");

    private final String message;

    /**
     * Initializes a type of exception that can occur in the program.
     *
     * @param message Error message
     */
    DukeExceptionType(String message) {
        this.message = message;
    }

    /**
     * Gets the error message
     *
     * @return Error message
     */
    public String getMessage() {
        return message;
    }
}