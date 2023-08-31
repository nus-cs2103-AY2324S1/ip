package adam.exception;

public class DateException extends AdamException {
    public DateException() {
    }
    public  DateException(String message) {
        super(message);
    }
    public  DateException(Throwable cause) {
        super(cause);
    }
    public DateException(String message, Throwable cause) {
        super(message,cause);
    }
    public String getInfo() {
        return "OOPS!!! you need the date to be in a yyyy-mm-dd format";
    }
}
