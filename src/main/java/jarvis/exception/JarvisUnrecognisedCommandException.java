package jarvis.exception;

public class JarvisUnrecognisedCommandException extends JarvisException {
    public JarvisUnrecognisedCommandException() {
        super("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
