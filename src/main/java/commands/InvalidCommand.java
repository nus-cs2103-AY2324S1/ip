package commands;

import io.Storage;
import tasks.TaskList;
import ui.Ui;


/**
 * Represents a command to be executed that is invalid.
 */
public class InvalidCommand extends Command {
    /**
     * Executes the InvalidCommand, showing an error message.
     * @param taskList The TaskList object that stores the list of tasks
     * @param ui The Ui object that handles the user interface
     * @param storage The Storage object that handles the saving and loading of tasks
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showInvalidCommandMessage();
    }
}
