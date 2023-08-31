package exceptions;

/**
 * The EmptyDateException class is a custom exception that is thrown when the date for a task (e.g., deadline or event)
 * is found to be empty or missing.
 */
public class EmptyDateException extends DukeException {

    private String msg;

    /**
     * Constructs an EmptyDateException with the specified task type for which the date is empty.
     *
     * @param msg The task type associated with the exception (e.g., "deadline" or "event").
     */
    public EmptyDateException(String msg) {
        super(msg);
        this.msg = msg;
    }

    /**
     * Returns a string representation of the exception message.
     *
     * @return A formatted error message indicating that the date of the specified task type cannot be empty.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! The date of a(n) " + this.msg + " cannot be empty.";
    }
}
