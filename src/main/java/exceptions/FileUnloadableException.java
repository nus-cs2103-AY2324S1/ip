package exceptions;

/**
 * The FileUnloadableException class is a custom exception that is thrown when there is an issue with loading or unloading a file.
 */
public class FileUnloadableException extends DukeException {

    /**
     * Constructs a FileUnloadableException with the specified error message.
     *
     * @param msg The error message associated with the exception.
     */
    public FileUnloadableException(String msg) {
        super(msg);
    }
}
