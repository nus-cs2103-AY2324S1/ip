package DukeException;

/**
 * An Exceptional class for any errors faced by the user inputs.
 */
public class DukeException extends Exception{

    /**
     * Constructor method that creates the exception.
     *
     * @param message the message that we want to output to the user.
     */
    public DukeException(String message) {
        super(message);
    }
}
