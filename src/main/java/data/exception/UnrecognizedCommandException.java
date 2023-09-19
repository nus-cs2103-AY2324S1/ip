package data.exception;

/**
 * Custom exception class to indicate
 * that the user has passed in a command
 * that is not recognized.
 */
public class UnrecognizedCommandException extends DukeException {
    public UnrecognizedCommandException(String command) {
        super(new String[] {
            "Unrecognized command: " + command,
            "Maybe create a new TODO with"
                    + " todo read a book"
        });
    }
}
