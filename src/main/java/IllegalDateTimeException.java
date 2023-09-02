import java.time.DateTimeException;

public class IllegalDateTimeException extends Exception {
    private String msg;

    public IllegalDateTimeException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
