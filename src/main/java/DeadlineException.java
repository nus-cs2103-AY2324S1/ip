/**
 * This exception is a subclass of the AdamException and is used when a user didnt input the /by command
 */
public class DeadlineException extends AdamException{
    public DeadlineException() {
    }
    public DeadlineException(String message) {
        super(message);
    }
    public DeadlineException(Throwable cause) {
        super(cause);
    }
    public DeadlineException(String message, Throwable cause) {
        super(message,cause);
    }
    public String getInfo() {
        return "OOPS!!! You need to add one /by command to indicate by when deadline is";
    }
}
