package Eddie;

public class MissingFromDateException extends DukeException{
    public MissingFromDateException() {
        super("Please use /from to include start date");
    }
}
