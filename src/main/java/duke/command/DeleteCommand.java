package duke.command;

import duke.task.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {

    private final int taskNumber;

    /**
     * Creates a new DeleteCommand with the specified task number.
     *
     * @param taskNumber The number of the task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.deleteTask(this.taskNumber, storage, ui);
    }
}
