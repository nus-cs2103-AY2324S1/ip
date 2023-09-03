package duke.exception;

/** Exception for invalid file */
public class InvalidFileException extends DukeException {
    /**
     * Initialize Invalid File Exception.
     *
     * @param message Error message to print out.
     */
    public InvalidFileException(String message) {
        super(message);
    }
}
