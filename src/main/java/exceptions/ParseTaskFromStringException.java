package exceptions;

/**
 * The ParseTaskFromStringException class is a custom exception that is thrown when there is an issue parsing task data from a string.
 */
public class ParseTaskFromStringException extends DukeException {
    String msg;

    /**
     * Constructs a ParseTaskFromStringException with the specified error message.
     *
     * @param msg The error message associated with the exception.
     */
    public ParseTaskFromStringException(String msg) {
        super(msg);
        this.msg = msg;
    }

    /**
     * Returns an error message indicating that saved task data has been corrupted and cannot be parsed from the given string.
     *
     * @return A formatted error message.
     */
    @Override
    public String toString() {
        return "Saved task data has been corrupted. Cannot parse task from: " + this.msg;
    }
}
