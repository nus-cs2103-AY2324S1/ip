package rua.exception;

/**
 * Handles undefined task type entered.
 */
public class InvalidTypeException extends Exception {
    private final String invalidTypeErrorMessage;

    /**
     * Constructs an Exception to handle the tasks with invalid type.
     * @param type The invalid task type.
     */
    public InvalidTypeException(String type) {
        this.invalidTypeErrorMessage = " OOPS!!! I'm sorry, but type "
                + type + " is not supported :-(\n";

    }

    /**
     * Returns the error message of this exception.
     *
     * @return The error message of this exception.
     */
    public String toString() {
        return invalidTypeErrorMessage;
    }
}
