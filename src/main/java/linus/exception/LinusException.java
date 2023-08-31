package linus.exception;

public class LinusException extends Exception{
    /**
     * Constructs a new LinusException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the Throwable.getMessage() method)
     */
    public LinusException(String message) {
        super(message);
    }
}
