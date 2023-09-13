package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to sort the tasks in the task list.
 */
public class SortCommand extends Command {
    /**
     * Executes the sort command, which sorts the tasks in the task list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The UI for user interaction.
     * @param storage The storage for saving tasks.
     * @return A string message showing the sorted tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.sortDeadlines();
        storage.save(tasks);
        return ui.showSortedTasks(tasks);
    }
}
