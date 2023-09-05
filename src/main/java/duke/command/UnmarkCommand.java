package duke.command;

import duke.task.TaskList;
import duke.utility.Storage;
import duke.utility.Ui;

public class UnmarkCommand extends Command{
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.unmarkTaskAsDone(this.taskNumber, storage, ui);
    }
}
