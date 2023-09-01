package nobita.command;

import nobita.exception.NobitaException;
import nobita.storage.Storage;
import nobita.task.Task;
import nobita.task.TaskList;
import nobita.ui.Ui;

/**
 *  Class that encapsulates UnmarkCommand which extends from Command.
 *
 *  @author Zheng Chenglong
 *
 */
public class UnmarkCommand extends Command{

    /** The index of task to be marked*/
    private final int index;

    /**
     * Constructs UnmarkCommand using the index of the task.
     *
     * @param index the index of the task to be marked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     *  Command that executes the unmarking of task.
     *
     *  @param tasks Contains all current tasks.
     *  @param ui Ui for interacting with user.
     *  @param storage Storage that the data file is stored in.
     *  @throws NobitaException If the task is not inside tasklist.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NobitaException {
        if (tasks.checkIndexWithinRange(index)) {
            throw new NobitaException("Selected task number not in list");
        }
        Task task = tasks.markIncomplete(index);
        ui.showMessage("OK, I've marked this task as not done yet:\n" + task);
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
