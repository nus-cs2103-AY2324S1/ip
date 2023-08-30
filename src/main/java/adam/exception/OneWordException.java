package adam.exception;

/**
 * This adam.exception is a subclass of the Exception.AdamException and is used whena user input something after inputing
 * the one word adam.command like list and bye
 */
public class OneWordException extends AdamException {
    public OneWordException(){}
    public OneWordException(String message){
        super(message);
    }
    public OneWordException(Throwable cause){
        super(cause);
    }

    public OneWordException(String message, Throwable cause){
        super(message,cause);
    }
    public String getInfo() {
        return "OOPS!!! Type in the first word you just entered";
    }
}
