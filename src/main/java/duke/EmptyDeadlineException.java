package duke;
// if the description of a deadline is empty, return error message
public class EmptyDeadlineException extends Exception {
    public EmptyDeadlineException() {
        super("OOPS!!! The description of a deadline cannot be empty.");
    }
}
