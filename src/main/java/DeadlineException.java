public class DeadlineException extends DukeException {
    public DeadlineException() {
        super("The format for adding a deadline is incorrect. Please use: 'deadline [description] /by [yyyy-MM-dd]'");
    }
}
