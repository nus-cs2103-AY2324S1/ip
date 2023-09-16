package dukeexception;

/**
 * The Exceptional class for any errors faced by the user inputs.
 */
public class DukeException extends Exception {

    /**
     * Constructs the exception when a method creates it.
     *
     * @param message The message that we want to output to the user.
     */
    public DukeException(String message) {
        super(message);
    }
}
