package sally;


/**
 * Represents a command to list all tasks in the TaskList.
 * Implements the Command interface to provide the execute method.
 */
public class ListCommand implements Command {

    /**
     * Executes the list command by showing all tasks in the TaskList.
     *
     * @param tasks The TaskList containing tasks to be displayed.
     * @param storage The Storage for tasks (not used in this command).
     * @param ui The Ui for user interaction to display tasks.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showTasks(tasks);
    }
}
