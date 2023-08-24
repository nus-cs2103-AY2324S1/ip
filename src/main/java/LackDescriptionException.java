public class LackDescriptionException extends DukeException {
    /**
     * Creates a LackDescriptionException instance.
     * @param s Message of the exception.
     */
    public LackDescriptionException(String s) {
        super("The description of "+ s + " should not be empty");
    }
}
