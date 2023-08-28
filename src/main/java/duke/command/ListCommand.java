package duke.command;

import duke.tasks.TaskList;
import duke.util.Ui;
import duke.util.Storage;

/**
 * Represents a duke.command to list all duke.tasks in the application.
 * <p>
 * When executed, this duke.command will display all duke.tasks in the task list to the user.
 * </p>
 */
public class ListCommand extends Command {

    /**
     * Executes the list duke.command, displaying all duke.tasks in the task list to the user.
     *
     * @param tasks List of duke.tasks to be displayed.
     * @param ui User interface.
     * @param storage Storage system (not used in this duke.command but retained for consistency).
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
