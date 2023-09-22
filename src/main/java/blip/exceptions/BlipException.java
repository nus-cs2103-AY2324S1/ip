package blip.exceptions;

/**
 * The BlipException class is a generic exception class
 * that is thrown for any invalid actions in the Blip ChatBot.
 */
public class BlipException extends Exception {
    /**
     * Creates an instance of BlipException.
     *
     * @param message The error message
     */
    public BlipException(String message) {
        super(message);
    }
}
