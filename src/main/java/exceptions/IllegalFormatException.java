package exceptions;

/**
 * An Exception to be thrown when the command received by the chatbot has an illegal format
 * @author Anton Tan Hong Zhi
 */
public class IllegalFormatException extends Exception {
    private String msg;

    /**
     * Creates an IllegalFormatException object
     * @param s The message that contains the error
     */
    public IllegalFormatException(String s) {
        msg = s;
    }

    /**
     * Returns the message contained by the Exception.
     * @return a string containing the message
     */
    public String toString() {
        return msg;
    }
}
