package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Determines the task index to be deleted.
 */
public class DeleteCommand implements Command {
    private final String taskDetail;
    private Task deletedTask;

    /**
     * Constructs a DeleteCommand with the specified details.
     *
     * @param detail The details of the task index to be deleted.
     */
    public DeleteCommand(String detail) {
        this.taskDetail = detail;
        this.deletedTask = null;
    }

    /**
     * Executes the command by removing a task from the task list.
     * Displays appropriate messages to the user.
     *
     * @param tasks   The list of tasks from which the task will be deleted.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving task data after modification.
     * @throws DukeException If the task index is out of bounds.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int deleteIndex = Integer.parseInt(this.taskDetail) - 1;
        if (deleteIndex > tasks.size() || deleteIndex < 0) {
            throw new DukeException("Error 404!! Task does not exist");
        } else {
            ui.sendMessage("Noted. I've removed this task:\n" + "\t" + tasks.get(deleteIndex).toString() + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.");
            this.deletedTask = tasks.get(deleteIndex);
            tasks.remove(deleteIndex);
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
     * Undoes the task from the command details and add it to the task list.
     *
     * @param tasks The list of tasks to which the task will be added.
     * @return The command to be executed.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public Command undoTask(TaskList tasks) throws DukeException {
        return new AddCommand(this.deletedTask, this.taskDetail);
    }
}
