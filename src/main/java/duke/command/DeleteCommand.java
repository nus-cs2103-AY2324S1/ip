package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private final int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (taskNumber > taskList.getNumberOfTasks()) {
            throw new DukeException("OOPS!!! Task " + taskNumber + " does not exist.");
        }
        ui.showDeletedTask(taskList.getTask(taskNumber), taskList.getNumberOfTasks() - 1);
        taskList.remove(taskNumber);
        storage.save(taskList.getList(), ui);
    }
}
