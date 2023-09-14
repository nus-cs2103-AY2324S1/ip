package dukeexception;

/**
 * An extension of DukeException, that represents an accessible but incorrectly formatted file.
 */
public class CorruptedFileException extends DukeException {
    public CorruptedFileException() {
        super();
    }
    public CorruptedFileException(String s) {
        super(s);
    }
}
