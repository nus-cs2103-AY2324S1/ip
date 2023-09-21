package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command that lists all tasks currently in the task list.
 * When executed, it will return a formatted string that displays all tasks
 * present in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by calling the UI's method to display
     * the list of tasks currently in the task list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The UI object to interact with the user.
     * @param storage The storage object to read/write tasks from/to the data file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTasksList(tasks);
    }
}
