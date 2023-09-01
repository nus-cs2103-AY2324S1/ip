package duke.exceptions;

/**
 * Represents the exception thrown when the user inputs an invalid index.
 * Happens when the tasklist is empty or the index is out of bounds
 */
public class InvalidIndexException extends Exception {
    public InvalidIndexException(String Message) {
        super("Invalid index!\n    " + Message);
    }
}
