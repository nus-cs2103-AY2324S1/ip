package juke.commands;

import juke.exceptions.JukeException;

/**
 * Action class that corresponds to an error action.
 */
public class JukeExceptionCommand extends JukeCommand {
    /** Error description. */
    private final Exception exception;

    /**
     * Creates an instance of {@code JukeExceptionCommand}.
     *
     * @param exception Error description
     */
    public JukeExceptionCommand(JukeException exception) {
        this.exception = exception;
    }

    /**
     * Carries out an action when the command is executed.
     */
    @Override
    public void execute() {
        System.out.print("Error: " + exception);
    }
}
