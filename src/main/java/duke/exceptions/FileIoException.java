package duke.exceptions;

/**
 * Represents an exception that is thrown when there's an error related to file input/output operations.
 */
public class FileIoException extends Exception {

    /** The default message that provides more context about the file I/O error. */
    private static final String FILEIO_ERROR_MESSAGE = "OOPS!!! Errors in handling the file ...\n";

    /**
     * Constructs a new FileIoException with a specific cause or reason for the error.
     *
     * @param error Detailed information about the specific file I/O error encountered.
     */
    public FileIoException(String error) {
        super(FILEIO_ERROR_MESSAGE + error);
    }
}
