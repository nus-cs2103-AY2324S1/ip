package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command issued by the user to find tasks with a keyword.
 */
public class FindCommand extends Command {
    private final String keyword; // The keyword to search for.

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList filteredTasks = new TaskList();
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().contains(this.keyword)) {
                filteredTasks.add(task);
            }
        }
        return ui.showFilteredTaskList(filteredTasks);
    }
}
