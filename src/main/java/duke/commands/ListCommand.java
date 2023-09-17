package duke.commands;

import duke.io.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to list all tasks in the given task list.
     * If there are no tasks, a message indicating the empty task list will be displayed.
     *
     * @param tasks   The list of tasks to be displayed.
     * @param ui      The user interface used to display the tasks.
     * @param storage The storage used to save tasks. (Not used in this context but present due to inheritance)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            String emptyListMessage = "There are no tasks in your list.";
            ui.addToResponse(emptyListMessage);
            return;
        }

        String returnMessage = "Here are the tasks in your list:\n";
        ui.addToResponse(returnMessage + tasks);
    }
}

