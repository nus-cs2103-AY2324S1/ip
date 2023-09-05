package thea;

/**
 * Represents an exception to use when data loaded is not in expected format.
 */
public class FileCorruptedException extends Exception {
    /**
     * Constructs a new FileCorruptedException object.
     *
     * @param errorMessage errorMessage of the exception.
     */
    public FileCorruptedException(String errorMessage) {
        super(errorMessage);
    }
}
