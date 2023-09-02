package duke.commands;

import duke.Duke;
import duke.service.UiService;

/**
 * Represents a command to exit the Duke application.
 */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand.
     *
     * @param dukeBot   The main Duke instance.
     * @param uiService The UI service for interactions.
     */
    public ExitCommand(Duke dukeBot, UiService uiService) {
        super(dukeBot, uiService);
    }

    /**
     * Executes the command to exit Duke, although the actual exit mechanism
     * is handled outside this class.
     */
    @Override
    public void execute() {
        // will be handled outside of this class.
        return;
    }

    /**
     * Indicates that this command causes the program to exit.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}