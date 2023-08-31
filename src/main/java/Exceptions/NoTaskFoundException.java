package Exceptions;

public class NoTaskFoundException extends DukeException {
    public NoTaskFoundException() {
        super("No Tasks.Task Id found. Please enter a Tasks.Task Id after your command");
    }
}
