package dukeexception;

/**
 * An extension of DukeException, that represents an accessible but incorrectly formatted file.
 */
public class CorruptedFileException extends DukeException {
    /**
     * Constructs a CorruptedFileException.
     */
    public CorruptedFileException() {
        super();
    }
    /**
     * Constructs a CorruptedFileException with a message.
     * @param s the associated message.
     */
    public CorruptedFileException(String s) {
        super(s);
    }
}
