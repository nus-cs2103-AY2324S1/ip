package dukeapp.commands;

import dukeapp.TaskList;
import dukeapp.Ui;

/**
 * Lists items from application state.
 */
public class ListCommand implements Command {
    /**
     * Lists all the items in the application state.
     *
     * @param input    The user input.
     * @param taskList The application's task list.
     * @param ui       The UI of the application.
     */
    @Override
    public void run(String input, TaskList taskList, Ui ui) {
        String output = taskList.listTasks();
        ui.displayMessage(output);
    }
}

