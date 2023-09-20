package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The UnmarkCommand class represents a command to be marked undone in the task list.
 */
public class UnmarkCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs an UnmarkCommand object.
     *
     * @param taskNumber The task number corresponding to the task, to be marked undone.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskNumber > taskList.getNumberOfTasks()) {
            throw new DukeException("OOPS!!! Task " + taskNumber + " does not exist.");
        }

        String message = ui.showTaskMarkedAsUndone(taskList.getTask(taskNumber));
        storage.save(taskList.getList(), ui);
        return message;
    }
}
