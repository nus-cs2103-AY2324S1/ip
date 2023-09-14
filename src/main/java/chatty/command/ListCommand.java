package chatty.command;

import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

/**
 * A class that handle the list command that the user entered
 */
public class ListCommand extends Command {

    /**
     * Contructor for list command
     */
    public ListCommand() {
        super(false);
    }

    /**
     * List out the current list of task
     * @param taskList the tasklist with the current task
     * @param ui the current user interface
     * @param storage helps to update the data when necessary
     * @return the string that list out the task for the user
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showList(taskList);
    }
}
