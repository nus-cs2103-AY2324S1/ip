package Exceptions;

public class InvalidEventException extends Exception {
    public InvalidEventException(String message) {
        super(":( OPPS!!! Please add a start and end of the event with /from & /to!");
    }
}
