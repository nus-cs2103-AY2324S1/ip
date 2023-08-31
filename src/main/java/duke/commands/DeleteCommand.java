package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.DukeList;
import duke.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs a command to delete a task at the specified index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete a task from the task list.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage manager.
     * @throws DukeException If the specified index is out of bounds.
     */
    @Override
    public void execute(DukeList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.getSize() < index) {
            throw new DukeException("The task you are trying to delete is out of bounds!");
        }
        ui.acknowledgeDelete(index, tasks.getTask(index - 1));
        tasks.deleteTask(index);
        storage.updateStorage(tasks.getArrayList());
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return False, as this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

