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
     * Creates an instance of {@code JukeInitialisationException}.
     *
     * @param error Error description
     */
    public JukeInitialisationException(String error) {
        super(error);
    }
}
