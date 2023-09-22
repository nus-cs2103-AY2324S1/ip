package dude.exception;

/**
 * Exception for error with saving to or reading from disk save file.
 */
public class SaveFileException extends DudeException {
    /**
     * Constructs new SaveFileException.
     *
     * @param message Message specifying what error occurred during saving/loading file.
     */
    public SaveFileException(String message) {
        super(
                message
        );
    }
}
