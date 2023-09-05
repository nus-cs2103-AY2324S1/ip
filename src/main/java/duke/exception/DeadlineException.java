package duke.exception;

/**
 * Represents an exception that occurs when the format for adding a deadline is incorrect.
 */
public class DeadlineException extends DukeException {
    public DeadlineException() {
        super("The format for adding a deadline is incorrect. Please use: 'deadline [description] /by [yyyy-MM-dd]'");
    }

    /**
     * Constructs a DeadlineException with a specified detail message.
     *
     * @param s The specified detail message.
     */
    public DeadlineException(String s) {
        super("The format for adding a deadline is incorrect. Please use: 'deadline [description] /by [yyyy-MM-dd]'"
                + "\n" + s);
    }
}
