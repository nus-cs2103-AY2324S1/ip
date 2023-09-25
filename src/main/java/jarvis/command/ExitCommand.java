package jarvis.command;

import jarvis.storage.Storage;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the ExitCommand.
     * Displays the farewell message to the user.
     *
     * @param tasks The list of tasks.
     * @param ui The Ui object, for displaying the farewell message to the user.
     * @param storage The Storage object, not used in this command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        isExit = true;
        return ui.farewell();
    }
}
