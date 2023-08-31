package exceptions;

/**
 * The OutOfRangeException class is a custom exception that is thrown when an index or number is out of the valid range.
 */
public class OutOfRangeException extends DukeException {
    String msg;

    /**
     * Constructs an OutOfRangeException with the specified error message.
     *
     * @param msg The error message associated with the exception.
     */
    public OutOfRangeException(String msg) {
        super(msg);
        this.msg = msg;
    }

    /**
     * Returns a user-friendly error message indicating that a number is out of the valid range.
     *
     * @return A formatted error message.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! " + this.msg + " number out of range.";
    }
}
