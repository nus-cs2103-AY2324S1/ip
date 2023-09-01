public class InvalidDeadlineException extends Exception {
    public InvalidDeadlineException() {
        super("OOPS!!! Please specify the description and deadline in the correct format for this Deadline!");
    }
}
