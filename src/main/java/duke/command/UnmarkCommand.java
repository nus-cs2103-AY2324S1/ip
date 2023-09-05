package duke.command;

import duke.task.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;

/**
 * Represents a command to unmark a task as done in the task list.
 */
public class UnmarkCommand extends Command {
    private int taskNumber;

    /**
     * Creates a new UnmarkCommand with the specified task number.
     *
     * @param taskNumber The number of the task to be unmarked as done.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.unmarkTaskAsDone(this.taskNumber, storage, ui);
    }
}
