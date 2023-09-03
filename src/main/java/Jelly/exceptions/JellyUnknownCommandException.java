package Jelly.exceptions;


public class JellyUnknownCommandException extends JellyException {
    public JellyUnknownCommandException() {
        super("Oops! I really don't know what you're saying :(");
    }
}
