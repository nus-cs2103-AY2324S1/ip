package qi.command;

import qi.qiexception.QiException;
import qi.storage.Storage;
import qi.tasklist.TaskList;
import qi.ui.Ui;

import java.io.IOException;

/**
 * Represents the process of unmarking a task in the list.
 */
public class UnmarkCommand extends Command {

    private int taskId;

    /**
     * Takes in the Id of the task to be unmarked.
     *
     * @param taskId Integer representing the index (start from one)
     *               of the task in the list.
     */
    public UnmarkCommand(int taskId) {
        super(false);
        this.taskId = taskId;
    }

    /**
     * Unmarks the task with the given Id in the list and then
     * updates the data on the hard disk.
     *
     * @param tasks TaskList recording the current task in the list.
     * @param ui Ui representing the current User Interface (UI).
     * @param storage Storage updating data if necessary.
     * @throws QiException If the file storing the data cannot be written.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws QiException {
        ui.showTaskUnmarked(this.taskId, tasks);
        try {
            storage.update(tasks);
        } catch (IOException e) {
            throw new QiException("Cannot write to file!");
        }
    }
}
