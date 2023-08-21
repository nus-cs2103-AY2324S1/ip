public class NoDeadlineException extends Exception {
    public NoDeadlineException() {
        super("_________________________________________________\n"
                + " OOPS!! Please provide a deadline for your deadline task.\n"
                + "_________________________________________________\n");
    }
}
