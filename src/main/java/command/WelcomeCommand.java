package command;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * Represents a command to print out the welcome message
 * and also load list of tasks from the storage file, or create a storage file if there isn't one.
 * This class is a child of the abstract Command class
 * and contains method to execute the welcome command,
 * including methods to check whether it is an exit command.
 */
public class WelcomeCommand extends Command {

    /**
     * Executes the welcome command.
     * It calls the showWelcome method of the Ui  to print out the welcome message.
     *
     * @param tasks The task list that will store the list of tasks
     *              being loaded from the storage file.
     * @param ui The UI that is used during command execution to show the welcome message.
     * @param storage The storage where the list of tasks are loaded from.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showWelcome();
    }

    /**
     * Specifies that this command is not an exit command.
     *
     * @return false, as this command does not cause the program to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
