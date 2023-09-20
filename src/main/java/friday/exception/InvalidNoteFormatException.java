package friday.exception;

/**
 * Represents an exception when an incorrect note command format is encountered in the Friday application.
 */
public class InvalidNoteFormatException extends InvalidCommandException {
    /**
     * Constructs a new InvalidNoteFormatException with the specified detail message.
     *
     * @param message The detail message.
     */
    public InvalidNoteFormatException(String message) {
        super(message);
    }
}

