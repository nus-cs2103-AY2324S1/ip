package duke.commands;

import duke.TaskList;
import duke.Ui;

/**
 * Find items from application state.
 */
public class FindCommand implements Command {
    /**
     * Search for tasks from the task list.
     *
     * @param input    The user input.
     * @param taskList The application's task list.
     * @param ui       The UI of the application.
     */
    @Override
    public void run(String input, TaskList taskList, Ui ui) {
        String[] args = input.split(" ", 2);
        String output = taskList.searchTasks(args[1]);
        ui.displayMessage(output);
    }
}
