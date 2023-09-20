package duke.commands;

import duke.storage.Storage;
import duke.tasks.DukeList;
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
    public String execute(DukeList tasks, Ui ui, Storage storage) {
        return ui.exit();
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
