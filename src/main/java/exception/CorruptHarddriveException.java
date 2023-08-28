package exception;

/**
 * Represents an exception that is thrown when the application encounters corrupted or incorrectly formatted data.
 * <p>
 * This exception indicates that the data read from the storage or hard drive is not in the expected format.
 * </p>
 */
public class CorruptHarddriveException extends Exception {

    /**
     * Constructs a new CorruptHarddriveException with a default error message.
     */
    public CorruptHarddriveException() {
        super("☹ OOPS!!! I'm sorry, but the data is not in the correct format :-(");
    }
}
