package linus.command;

import linus.exception.LinusException;
import linus.util.Ui;

/**
 * Represents a command to exit the program.
 */
public class ByeCommand extends Command {
    private Ui ui = null;

    /**
     * Constructs a command to exit the program.
     * @param ui
     */
    public ByeCommand(Ui ui) {
        this.ui = ui;
    }

    /**
     * Prints the exit message.
     * @throws LinusException If an error occurs when printing the exit message.
     */
    @Override
    public void execute() throws LinusException {
        ui.printExitMessage();
    }

}

