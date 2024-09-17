package duke;

public class EmptyException extends Exception {
    public EmptyException(String message) {
        super("☹ OOPS!!! The description of a " + message + " cannot be empty.");
    }
}
