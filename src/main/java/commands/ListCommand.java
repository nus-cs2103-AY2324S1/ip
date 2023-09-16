package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Constructs a new ListCommand.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Executes the list command to display all tasks.
     *
     * @param taskList The list of tasks.
     * @param ui       The user interface to display messages.
     * @param storage  The storage to save the task list.
     * @return The list of tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showTaskList(taskList);
    }
}
