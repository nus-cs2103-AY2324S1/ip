package error;

public class EkudException extends RuntimeException {
    public EkudException(String message) {
        super("☹ OOPS!!! " + message);
    }
}
