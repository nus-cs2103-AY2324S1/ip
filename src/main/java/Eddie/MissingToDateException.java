package Eddie;

/**
 * Exception for when user does not provide to date.
 */
public class MissingToDateException extends DukeException {
    public MissingToDateException() {
        super("Please use /to to include an end date");
    }
}
