package duke.exception;

/**
 * Represents the exceptions for an Invalid Input of Date.
 *
 * @author Angky Akdi Frandy Putrakelana
 */
public class InvalidTimeException extends DukeException {
    /**
     * Constructs a InvalidTimeException with a specified message.
     *
     * @param message A message describing the error.
     */
    public InvalidTimeException(String message) {
        super(message);
    }
}
