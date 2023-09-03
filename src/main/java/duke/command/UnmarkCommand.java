package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Determines the task index to be unmarked.
 */
public class UnmarkCommand implements Command {
    private final String details;

    /**
     * Constructs a UnmarkCommand with the specified details.
     *
     * @param details The details of the task index to be marked.
     */
    public UnmarkCommand(String details) {
        this.details = details;
    }

    /**
     * Executes the command by unmarking a task as completed in the task list.
     * Displays appropriate messages to the user.
     *
     * @param tasks   The list of tasks in which the task will be unmarked.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving task data after modification.
     * @throws DukeException If the task index is out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int unmarkIndex = Integer.parseInt(details) - 1;
        if (unmarkIndex > tasks.size() || unmarkIndex < 0) {
            throw new DukeException("OOPS!! Task does not exist");
        }
        Task curr = tasks.get(unmarkIndex);
        curr.taskUndone();
        ui.sendMessage("OK, I've marked this task as not done yet:\n" + "\t" + curr);
        storage.editData(tasks);
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
     * Indicates that this command is not an exit command.
     *
     * @return `false` indicating that the application should not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
