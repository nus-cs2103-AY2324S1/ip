package jarvis.exception;

/**
 * Represents an exception that occurs when the user does not provide a value for a command.
 */
public class JarvisMissingValueException extends JarvisException {

    /**
     * Creates a new JarvisMissingValueException with the specified value and command.
     *
     * @param value The value that is missing.
     * @param command The command that requires the value.
     */
    public JarvisMissingValueException(String value, String command) {
        super(String.format("OOPS!!! The %s of %s cannot be empty.", value, command));
    }
}
