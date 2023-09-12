package rua.exception;

public class InvalidTypeException extends Exception {
    private final String type;
    private final String invalidTypeErrorMessage;

    public InvalidTypeException(String type) {
        this.type = type;
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
