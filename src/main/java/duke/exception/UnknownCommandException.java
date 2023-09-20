package duke.exception;

public class UnknownCommandException extends Exception {
    /**
     * Constructs an UnknownCommandException object which happens when user enters an invalid command.
     */
    public UnknownCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
