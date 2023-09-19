package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Iterates through the task list and displays the tasks.
 */
public class ListCommand implements Command {
    /**
     * Executes the command by displaying all the tasks in the task list.
     * Displays the matching tasks to the user.
     *
     * @param tasks   The list of tasks to iterate through.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            output.append("\n").append(i + 1).append(".").append(tasks.get(i).toString());
        }
        ui.sendMessage(output.toString());
    }

    /**
     * Does nothing.
     *
     * @param tasks The list of tasks.
     */
    @Override
    public void loadTask(TaskList tasks) {
        //Do nothing
    }

    /**
     * Does nothing.
     *
     * @param tasks The list of tasks.
     * @return The command to be executed.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public Command undoTask(TaskList tasks) throws DukeException {
        return new EmptyCommand();
    }
}
