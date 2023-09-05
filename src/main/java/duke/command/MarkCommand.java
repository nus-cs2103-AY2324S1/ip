package duke.command;

import duke.task.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private int taskNumber;

    /**
     * Creates a new MarkCommand with the specified task number.
     *
     * @param taskNumber The number of the task to be marked as done.
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.markTaskAsDone(this.taskNumber, storage, ui);
    }
}
