package jarvis.commands;

import jarvis.exceptions.DukeException;
import jarvis.storage.Storage;
import jarvis.tasks.Task;
import jarvis.tasks.TaskList;
import jarvis.ui.Ui;

/**
 * Represents the DeleteCommand Class.
 * Responsible for handling delete operations.
 *
 * @author Shishir
 */
public class DeleteCommand extends Command {

    /** Index of respective task. */
    private int index;

    /**
     * Constructs the DeleteCommand Object.
     * @param index Index of the respective task.
     */
    public DeleteCommand(int index) {
        assert index > 0 : "Invalid index!";
        this.index = index;
    }

    /**
     * Executes the required command.
     * @param tasks List of all the tasks.
     * @param ui Ui for interacting with the user.
     * @param storage Storage of the tasks.
     * @throws DukeException Throws DukeException on invalid input.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert this.index <= tasks.size();
        Task task = tasks.getTask(this.index - 1);
        tasks.delete(this.index - 1);
        storage.writeData(tasks.getAllTasks());
        return ui.showDelete(this.index, task);
    }
}
