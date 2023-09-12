package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The DeleteCommand class represents a command to deleted from the task list.
 */
public class DeleteCommand extends Command {
    private final int TASK_NUMBER;

    /**
     * Constructs a DeleteCommand object.
     *
     * @param taskNumber Task number corresponding to the task to be deleted from the task list.
     */
    public DeleteCommand(int taskNumber) {
        TASK_NUMBER = taskNumber;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (TASK_NUMBER > taskList.getNumberOfTasks()) {
            throw new DukeException("OOPS!!! Task " + TASK_NUMBER + " does not exist.");
        }

        String message = ui.showDeletedTask(taskList.getTask(TASK_NUMBER), taskList.getNumberOfTasks() - 1);
        taskList.remove(TASK_NUMBER);
        storage.save(taskList.getList(), ui);
        return message;
    }
}
