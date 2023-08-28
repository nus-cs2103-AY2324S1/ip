package teho.exceptions;

/**
 * Represents an exception that is thrown when description of todo task is empty.
 */
public class EmptyToDoDescriptionException extends TehOException {
    /**
     * Returns an exception message.
     *
     * @return Exception message.
     */
    @Override
    public String toString() {
        return "OOPS!!! The description of a todo cannot be empty.";
    }
}
