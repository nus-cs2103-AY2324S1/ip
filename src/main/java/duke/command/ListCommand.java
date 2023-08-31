package duke.command;

import duke.tasks.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Represents a command to list all tasks in the application.
 * <p>
 * When executed, this command will display all tasks in the task list to the user.
 * </p>
 */
public class ListCommand extends Command {

    /**
     * Executes the list command, displaying all tasks in the task list to the user.
     *
     * @param tasks List of tasks to be displayed.
     * @param ui User interface.
     * @param storage Storage system (not used in this command but retained for consistency).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.listTasks();
    }

    /**
     * Specifies that executing a ListCommand will not cause the application to exit.
     *
     * @return {@code false} indicating the application should continue running.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
