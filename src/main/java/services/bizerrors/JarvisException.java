package services.bizerrors;

/**
 * Represents any business error that occurs during the execution of Jarvis.
 */
public class JarvisException extends Exception {
    public JarvisException(String message) {
        // Prepend a newline to the message to make it more readable.
        super("\n" + message);
    }
}
