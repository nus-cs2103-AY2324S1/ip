package duke.exception;

/**
 * An exception that arises when the information provided is insufficient.
 */
public class LackInformationException extends DukeException {
    /**
     * Creates an duke.exception.LackInformationException instance.
     *
     * @param s Message of the exception.
     */
    public LackInformationException(String s) {
        super("Please provide information for: " + s);
    }
}
