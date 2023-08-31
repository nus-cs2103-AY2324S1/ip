package duke;

/**
 * Represents the Exception which occurs when a file has been corrupted during loading.
 * This exception is a subclass of {@link FileLoadException}.
 */
public class FileCorruptedException extends FileLoadException {
    /**
     * Constructs a new FileCorruptedException with the specified error message.
     * @param message The error message indicating the cause of the exception.
     */
    public FileCorruptedException(String message) {
        super(message);
    }
}
