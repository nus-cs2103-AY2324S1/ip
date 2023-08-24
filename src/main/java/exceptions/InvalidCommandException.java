package exceptions;

public class InvalidCommandException extends JarvisException {
    public InvalidCommandException(String message) {
        super("Sorry Master, I'm not 100% sure what that means?");
    }
}
