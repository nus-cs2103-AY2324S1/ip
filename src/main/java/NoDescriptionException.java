/**
 * This exception is a subclass of the AdamException and is used when a user
 * didnt input the description of a task
 */
public class NoDescriptionException extends AdamException{
    public NoDescriptionException(){}
    public NoDescriptionException(String message) {
        super(message);
    }
    public NoDescriptionException(Throwable cause) {
        super(cause);
    }

    public NoDescriptionException(String message, Throwable cause) {
        super(message,cause);
    }
    public String getInfo() {
        return "OOPS!!! You need to add a description to these tasks";
    }
}
