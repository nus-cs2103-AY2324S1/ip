package adam.exception;

/**
 * This adam.exception is a subclass of the Exception.AdamException and is used when a user didnt input the /from and /to adam.command
 */
public class EventsException extends AdamException {
    public EventsException(){}
    public EventsException(String message) {
        super(message);
    }
    public EventsException(Throwable cause) {
        super(cause);
    }

    public EventsException(String message, Throwable cause) {
        super(message,cause);
    }
    public String getInfo() {
        return "OOPS!!! You need to add one /from and one /to adam.command";
    }
}
