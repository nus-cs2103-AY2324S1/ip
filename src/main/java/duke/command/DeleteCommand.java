package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;
import duke.task.Task;

import duke.exception.DukeException;

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
        this.index = index;
    }

    /**
     * Executes the DeleteCommand by removing the task from the task list,
     * displaying a deletion message, and saving the updated task list to storage.
     *
     * @param taskList The list of tasks to operate on.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage for saving tasks to a file.
     * @throws DukeException If the provided task index is invalid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index >= 0 && index < taskList.size()) {
            Task removedTask = taskList.get(index);
            taskList.delete(index);
            ui.printDeleteMessage(removedTask, taskList.size());
            storage.saveTasksToFile(taskList);
        } else {
            throw new DukeException("☹ OOPS!!! Please provide a valid task index to delete.");
        }
    }
}
