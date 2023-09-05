package dukeExceptions;

/**
 * This Exception class is for when the parser is not able to parse the string.
 */
public class DukeUnknownCommandException extends DukeException {
    public DukeUnknownCommandException(String message) {
        super(message);
    }
}
