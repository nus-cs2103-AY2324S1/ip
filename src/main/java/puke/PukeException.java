package puke;

/**
 * An exception class that represents any invalid formatting encountered by Puke.
 */
public class PukeException extends Exception {
    /**
     * Creates the exception.
     */
    public PukeException() {
        super("Unfortunately, the circumstances preceding this has necessitated that I issue an apology "
                + ", for the input that I have received is unrecognised.");
    }
}
