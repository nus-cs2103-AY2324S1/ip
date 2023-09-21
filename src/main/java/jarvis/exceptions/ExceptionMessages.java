package jarvis.exceptions;

/**
 * Exception Messages Class.
 * Responsible for exception messages.
 *
 * @author Shishir
 */
public class ExceptionMessages {
    public static final String INVALID_COMMAND = "I'm sorry, I couldn't understand that. Please try again!";
    public static final String INVALID_INDEX = "Please enter a valid index!";
    public static final String INVALID_DEADLINE = "Please ensure the entered deadline task is valid!";
    public static final String INVALID_EVENT = "Please ensure the entered event task is valid!";
    public static final String INVALID_DATE = "Please enter the date & time in a valid format! (DD/MM/YY HHMM)";
    public static final String INVALID_RANGE = "Please ensure that the date range is valid!";
    public static final String INVALID_TASK = "Please ensure that the task is valid (Event/Todo/Deadline)";
    public static final String INVALID_MARK = "I'm unable to perform the mark/unmark operation because the task"
            + " is already marked/unmarked!";
    public static final String FILE_DOESNT_EXIST = "The file doesn't exist yet, but will be created under the path (";
    public static final String INVALID_INPUT = "Incorrect input has been detected from the file stored at the path (";

}
