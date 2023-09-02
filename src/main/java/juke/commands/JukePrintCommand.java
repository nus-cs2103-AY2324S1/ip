package juke.commands;

import juke.Juke;
import juke.core.JukeObject;
import juke.responses.Response;
import juke.utils.StringUtils;

/**
 * Action that invokes a print action on the command line.
 */
public class JukePrintCommand extends JukeCommand {
    /** Object to print. */
    private final JukeObject printTarget;

    /**
     * Creates an instance of {@code JukePrintCommand}.
     *
     * @param printTarget {@code Object} to print
     */
    public JukePrintCommand(JukeObject printTarget) {
        this.printTarget = printTarget;
    }

    /**
     * Carries out an action when the command is executed.
     *
     * @param response {@code Response} object that contains response from Juke and the user
     * @return {@code Response} object that contains response from Juke and the user
     */
    @Override
    public Response execute(Response response) {
        return response.withJuke(printTarget.toString());
    }
}
