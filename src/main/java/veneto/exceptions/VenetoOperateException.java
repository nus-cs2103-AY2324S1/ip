package veneto.exceptions;

public class VenetoOperateException extends VenetoException {
    /**
     * exception that may occur when a Command operates
     * @param s the message of the exception
     */
    public VenetoOperateException(String s) {
        super(s);
    }
}
