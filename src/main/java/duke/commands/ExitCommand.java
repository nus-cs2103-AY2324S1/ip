package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the ExitCommand Class.
 * Responsible for handling exit operations.
 *
 * @author Shishir
 */
public class ExitCommand extends Command {

    /**
     * Executes the respective command.
     * @param tasks List of all the tasks.
     * @param ui Ui for interacting with the user.
     * @param storage Storage of the tasks.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        ui.leave();
        ui.showLine();
    }

    /**
     * Returns the exit status of the command.
     * @return Exit status of the command.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
