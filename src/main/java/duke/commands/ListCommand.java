package duke.commands;

import duke.TaskList;
import duke.Ui;

/**
 * Lists items from the application's task list.
 */
public class ListCommand implements Command {
    /**
     * Lists all the items in the application's task list.
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

