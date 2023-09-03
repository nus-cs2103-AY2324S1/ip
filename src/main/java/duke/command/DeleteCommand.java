package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Determines the task index to be deleted.
 */
public class DeleteCommand implements Command {
    private final String details;

    /**
     * Constructs a DeleteCommand with the specified details.
     *
     * @param details The details of the task index to be deleted.
     */
    public DeleteCommand(String details) {
        this.details = details;
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
        int deleteIndex = Integer.parseInt(this.details) - 1;
        if (deleteIndex > tasks.size() || deleteIndex < 0) {
            throw new DukeException("OOPS!! Task does not exist");
        } else {
            ui.sendMessage("Noted. I've removed this task:\n" + "\t" + tasks.get(deleteIndex).toString() + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.");
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
     * Indicates that this command is not an exit command.
     *
     * @return `false` indicating that the application should not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
