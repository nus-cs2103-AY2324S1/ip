package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class MarkCommand extends Command {
    private final int taskNumber;

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
