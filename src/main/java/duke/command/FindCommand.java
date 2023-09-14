package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to find tasks containing a specific keyword.
 */
public class FindCommand extends Command {

    /** The keyword to search for in task descriptions */
    private final String keyword;

    /**
     * Constructs a FindCommand object with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand. Searches for tasks containing the keyword in the task list
     * and displays the matching tasks or a message if no matching tasks are found.
     *
     * @param tasks   The task list containing tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage object for saving tasks to a file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList matchingTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(keyword)) {
                matchingTasks.addTask(task);
            }
        }

        if (matchingTasks.size() == 0) {
            ui.showNoMatchingTasks();
        } else {
            ui.showMatchingTasks(matchingTasks);
        }
    }

    /**
     * Returns whether the command is an exit command.
     *
     * @return False, as the FindCommand is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
