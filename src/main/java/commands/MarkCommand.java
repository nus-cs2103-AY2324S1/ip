package commands;

import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class MarkCommand extends Command{
    protected int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void runCommand (TaskList tasks, Ui ui, Storage storage){
        Task task = tasks.get(taskNumber -1);
        task.markAsDone();
        ui.markMessage(task);
    }
}
