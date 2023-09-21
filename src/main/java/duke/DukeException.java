package duke;

/**
 * Various exceptions related to Duke.
 */
public class DukeException extends RuntimeException {
    public static final String CORRUPTED_FILE = "Please start over as your files are corrupted.";
    public static final String EMPTY = "Print commands cannot have anything after it!";
    public static final String NO_SUCH_TASK = "This task does not exist!";
    public static final String EMPTY_DESCRIPTION = "The input after %s cannot be empty!";
    public static final String UNKNOWN = "I'm sorry, but I don't know what that means :-(";
    public static final String WRONG_DATETIME_FORMAT = "Sorry, please input the datetime in the format dd-MM-yyyy HHmm";
    public static final String INVALID_INDEX = "Sorry, please input a valid index (ie. a number)!";
    private static final String OOPS = "Oopssss!";

    public DukeException(String message) {
        super(String.format("%s %s", OOPS, message));
    }
}
