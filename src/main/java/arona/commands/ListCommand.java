package arona.commands;

import arona.task.TaskList;
import arona.ui.Ui;

/**
 * Represents a command to list all tasks. When executed, this command displays
 * the list of tasks to the user interface.
 */
public class ListCommand extends Command {

    /**
     * Initializes a new instance of the ListCommand class with the specified
     * task list and user interface.
     *
     * @param taskList   The task list containing the tasks to be listed.
     * @param ui         The user interface to display the list of tasks.
     */
    public ListCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    /**
     * Executes the "List" command by displaying the list of tasks to the user
     * interface.
     *
     * @return A string message indicating the message in the GUI.
     */
    @Override
    public String execute() {
        return ui.showTaskList(taskList);
    }
}
