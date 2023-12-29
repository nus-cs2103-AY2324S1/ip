package juke.commands;

import juke.commons.classes.JukeObject;
import juke.responses.Dialog;
import juke.responses.Response;

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
     * Invokes an action when the command is executed.
     *
     * @param response {@code Response} object that contains response from Juke and the user
     * @return {@code Response} object composed with response from Juke or the user
     */
    @Override
    public Response execute(Response response) {
        return response.with(Dialog.ofJuke(printTarget.toString()));
    }
}
