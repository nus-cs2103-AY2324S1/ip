package juke.commands;

import juke.Juke;
import juke.exceptions.JukeException;
import juke.responses.Response;
import juke.utils.StringUtils;

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
     *
     * @param response {@code Response} object that contains response from Juke and the user
     * @return {@code Response} object that contains response from Juke and the user
     */
    @Override
    public Response execute(Response response) {
        return response.withJuke(
                StringUtils.wrap(exception.toString(), Juke.MAX_STRING_LENGTH));
    }
}
