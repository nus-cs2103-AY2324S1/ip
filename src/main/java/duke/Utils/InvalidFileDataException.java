package duke.utils;

/**
 * The InvalidFileDataException class represents an exception that is thrown when
 * the integrity of file data is compromised or data cannot be parsed properly in the Duke application.
 * It extends the DukeException class and includes a custom error message.
 */
public class InvalidFileDataException extends DukeException {
    /**
     * Constructs a new InvalidFileDataException with a custom error message
     * indicating that the file data integrity is compromised.
     */
    protected InvalidFileDataException() {
        super("File data integrity is compromised.");
    }
}
