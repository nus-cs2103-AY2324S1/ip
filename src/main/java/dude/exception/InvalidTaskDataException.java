package dude.exception;

/**
 * Exception for invalid task data format when loading from disk.
 */
public class InvalidTaskDataException extends DudeException {
    public InvalidTaskDataException() {
        super(
                "Could not read task(s) from saved data.\n"
                        + "The format is possibly corrupted.\n"
                        + "Try checking the file and try again,\n"
                        + "or delete the file to start over."
        );
    }
}
