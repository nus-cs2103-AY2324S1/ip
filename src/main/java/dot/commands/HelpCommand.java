package dot.commands;

import dot.errors.DotException;
import dot.ui.Ui;

public class HelpCommand extends Command {
    public HelpCommand() { }

    @Override
    public void execute() throws DotException {
        Ui.displayHelpMessage();
    }
}
