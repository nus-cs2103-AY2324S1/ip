package duke.exception;

/**
 * Represents a custom exception for the duke.Duke application.
 * Specific types of exceptions should extend this class.
 */
public abstract class DukeException extends Exception {

    /**
     * Default constructor for duke.exception.DukeException.
     */
    public DukeException() {
        super();
    }

    /**
     * Returns the string representation of the duke.exception.
     * It calls the toString() method from the superclass.
     *
     * @return A string representing the duke.exception.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
