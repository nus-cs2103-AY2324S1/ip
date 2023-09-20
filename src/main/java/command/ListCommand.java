package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructs a command.ListCommand.
     */
    public ListCommand() {
        super(null);
    }

    /**
     * Executes the ListCommand, generating a string representation of the list of tasks.
     *
     * @param tasks   The list of tasks to be represented as a string.
     * @param ui      The user interface utility to format task messages.
     * @param storage The storage utility for saving tasks to a file (not used in this command).
     * @return A string representation of the tasks list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder response = new StringBuilder();

        if (tasks.size() == 0) {
            response.append(ui.showEmptyTaskList());
        } else {
            response.append(ui.showTaskListHeader());
            for (int i = 0; i < tasks.size(); i++) {
                Task currTask = tasks.getList().get(i);
                response.append(ui.showTask(i, currTask)).append("\n"); // Assuming each task is on a new line
            }
        }

        return response.toString().trim();  // Trim to remove any trailing newline
    }
}
