package duke.command;

import duke.exception.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * The DeleteCommand class is for the command to delete tasks.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * The constructor for a DeleteCommand.
     *
     * @param index The one-based index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Executes the delete command.
     *
     * @param tasks The TaskList to be worked on.
     * @param ui The Ui to be worked on.
     * @param storage The Storage to be worked on.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printDeleteSuccessMessage(index, tasks.getAllTasks());
        tasks.delete(index);
        storage.save(tasks.getAllTasks());
    }

    /**
     * Checks whether it is an Exit command.
     *
     * @return Returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
