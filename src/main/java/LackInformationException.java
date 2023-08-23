public class LackInformationException extends DukeException{

    public LackInformationException(String s) {
        super("Please provide information for: " + s);
    }
}
