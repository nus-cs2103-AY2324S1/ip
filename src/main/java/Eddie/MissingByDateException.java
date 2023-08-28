package Eddie;

public class MissingByDateException extends DukeException{
    public MissingByDateException() {
        super("Please use /by to include a due date");
    }
}
