package Exceptions;

public class InvalidTodoException extends Exception {
    public InvalidTodoException(String message) {
        super(":( OPPS!!! The description of a todo cannot be empty.");
    }
}
