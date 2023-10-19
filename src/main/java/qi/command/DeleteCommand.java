package qi.command;

import java.io.IOException;

import qi.qiexception.QiException;
import qi.storage.Storage;
import qi.tasklist.TaskList;
import qi.ui.Ui;

/**
 * Represents the execution of removing a task from the list.
 */
public class DeleteCommand extends Command {

    private final int taskId;

    /**
     * Takes in the Id of the task to be removed.
     *
     * @param taskId Integer representing the index (start from one)
     *               of the task in the list.
     */
    public DeleteCommand(int taskId) {
        this.taskId = taskId;
    }

    /**
     * Removes the task from the list and then updates the data on the hard disk.
     *
     * @param tasks TaskList recording the current task in the list.
     * @param ui Ui representing the current User Interface (UI).
     * @param storage Storage updating data if necessary.
     * @return String notification created by Ui.
     * @throws QiException If the file storing the data cannot be written.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws QiException {
        String response = ui.showTaskDeleted(this.taskId, tasks);
        try {
            storage.update(tasks);
        } catch (IOException e) {
            throw new QiException("Cannot write to file!");
        }
        return response;
    }
}


