package services.bizerrors;

/**
 * Represents any business error that occurs during the execution of Jarvis.
 */
public class JarvisException extends Exception {
    public JarvisException(String message) {
        super(message);
    }
}
