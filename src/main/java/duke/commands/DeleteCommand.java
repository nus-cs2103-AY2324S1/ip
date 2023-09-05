package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    /**
     * The index of the task to be deleted
     */
    private final int index;

    /**
     * Constructor
     *
     * @param index the index of the task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command
     *
     * @param tasks   the task list
     * @param ui      the ui
     * @param storage the storage
     * @return
     * @throws DukeException if there is an error
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Check if index is invalid or the task exists.
        if (tasks.length() < index) {
            throw new DukeException("The task you are trying to delete either doesn't exist, or is already deleted!");
        }
        Task task = tasks.getTask(this.index - 1);
        tasks.delete(index - 1);
        storage.writeData(tasks.getAllTasks());
        return ui.showDelete(index, task) + "\n" + "You now have" + ui.showList(tasks.length()) + "tasks in the list.";
    }

    /**
     * Returns true if the command is an exit command
     *
     * @return true if the command is an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
