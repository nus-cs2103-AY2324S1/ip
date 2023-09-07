package duke;

public class OutOfRangeException extends DukeException {
    public OutOfRangeException() {
        super("Please provide a number within range.");
    }
}
