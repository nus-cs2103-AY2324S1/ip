package functional;

/**
 * The DukeException class encapsulates an exception specific to the duke.Duke application.
 */
public class DukeException extends Exception {
    /**
     * Constructs a new DukeException object with the specified error message.
     *
     * @param message The error message to be displated
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * toString method of DukeException thats prints the error msg
     *
     * @return the error message to be printed
     */
    public String toString() {
        return " ☹ OOPS!!! " + getMessage();
    }
}
