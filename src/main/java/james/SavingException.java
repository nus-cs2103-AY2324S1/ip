package james;

/**
 * Represents an exception that occurs during saving.
 */
public class SavingException extends JamesException {

    /**
     * Constructs a new SavingException with the specified detail message.
     *
     * @param message The detail message.
     */
    public SavingException(String message) {
        super(message);
    }
}
