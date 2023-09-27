package didier.exception;

/**
 * Thrown to indicate that the file has been corrupted as the program can no longer make sense of its
 * contents so the user may need to delete the file for the application to continue working as normal again.
 */
public class FileCorruptedException extends DidierException {

    public FileCorruptedException() {
        super("The task file was corrupted. Please delete the file.");
    }
}
