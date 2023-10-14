package sally;

/**
 * Custom exception class for handling exceptions specific to the Sally program.
 */
public class SallyException extends Exception {
    /**
     * Constructs a new SallyException with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public SallyException(String message) {
        super(message);
    }
}
