package juke.exceptions;

/**
 * Exception that encapsulates the situation whereby Juke cannot
 * properly instantiate and should therefore exit.
 * <p>
 * All {@code JukeInitialisationExceptions} and subclasses of it should cause
 * the program to exit, as there is no way for the program to continue
 * if it cannot initialise the variables and data it needs to work.
 */
public class JukeInitialisationException extends JukeException {
    /**
     * Constructor to create a {@code JukeInitialisationException}.
     *
     * @param err Error description
     */
    public JukeInitialisationException(String err) {
        super(err);
    }
}
