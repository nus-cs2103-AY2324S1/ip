package duke.exceptions;

/**
 * Represents an exception specific to the chatbot.
 */
public class StorageException extends ChatException {
    public StorageException(String message) {
        super(message);
    }
}
