package duke.exception;

import java.lang.Exception;

/**
 * Represents the exceptions for the Duke Chat bot.
 *
 * @author Armando Jovan Kusuma
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException with the specified message.
     */
    public DukeException(String message) {
        super(message);
    }


    /**
     * Returns the string representation of the DukeException.
     *
     * @return String representation of the DukeException.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
