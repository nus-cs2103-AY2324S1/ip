package Exceptions;

public class InvalidTaskException extends Exception {
    public InvalidTaskException(String message) {
        super(":( OPPS!!! I'm sorry, but I don't know what that means :-( \n Please add todo/ deadline / event before your task description~");
    }

}
