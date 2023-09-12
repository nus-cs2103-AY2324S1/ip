package rua.exception;

public class EmptyDescriptionException extends Exception {
    private final String type;
    private final String emptyDescriptionErrorMessage;

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
