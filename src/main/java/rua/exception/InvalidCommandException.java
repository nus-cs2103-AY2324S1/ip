package rua.exception;

public class InvalidCommandException extends Exception {
    private static final String invalidCommandErrorMessage = " OOPS!!! I'm sorry, but I don't know what that means :-(\n";

    /**
     * Returns the error message of this exception.
     *
     * @return The error message of this exception.
     */
    public String toString() {
        return invalidCommandErrorMessage;
    }
}
