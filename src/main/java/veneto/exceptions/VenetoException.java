package veneto.exceptions;

public class VenetoException extends RuntimeException {
    /**
     * Exception that may occur when Veneto runs
     * @param s the message of the exception
     */
    public VenetoException(String s) {
        super(s);
    }
}
