package oreo.exception;

import java.util.NoSuchElementException;

public class IllegalCommandException extends NoSuchElementException {
    private String message;

    /**
     * Constructor for IllegalCommandException.
     *
     * @param message message to include.
     */
    public IllegalCommandException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        String fullMessage = "I don't think I can " + message + "\n"
                + "do you want to try again?";
        return fullMessage;
    }
}
