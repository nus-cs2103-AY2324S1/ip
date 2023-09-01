package nobita.command;

import nobita.exception.NobitaException;
import nobita.storage.Storage;
import nobita.task.Task;
import nobita.task.TaskList;
import nobita.ui.Ui;

/**
 * Class that encapsulates DeleteCommand which extends from Command.
 *
 * @author Zheng Chenglong
 *
 */
public class DeleteCommand extends Command{

    /** The index of task to be deleted */
    private final int index;

    /**
     * Constructs DeleteCommand using the index of the task.
     *
     * @param index the index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Command that executes the task to be deleted.
     *
     * @param tasks Contains all current tasks.
     * @param ui Ui for interacting with user.
     * @param storage Storage that the data file is stored in.
     * @throws NobitaException If the task is not inside tasklist.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NobitaException {
        if (tasks.checkIndexWithinRange(index)) {
            throw new NobitaException("Selected task number not in list");
        }
        Task task = tasks.deleteTask(index);
        ui.showMessage("Noted. I've removed this task:\n" + task + "\nNow you have " + tasks.getTotalTask() +" tasks in the list.");
    };

    /**
     * Retrieves a boolean on whether the Command is an Exit Command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    };
}
