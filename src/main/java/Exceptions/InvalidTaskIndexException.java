package Exceptions;

public class InvalidTaskIndexException extends Exception {
    public InvalidTaskIndexException(String message) {
        super(":( OPPS!!! There are no tasks associated with this task number!");
    }
}
