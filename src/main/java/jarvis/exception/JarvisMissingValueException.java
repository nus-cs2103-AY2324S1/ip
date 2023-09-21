package jarvis.exception;

public class JarvisMissingValueException extends JarvisException {
    public JarvisMissingValueException(String value, String command) {
        super(String.format("OOPS!!! The %s of %s cannot be empty.", value, command));
    }
}
