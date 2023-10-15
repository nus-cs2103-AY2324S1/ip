package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index; //index of task to be deleted

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        assert index > 0 : "Index must be a positive integer.";
        this.index = index - 1;
    }

    /**
     * Executes the DeleteCommand by removing the task from the task list,
     * displaying a deletion message, and saving the updated task list to storage.
     *
     * @param tasks The list of tasks to operate on.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage for saving tasks to a file.
     * @throws DukeException If the provided task index is invalid.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert index > 0 : "Index must be a positive integer.";
        if (index >= 0 && index < tasks.size()) {
            Task removedTask = tasks.get(index);
            tasks.delete(index);
            ui.printDeleteMessage(removedTask, tasks.size());
            storage.saveTasksToFile(tasks);
        } else {
            throw new DukeException("☹ OOPS!!! Please provide a valid task index to delete.");
        }
    }
}
