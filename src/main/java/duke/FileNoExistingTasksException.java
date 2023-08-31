package duke;

/**
 * Represents the exception that indicates there are no existing tasks in a file during loading.
 * This exception is a subclass of {@link FileLoadException}.
 */
public class FileNoExistingTasksException extends FileLoadException {
    /**
     * Constructs a new FileNoExistingTasksException with the specified error message.
     *
     * @param message The error message indicating the cause of the exception.
     */
    public FileNoExistingTasksException(String message) {
        super(message);
    }
}