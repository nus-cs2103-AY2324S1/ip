package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.DukeList;

/**
 * The DeleteCommand class represents a command for deleting tasks from the task list.
 *
 */
public class DeleteCommand extends Command {
    private String description;

    /**
     * Constructs a DeleteCommand with the specified details of the task to be deleted.
     *
     * @param description The details of the task to be deleted.
     */
    public DeleteCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the DeleteCommand by deleting a task from the task list and saving it to storage.
     *
     * @param dukelist The task list from which the task will be deleted.
     * @param storage  The storage object used for saving tasks.
     * @throws DukeException If there is an error deleting the task.
     */
    @Override
    public String execute(DukeList dukelist, Storage storage) throws DukeException {
        Task deletedTask;
        try {
            deletedTask = dukelist.deleteTask(this.description);
            assert deletedTask != null : "Task to be deleted cannot be null";
            storage.saveData(dukelist.getList());
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }

        return Ui.printDeletedTask(deletedTask, dukelist.getList());
    }
}