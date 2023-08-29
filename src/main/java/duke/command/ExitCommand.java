package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeStorageException;

/**
 * Represents a command that exits Duke.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command: displays a "Goodbye" message and exits Duke.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.closeAndGoodbyeMessage();
        try {
            storage.saveData(tasks);
        } catch (DukeStorageException e) {
            ui.showErrorMessage(e);
        }
    }

    /**
     * Returns true.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
