package juke.commands;

import juke.commons.exceptions.JukeException;
import juke.responses.Dialog;
import juke.responses.Response;

/**
 * Action class that corresponds to an exception is raised.
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
     * Invokes an action when the command is executed.
     *
     * @param response {@code Response} object that contains response from Juke and the user
     * @return {@code Response} object composed with response from Juke or the user
     */
    @Override
    public Response execute(Response response) {
        return response.with(Dialog.ofJuke(exception.toString()));
    }
}
