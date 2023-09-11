package duke;

/**
 * Various exceptions related to Duke.
 */
public class DukeException extends RuntimeException {
    public static final String NO_SUCH_TASK = "This task does not exist!";
    public static final String NON_EMPTY = "The description of %s cannot be empty!";
    public static final String UNKNOWN = "I'm sorry, but I don't know what that means :-(";
    public static final String WRONG_DATETIME_FORMAT = "Sorry, please input the datetime in the format yyyy-MM-dd HHmm";
    private static final String OOPS = "Oopssss!";

    public DukeException(String message) {
        super(String.format("%s %s", OOPS, message));
    }
}
