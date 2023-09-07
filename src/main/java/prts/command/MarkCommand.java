package prts.command;

import prts.OutOfRangeException;
import prts.SaveToFileException;
import prts.Storage;
import prts.TaskList;
import prts.Ui;
import prts.task.AlreadyMarkedException;

/**
 * The command for marking a Task as complete.
 */
public class MarkCommand extends Command {

    private Integer index;

    /**
     * Constructs a MarkCommand object, given the index of the task to be deleted.
     * @param index The index of the task to be marked. If all tasks are to be marked,
     *              this is null.
     */
    public MarkCommand(Integer index) {
        this.index = index;
    }

    /**
     * Executes the marking of the Task as done.
     * @param tasks The list of tasks currently stored.
     * @param ui The UI object stored by PRTS.
     * @param storage The Storage object stored by PRTS.
     * @throws OutOfRangeException If the index provided is out of range of the TaskList.
     * @throws AlreadyMarkedException If the Task indicated by the provided index is already marked
     * as complete.
     * @throws SaveToFileException If the Storage object fails to save the state of the TaskList
     * after the marking of the Task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OutOfRangeException,
            AlreadyMarkedException, SaveToFileException {
        ui.displayMessage(tasks.mark(index));
        storage.save(tasks);
    }

}
