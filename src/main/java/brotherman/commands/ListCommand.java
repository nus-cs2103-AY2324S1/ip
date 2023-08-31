package brotherman.commands;

import brotherman.storage.Storage;
import brotherman.tasks.TaskList;
import brotherman.ui.Ui;


/**
 * Represents a command to list the tasks in the task list
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Executes the command to list the tasks in the task list
     * @param taskList Task list to be listed
     * @param ui Ui to show the user the task list
     * @param storage Storage to save the task list
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList);
    }
}
