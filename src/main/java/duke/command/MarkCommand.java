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
    private final String details;

    /**
     * Constructs a MarkCommand with the specified details.
     *
     * @param details The details of the task index to be marked.
     */
    public MarkCommand(String details) {
        this.details = details;
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
        int markIndex = Integer.parseInt(details) - 1;
        if (markIndex > tasks.size() || markIndex < 0) {
            throw new DukeException("OOPS!! Task does not exist");
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
     * Indicates that this command is not an exit command.
     *
     * @return `false` indicating that the application should not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
