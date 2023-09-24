package carbonbot.exception;

/**
 * Thrown to indicate an error while writing or reading from the storage.
 */
public class CarbonStorageException extends CarbonException {

    /**
     * Constructs a CarbonStorageException.
     */
    public CarbonStorageException(String details) {
        super("There was an error accessing the storage. " + details);
    }
}
