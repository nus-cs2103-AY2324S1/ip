package qi.command;

import java.io.IOException;

import qi.qiexception.QiException;
import qi.storage.Storage;
import qi.tasklist.TaskList;
import qi.ui.Ui;

/**
 * Represents the process of marking a task in the list as done.
 */
public class MarkCommand extends Command {

    private int taskId;

    /**
     * Takes in the Id of the task to be marked as done.
     *
     * @param taskId Integer representing the index (start from one)
     *               of the task in the list.
     */
    public MarkCommand(int taskId) {
        super(false);
        this.taskId = taskId;
    }

    /**
     * Marks the task with the given Id in the list as done and then
     * updates the data on the hard disk.
     *
     * @param tasks TaskList recording the current task in the list.
     * @param ui Ui representing the current User Interface (UI).
     * @param storage Storage updating data if necessary.
     * @throws QiException If the file storing the data cannot be written.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws QiException {
        ui.showTaskMarked(this.taskId, tasks);
        try {
            storage.update(tasks);
        } catch (IOException e) {
            throw new QiException("Cannot write to file!");
        }
    }
}
