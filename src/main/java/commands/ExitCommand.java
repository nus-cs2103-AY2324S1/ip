package commands;

import data.TaskList;
import storage.Storage;
import ui.Ui;

/**
 * The ExitCommand class.
 * Handles the exit logic when the user enters the exit command.
 */
public class ExitCommand extends Command {
    
    @Override
    public void execute(
            TaskList tasks, Storage storage, Ui ui) {
        ui.displayGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
