package duke;
public class EmptyEventException extends Exception {
    public EmptyEventException() {
        super("OOPS!!! The description of an event cannot be empty.");
    }
}