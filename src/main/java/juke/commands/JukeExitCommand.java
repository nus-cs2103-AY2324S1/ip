package juke.commands;

import juke.Juke;
import juke.responses.Response;
import juke.utils.StringUtils;

/**
 * Action that is invoked when the user wishes to quit the assistant.
 */
public class JukeExitCommand extends JukeCommand {
    /** The farewell statement used by the assistant when the user decides to quit the assistant. */
    private static final String EXIT_STRING = "Goodbye!";

    /**
     * Creates an instance of {@code JukeExitCommand}.
     */
    public JukeExitCommand() {}

    /**
     * Carries out an action when the command is executed.
     *
     * @param response {@code Response} object that contains response from Juke and the user
     * @return {@code Response} object that contains response from Juke and the user
     */
    @Override
    public Response execute(Response response) {
        return response.withJuke(
                StringUtils.wrap(JukeExitCommand.EXIT_STRING, Juke.MAX_STRING_LENGTH));
    }
}
