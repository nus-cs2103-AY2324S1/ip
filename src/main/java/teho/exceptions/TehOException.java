package teho.exceptions;

/**
 * Represents exceptions specific to TehO.
 */
public class TehOException extends Exception {
    /**
     * Returns an exception message.
     *
     * @return Exception message.
     */
    @Override
    public String toString() {
        return "OOPS!!! Something is wrong with TehO!";
    }
}
