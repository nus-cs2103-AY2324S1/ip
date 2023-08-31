package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * The MarkCommand class represents a command to mark the specified task in the list as done.
 */
public class MarkCommand extends Command {
    private final int taskNumber;

    /**
     * Construcst a MarkCommand object.
     *
     * @param taskNumber The task number corresponding to the task, to be marked done.
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskNumber > taskList.getNumberOfTasks()) {
            throw new DukeException("OOPS!!! Task " + taskNumber + " does not exist.");
        }
        ui.showTaskMarkedAsDone(taskList.getTask(taskNumber));
        storage.save(taskList.getList(), ui);
    }
}
