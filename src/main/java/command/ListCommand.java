package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command that lists all tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand instance.
     */
    public ListCommand() {
        super(null);
    }

    /**
     * Executes the ListCommand by generating a formatted string of all tasks.
     *
     * @param tasks   The collection of tasks to be displayed.
     * @param ui      The user interface utility for formatting messages.
     * @param storage The storage mechanism for tasks. Not utilized in the context of the ListCommand.
     * @return A formatted string representing the list of tasks.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder response = new StringBuilder();

        if (tasks.size() == 0) {
            return ui.showEmptyTaskList();  // Early return for clarity
        }

        response.append(ui.showTaskListHeader());
        int index = 1;
        for (Task currTask : tasks.getList()) {
            response.append(ui.showTask(index++, currTask)).append("\n");
        }

        return response.toString().trim();  // Remove any trailing whitespace or newline
    }
}
