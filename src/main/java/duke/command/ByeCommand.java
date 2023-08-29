package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Displays a farewell message and indicates that the application should exit.
 */
public class ByeCommand implements Command {

    /**
     * Executes the command by displaying a farewell message to the user.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving task data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sendMessage("Bye. Hope to see you again soon!");
    }

    /**
     * Does nothing.
     *
     * @param tasks The list of tasks.
     */
    @Override
    public void loadTask(TaskList tasks) {
        //Do nothing
    }

    /**
     * Indicates that the command is an exit command.
     *
     * @return `true` indicating that the application should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
