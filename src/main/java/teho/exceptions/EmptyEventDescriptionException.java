package teho.exceptions;

/**
 * Represents an exception that is thrown when description of an event task is empty.
 */
public class EmptyEventDescriptionException extends TehOException {
    /**
     * Returns an exception message.
     *
     * @return Exception message.
     */
    @Override
    public String toString() {
        return "OOPS!!! The description of a event cannot be empty.";
    }
}
