package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Determines the task index to be marked.
 */
public class MarkCommand implements Command {
    private final String taskDetail;

    /**
     * Constructs a MarkCommand with the specified details.
     *
     * @param detail The details of the task index to be marked.
     */
    public MarkCommand(String detail) {
        this.taskDetail = detail;
    }

    /**
     * Executes the command by marking a task as completed in the task list.
     * Displays appropriate messages to the user.
     *
     * @param tasks   The list of tasks in which the task will be marked.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving task data after modification.
     * @throws DukeException If the task index is out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int markIndex = Integer.parseInt(this.taskDetail) - 1;
        if (markIndex >= tasks.size() || markIndex < 0) {
            throw new DukeException("Error 404!! Task does not exist");
        } else {
            Task curr = tasks.get(markIndex);
            curr.taskDone();
            ui.sendMessage("Nice! I've marked this task as done:\n" + "\t" + curr);
            storage.editData(tasks);
        }
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
     * Undoes the task from the command details and unmark the task.
     *
     * @param tasks The list of tasks to which the task will be unmark.
     * @return The command to be executed.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public Command undoTask(TaskList tasks) throws DukeException {
        return new UnmarkCommand(this.taskDetail);
    }
}
