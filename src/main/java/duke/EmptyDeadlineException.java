package duke;
public class EmptyDeadlineException extends Exception {
    public EmptyDeadlineException() {
        super("OOPS!!! The description of a deadline cannot be empty.");
    }
}
