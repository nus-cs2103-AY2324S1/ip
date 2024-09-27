package duke;

/**
 * Exception class for handling unknown commands in Duke.
 */
public class UnknownCommandMessageException extends DukeException {
    /**
     * Constructs a new UnknownCommandMessageException with a default error message.
     */
    public UnknownCommandMessageException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Returns a string representation of this UnknownCommandMessageException, including the error message.
     *
     * @return A string representation of the exception's detail message.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
