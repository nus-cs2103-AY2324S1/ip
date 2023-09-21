package duke;
// if the description of an event is empty, return error message
public class EmptyEventException extends Exception {
    public EmptyEventException() {
        super("OOPS!!! The description of an event cannot be empty.");
    }
}