package prts.command;

import prts.OutOfRangeException;
import prts.SaveToFileException;
import prts.Storage;
import prts.TaskList;
import prts.Ui;
import prts.task.AlreadyUnmarkedException;

/**
 * The command for marking a Task as not yet complete.
 */
public class UnmarkCommand extends Command {

    private Integer index;

    /**
     * Constructs a UnmarkCommand object, given the index of the task to be deleted.
     * @param index The index of the task to be unmarked. If all tasks are to be unmarked,
     *              this is null.
     */
    public UnmarkCommand(Integer index) {
        this.index = index;
    }

    /**
     * Executes the marking of the Task as not yet done.
     * @param tasks The list of tasks currently stored.
     * @param ui The UI object stored by PRTS.
     * @param storage The Storage object stored by PRTS.
     * @throws OutOfRangeException If the index provided is out of range of the TaskList.
     * @throws AlreadyUnmarkedException If the Task indicated by the provided index is already marked
     * as not yet complete.
     * @throws SaveToFileException If the Storage object fails to save the state of the TaskList
     * after the Task is unmarked.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlreadyUnmarkedException,
            OutOfRangeException, SaveToFileException {
        ui.displayMessage(tasks.unmark(index));
        storage.save(tasks);
    }
}
