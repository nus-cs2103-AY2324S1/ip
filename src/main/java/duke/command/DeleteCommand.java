package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;

/**
 * Represents the command to delete tasks from the task list.
 */
public class DeleteCommand extends Command {
    /** Index of the task to be deleted. */
    private int index;

    /**
     * Constructor for the delete command.
     * 
     * @param index Index to be zero based to fit Array List indexing.
     */
    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index < 0 || index >= taskList.size()) {
            throw new DukeException("OOPS! The index to delete is invalid!");
        }
        Task deletedTask = taskList.get(index);
        assert (deletedTask != null) : "task not found";
        taskList.delete(index);
        ui.deleteFromListSuccess(deletedTask, taskList.size());
        storage.saveList(taskList.getAllTasks());
    }
}
