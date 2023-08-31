package duke.commands;

import duke.tasks.DukeList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command to exit the application.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage manager.
     */
    @Override
    public void execute(DukeList tasks, Ui ui, Storage storage) {
        ui.exit();
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return True, as this command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
