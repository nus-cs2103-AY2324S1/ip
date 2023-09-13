package james;

/**
 * Represents an exception that occurs when loading from a file.
 */
public class LoadingException extends JamesException {

    /**
     * Constructs a new LoadingException with the specified detail message.
     *
     * @param message The detail message.
     */
    public LoadingException(String message) {
        super(message);
    }
}
