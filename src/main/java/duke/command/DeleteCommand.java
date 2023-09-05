package duke.command;

import duke.task.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;

public class DeleteCommand extends Command{

    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.deleteTask(this.taskNumber, storage, ui);
    }

}
