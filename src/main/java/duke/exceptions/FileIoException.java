package duke.exceptions;

/**
 * Represents an exception that is thrown when there's an error related to file input/output operations.
 */
public class FileIoException extends Exception {


    /**
     * Constructs a new FileIoException with a specific cause or reason for the error.
     *
     * @param error Detailed information about the specific file I/O error encountered.
     */
    public FileIoException(String error) {
        super(ErrorMessages.FILEIO_ERROR + error);
    }
}
