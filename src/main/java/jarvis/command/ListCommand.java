package jarvis.command;

import jarvis.storage.Storage;
import jarvis.tasklist.TaskList;
import jarvis.ui.Ui;
import jarvis.exception.JarvisException;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand.
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks.
     * @param ui The Ui object, for displaying the list of tasks to the user.
     * @param storage The Storage object, not used in this command.
     * @throws JarvisException If there is any error during the command execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JarvisException {
        if (tasks.size() == 0) {
            ui.displayEmptyList();
        } else {
            ui.displayList(tasks);
        }
    }
}