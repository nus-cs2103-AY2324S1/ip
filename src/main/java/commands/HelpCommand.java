package commands;

import errors.DotException;
import ui.Ui;

/**
 * Command to display the help message.
 */
public class HelpCommand extends Command {
    /**
     * Constructor of HelperCommand, which does nothing.
     */
    public HelpCommand() { }

    /**
     * Displays the help message.
     * @throws DotException On detected error
     */
    @Override
    public void execute() throws DotException {
        Ui.displayHelpMessage();
    }
}
