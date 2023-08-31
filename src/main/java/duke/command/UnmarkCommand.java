package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

/**
 * Represents a command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private int index; //index of task to mark

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to be marked as not done.
     */
    public UnmarkCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Executes the UnmarkCommand by marking a task as not done,
     * displaying a completion message, and saving the updated task list to storage.
     *
     * @param taskList The list of tasks to operate on.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage for saving tasks to a file.
     * @throws DukeException If the provided task index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (index >= 0 && index < tasks.size()) {
            Task unmarkTask = tasks.get(index);
            tasks.markNotDone(index);
            ui.printMessage("OK, I've marked this task as not done yet:\n\t",unmarkTask);
            storage.saveTasksToFile(tasks);
        } else {
            throw new DukeException("☹ OOPS!!! Please provide a valid task index to be unmarked.");
        }
    }
}
