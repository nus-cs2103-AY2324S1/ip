package jeo.exception;

/**
 * Represents the exceptions for Je-O chatbot.
 *
 * @author Joseph Oliver Lim
 */
public class JeoException extends Exception {
    /**
     * Constructs a JeoException with a specified message.
     *
     * @param message A message describing the error.
     */
    public JeoException(String message) {
        super(message);
    }

    /**
     * Returns the String representation of the JeoException.
     *
     * @return String representation of the JeoException.
     */
    @Override
    public String toString() {
        return super.getMessage();
    }
}
