package bob.exception;

/**
 * Exception thrown when reading from a corrupted file.
 */
public class BobCorruptFileException extends BobException {
    /**
     * Constructor for the BobCorruptFileException.
     *
     * @param errorMessage Shown to standard output
     */
    public BobCorruptFileException(String errorMessage) {
        super("Your file may be corrupted :(");
    }
}
