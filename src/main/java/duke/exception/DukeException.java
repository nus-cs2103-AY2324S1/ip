package duke.exception;

import java.lang.Exception;
/**
 * Represents the exceptions for Duke application.
 *
 * @author Angky Akdi Frandy Putrakelana
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException with a specified message.
     *
     * @param message A message describing the error.
     */
    public DukeException(String message) {
        super(message);
    }
}
