package jarvis.exception;

/**
 * Represents an exception that occurs when the user provides an invalid date time format.
 */
public class JarvisWrongDateFormatException extends JarvisException {

    /**
     * Creates a new JarvisWrongDateFormatException.
     */
    public JarvisWrongDateFormatException() {
        super("Please use the correct date time format: d/M/yyyy HHmm.");
    }
}
