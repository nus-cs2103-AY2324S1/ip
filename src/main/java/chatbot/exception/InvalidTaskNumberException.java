package chatbot.exception;

public class InvalidTaskNumberException extends Exception {
    public InvalidTaskNumberException(int index){
        super("OOPS!!! There is no task " + index + "!");
    }
}
