package Errors;

public class InvalidTaskException extends Exception{
    public InvalidTaskException() {
        super("oops. I do not know what task you are referring to.");
    }
}
