package Eddie;

public class MissingToDateException extends DukeException {
    public MissingToDateException() {
        super("Please use /to to include an end date");
    }
}
