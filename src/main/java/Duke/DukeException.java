package duke;

/**
 * The `DukeException` class represents custom exceptions that can be thrown by the BloopBot application.
 * It extends the base `Exception` class to provide specific error messages for different exceptional situations.
 * This class is used to handle and communicate application-specific errors.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public class DukeException extends Exception {

    /**
     * Constructs a new `DukeException` with the specified error message.
     *
     * @param message The error message associated with this exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
