package barbie.commands;

import java.util.ArrayList;

import barbie.Storage;
import barbie.Ui;
import barbie.types.Task;

public class UnmarkCommand extends Command {
    int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
        this.isExit = false;
    }

    @Override
    public String run(ArrayList<Task> taskList) {
        Task task = taskList.get(taskNumber);
        task.unmark();
        Storage.changeLineStatus("0", taskNumber);
        return Ui.unmark(task);
    }
}
