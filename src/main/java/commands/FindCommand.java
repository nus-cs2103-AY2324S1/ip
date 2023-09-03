package commands;

import exceptions.DukeException;
import io.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command to be executed that finds tasks with a keyword.
 */
public class FindCommand extends Command {
    /**
     * Constructor for FindCommand.
     */
    public FindCommand(String command) {
        super(command);
    }

    /**
     * Executes the FindCommand, finding tasks with a keyword.
     * @param taskList The TaskList object that stores the list of tasks
     * @param ui The Ui object that handles the user interface
     * @param storage The Storage object that handles the saving and loading of tasks
     * @throws DukeException If the command is invalid
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String input = getCommand();
        String[] inputArray = input.split(" ");
        String keyword = inputArray[1];
        String output = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getDescription().contains(keyword)) {
                output += (i + 1) + ". " + task.toString() + "\n";
            }
        }
        ui.showFind(output);
    }
}
