package juke.commands;

import juke.exceptions.JukeException;

/**
 * Action class that corresponds to an error action.
 *
 * This class may be deprecated in future use for favour of proper exception handling.
 */
public class JukeExceptionCommand extends JukeCommand {
    /** Error description. */
    private final Exception exception;

    /**
     * Constructor to create an Error Action.
     * @param exception Error description
     */
    public JukeExceptionCommand(JukeException exception) {
        this.exception = exception;
    }

    /**
     * Necessary method that is invoked when the action is carried out.
     */
    @Override
    public void execute() {
        System.out.print("Error: " + exception);
    }
}
