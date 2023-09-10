package buddy.commands;

import buddy.TaskList;
import buddy.utils.Storage;
import buddy.utils.Ui;

/**
 * The class represents the command for exiting the current session.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printFarewell();
    }
}
