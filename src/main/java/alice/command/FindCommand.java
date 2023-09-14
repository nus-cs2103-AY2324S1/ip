package alice.command;

import alice.exception.DukeException;
import alice.storage.Storage;
import alice.task.Task;
import alice.task.TaskList;
import alice.ui.Ui;

/**
 * Represents a command issued by the user to find tasks with a keyword.
 */
public class FindCommand extends Command {
    private final String keyword; // The keyword to search for.

    /**
     * Constructs a FindCommand with the given keyword.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     */
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
