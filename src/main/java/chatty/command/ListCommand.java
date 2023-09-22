package chatty.command;

import chatty.task.TaskList;
import chatty.utils.Storage;
import chatty.utils.Ui;

/**
 * A class that handles the list command entered by the user to display the current task list.
 * When the user provides a list command, an instance of this class is created
 * to display the list of tasks in the task list.
 */
public class ListCommand extends Command {


    /**
     * Constructor for the ListCommand instance.
     * Initializes the instance without any specific parameters.
     */
    public ListCommand() {
        super(false);
    }

    /**
     * Executes the list command to display the current list of tasks.
     * This method returns a string containing the list of tasks.
     *
     * @param taskList The task list with the current available tasks.
     * @param ui The current user interface for displaying messages.
     * @param storage The storage class (unused in this command).
     * @return A String containing the list of tasks for the user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showList(taskList);
    }
}
