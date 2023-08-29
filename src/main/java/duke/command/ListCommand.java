package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command that lists all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Executes the command: displays all tasks in the list of tasks.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.listTasks(tasks);
    }

    /**
     * Returns false.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
