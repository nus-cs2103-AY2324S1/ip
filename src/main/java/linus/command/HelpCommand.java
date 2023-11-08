package linus.command;

import linus.exception.LinusException;
import linus.util.Ui;

/**
 * Represents command to print help message.
 */
public class HelpCommand extends Command {
    private Ui ui = null;

    /**
     * Constructs a command to print help message.
     * @param ui
     */
    public HelpCommand(Ui ui) {
        this.ui = ui;
    }

    /**
     * Prints the help message.
     * @throws LinusException
     */
    @Override
    public void execute() throws LinusException {
        ui.printHelpMessage();
    }
}
