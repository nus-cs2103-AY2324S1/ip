package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The SortCategoryCommand is the command for sorting the tasks in the task list by their categories.
 */
public class SortCategoryCommand extends Command {
    /**
     * Executes the sort category command.
     *
     * @param tasks The TaskList to be worked on.
     * @param ui The Ui to be worked on.
     * @param storage The Storage to be worked on.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printList(tasks.sortByCategory().getAllTasks(),
                "Here are the tasks in your list sorted by category:");
    }

    /**
     * Checks whether it is an exit command.
     *
     * @return Returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
