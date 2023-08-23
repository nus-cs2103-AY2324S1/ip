public class InvalidDeadlineException extends Exception {
    public InvalidDeadlineException() {
        super("OOPS!!! Please specify the description and deadline for this Deadline!");
    }
}
