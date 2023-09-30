package veneto.exceptions;

public class VenetoStorageException extends VenetoException {
    /**
     * exception that may occur when operate on the Storage
     * @param s the message of the exception
     */
    public VenetoStorageException(String s) {
        super(s);
    }
}
