package Exceptions;

public class NoTaskFoundException extends DukeException {
    public NoTaskFoundException() {
        super("No Task found. Please enter a Task Id after your command");
    }
}
