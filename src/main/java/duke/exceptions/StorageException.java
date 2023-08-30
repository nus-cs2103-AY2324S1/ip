package duke.exceptions;

public class StorageException extends ChatException {
    public StorageException(String message) {
        super("Having trouble accessing the file: " + message + "\n");
    }
}
