package barbie.commands;

import java.util.ArrayList;

import barbie.Storage;
import barbie.Ui;
import barbie.types.Task;

public class MarkCommand extends Command {
    int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
        this.isExit = false;

    }

    @Override
    public String run(ArrayList<Task> taskList) {
        Task task = taskList.get(taskNumber);
        task.mark();
        Storage.changeLineStatus("1", taskNumber);
        return Ui.mark(task);
    }
}
