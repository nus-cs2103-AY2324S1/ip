package exception;

/**
 * DukeException are Exceptions specific to Duke.
 */
public class DukeException extends Exception {

    /**
     * The constructor of DukeException.
     */
    public DukeException() {
    }

    /**
     * Returns the message of exception.
     *
     * @return The error message of the exception.
     */
    @Override
    public String getMessage() {
        return "☹ OOPS!!! Something went wrong D:";
    }
}
