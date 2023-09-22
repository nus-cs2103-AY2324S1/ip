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
        return "Ohno! There's something is wrong with TehO!";
    }
}
