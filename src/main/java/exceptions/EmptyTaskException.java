package exceptions;

/**
 * The EmptyTaskException is an exception that is thrown when the description of a task is empty or missing.
 */
public class EmptyTaskException extends DukeException {

    private String msg;

    /**
     * Constructs an EmptyTaskException with the specified task type for which the description is empty.
     *
     * @param msg The task type associated with the exception (e.g., "todo", "deadline", or "event").
     */
    public EmptyTaskException(String msg) {
        super(msg);
        this.msg = msg;
    }

    /**
     * Returns a string representation of the exception message.
     *
     * @return A formatted error message indicating that the description of the specified task type cannot be empty.
     */
    @Override
    public String getMessage() {
        return "OOPS!!! The description of a(n) " + this.msg + " cannot be empty.";
    }
}
