package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The DeleteCommand class represents a command to deleted from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param taskNumber Task number corresponding to the task to be deleted from the task list.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskNumber > taskList.getNumberOfTasks()) {
            throw new DukeException("OOPS!!! Task " + taskNumber + " does not exist.");
        }

        String message = ui.showDeletedTask(taskList.getTask(taskNumber), taskList.getNumberOfTasks() - 1);
        taskList.remove(taskNumber);
        storage.save(taskList.getList(), ui);
        return message;
    }
}
