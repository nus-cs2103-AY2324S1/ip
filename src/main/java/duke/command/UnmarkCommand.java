package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The UnmarkCommand class represents a command to be marked undone in the task list.
 */
public class UnmarkCommand extends Command {
    private final int TASK_NUMBER;

    /**
     * Construct an UnmarkCommand object.
     *
     * @param taskNumber The task number corresponding to the task, to be marked undone.
     */
    public UnmarkCommand(int taskNumber) {
        TASK_NUMBER = taskNumber;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (TASK_NUMBER > taskList.getNumberOfTasks()) {
            throw new DukeException("OOPS!!! Task " + TASK_NUMBER + " does not exist.");
        }

        String message = ui.showTaskMarkedAsUndone(taskList.getTask(TASK_NUMBER));
        storage.save(taskList.getList(), ui);
        return message;
    }
}
