public class LackDescriptionException extends DukeException {
    public LackDescriptionException(String s) {
        super("The description of "+ s + " should not be empty");
    }
}
