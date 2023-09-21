package rua.exception;

/**
 * Handles command with insufficient component (e.g. description, date).
 */
public class EmptyDescriptionException extends Exception {
    private final String type;
    private final String emptyDescriptionErrorMessage;

    /**
     * Constructs an Exception handling the empty description.
     *
     * @param type The type of the task whose description is missing.
     */
    public EmptyDescriptionException(String type) {
        super();
        this.type = type;
        this.emptyDescriptionErrorMessage = " OOPS!!! The description of a "
                + type + " cannot be empty.\n";
    }

    /**
     * Returns the error message of this exception.
     *
     * @return The error message of this exception.
     */
    public String toString() {
        return emptyDescriptionErrorMessage;
    }
}
