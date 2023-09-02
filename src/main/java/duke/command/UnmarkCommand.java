package duke.command;

import duke.error.DukeException;
import duke.lib.Storage;
import duke.lib.UI;
import duke.task.TaskList;

/**
 * Represents a command to mark a task as not done in the task list.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an UnmarkCommand object with the specified task index.
     *
     * @param index The index of the task to be marked as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command, marking the specified task as not done and updating the task list.
     *
     * @param tasks   The task list containing the tasks.
     * @param ui      The user interface to display messages.
     * @param storage The storage handler to save the updated task list.
     * @throws DukeException If there's an error unmarking the task or saving the task list.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        ui.showMessage(String.format("OK, I've marked this task as not done yet:\n\t%s\n",
                tasks.unmarkTask(this.index)));
        storage.save(tasks);
    }
}
