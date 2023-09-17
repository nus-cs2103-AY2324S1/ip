package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to print out the goodbye message
 * and also saves the current list of tasks into the storage file.
 * This class is a child of the abstract Command class
 * and contains method to execute the bye command,
 * including methods to check whether it is an exit command.
 */
public class ByeCommand extends Command {

    /**
     * Executes the bye command.
     * This method  use the UI to display the goodbye message
     * and then saves the current list of tasks into the storage and thereafter exits the program.
     *
     * @param tasks The current list of task to be saved.
     * @param ui The UI which is used during the command execution to show goodbye text.
     * @param storage The storage where list of tasks are stored.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTasksToFile(tasks);
        return ui.showGoodbye();
    }

    /**
     * Specifies that this command is an exit command.
     *
     * @return true, as this command causes the program to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
