package commands;

import errors.DotException;
import ui.Ui;

public class HelpCommand extends Command {
    public HelpCommand() { }

    @Override
    public void execute() throws DotException {
        Ui.displayHelpMessage();
    }
}
