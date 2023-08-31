package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand implements Command {

    /**
     * Executes the command to exit the application.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The user interface to handle the application exit.
     * @param storage The storage (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit(); // Instruct the user interface to exit the application
    }
}
