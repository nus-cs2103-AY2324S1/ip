package juke.commands;

import juke.responses.Dialog;
import juke.responses.Response;

/**
 * Action that is invoked when the user wishes to quit Juke.
 */
public class JukeExitCommand extends JukeCommand {
    /** The farewell statement used by the assistant when the user decides to quit the assistant. */
    private static final String EXIT_STRING = "Goodbye!";

    /**
     * Creates an instance of {@code JukeExitCommand}.
     */
    public JukeExitCommand() {}

    /**
     * Invokes an action when the command is executed.
     *
     * @param response {@code Response} object that contains response from Juke and the user
     * @return {@code Response} object composed with response from Juke or the user
     */
    @Override
    public Response execute(Response response) {
        return response.with(Dialog.ofJuke(JukeExitCommand.EXIT_STRING));
    }
}
