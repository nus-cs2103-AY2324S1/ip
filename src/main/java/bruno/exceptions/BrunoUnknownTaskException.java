package bruno.exceptions;

public class BrunoUnknownTaskException extends BrunoException {
    public BrunoUnknownTaskException() {
        super("I am sorry, I do not understand what you mean.");
    }
}
