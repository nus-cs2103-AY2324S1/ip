package commands;

import exceptions.DukeException;
import io.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command to be executed that loads tasklist from storage.
 */
public class LoadCommand extends Command {
    /**
     * Executes the LoadCommand, loading the tasklist from storage.
     * @param taskList The TaskList object that stores the list of tasks
     * @param ui The Ui object that handles the user interface
     * @param storage The Storage object that handles the saving and loading of tasks
     * @throws DukeException If the command is invalid
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.load(taskList);
        ui.showLoad();
    }
}
