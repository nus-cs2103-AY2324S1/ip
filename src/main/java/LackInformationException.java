/**
 * An exception that arises when the information provided is insufficient.
 */
public class LackInformationException extends DukeException{
    /**
     * Creates an LackInformationException instance.
     *
     * @param s Message of the exception.
     */
    public LackInformationException(String s) {
        super("Please provide information for: " + s);
    }
}
