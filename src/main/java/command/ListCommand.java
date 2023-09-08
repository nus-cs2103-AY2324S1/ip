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
     * Executes the command.ListCommand, displaying the list of tasks to the user.
     *
     * @param tasks   The list of tasks to be displayed.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving tasks to a file (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            ui.showEmptyTaskList();
        } else {
            ui.showTaskListHeader();
            for (int i = 0; i < tasks.size(); i++) {
                Task currTask = tasks.getList().get(i);
                ui.showTask(i, currTask);
            }
        }
    }
}
