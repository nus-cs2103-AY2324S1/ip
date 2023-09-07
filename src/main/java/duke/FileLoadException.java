package duke;

/**
 * Represents the base exception class for file loading errors.
 * This exception is a subclass of {@link Exception}.
 */
public class FileLoadException extends Exception {
    /**
     * Constructs a new FileLoadException with the specified error message.
     *
     * @param message The error message indicating the cause of the exception.
     */
    public FileLoadException(String message) {
        super(message);
    }
}
