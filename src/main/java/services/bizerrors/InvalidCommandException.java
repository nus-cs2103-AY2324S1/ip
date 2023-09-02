package services.bizerrors;

public class InvalidCommandException extends JarvisException {
    public InvalidCommandException(String command) {
        super(String.format("Sorry, sir. Executing this command (%s) is beyond my capabilities.", command));
    }
}
