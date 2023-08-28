package teho.exceptions;

/**
 * Represents an exception that is thrown when description of a deadline task is empty.
 */
public class EmptyDeadlineDescriptionException extends TehOException {
    /**
     * Returns an exception message.
     *
     * @return Exception message.
     */
    @Override
    public String toString() {
        return "OOPS!!! The description of a deadline cannot be empty.";
    }
}

