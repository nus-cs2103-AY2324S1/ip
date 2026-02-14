package bruno.exceptions;

/**
 * The BrunoUnknownTaskException handles the case where the user has entered an invalid command.
 */
public class BrunoUnknownTaskException extends BrunoException {

    public BrunoUnknownTaskException() {
        super("I am sorry, I do not understand what you mean.");
    }
}
