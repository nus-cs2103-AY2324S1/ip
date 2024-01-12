package duke.exception;

/**
 * The base abstract class for custom exceptions.
 */
public abstract class DukeException extends Exception {

    /**
     * Returns a common error message for Duke exceptions.
     *
     * @return A String representation of the error message.
     */
    @Override
    public String toString() {
        return "OOPS!!! ";
    }
}
