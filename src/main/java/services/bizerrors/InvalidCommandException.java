package services.bizerrors;

/**
 * Represents an exception when the user provides an invalid command.
 */
public class InvalidCommandException extends JarvisException {
    public InvalidCommandException(String command) {
        super(String.format("Sorry, sir. Executing this command (%s) is beyond my capabilities.", command));
    }
}
