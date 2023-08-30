package adam.exception;

/**
 * This adam.exception is a subclass of the Exception.AdamException and is used when a user didnt input the /by adam.command
 */
public class DeadlineException extends AdamException {
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
        return "OOPS!!! You need to add one /by adam.command to indicate by when deadline is";
    }
}
