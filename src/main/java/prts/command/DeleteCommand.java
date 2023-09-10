package prts.command;

import prts.OutOfRangeException;
import prts.SaveToFileException;
import prts.Storage;
import prts.TaskList;
import prts.Ui;

/**
 * The command for deletion of a Task from the TaskList.
 */
public class DeleteCommand extends Command {

    private Integer index;

    /**
     * Constructs a DeleteCommand object, given the index of the task to be deleted.
     * @param index The index of the task to be deleted. If all tasks are to be deleted,
     *              this is null.
     */
    public DeleteCommand(Integer index) {
        this.index = index;
    }

    /**
     * Executes the deletion of the Task from the TaskList.
     * @param tasks The list of tasks currently stored.
     * @param ui The UI object stored by PRTS.
     * @param storage The Storage object stored by PRTS.
     * @return The string to be displayed to the user upon successful execution.
     * @throws SaveToFileException If the Storage object fails to save the state of the TaskList
     *         after the deletion of the Task.
     * @throws OutOfRangeException If the index provided falls out of the range of the TaskList.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws SaveToFileException, OutOfRangeException {
        String ret = tasks.delete(index);
        storage.save(tasks);
        return ret;
    }
}
