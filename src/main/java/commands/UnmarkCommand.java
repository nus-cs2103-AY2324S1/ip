package commands;

import exceptions.JamesBondException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command {
    protected int taskNumber;
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void runCommand(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(taskNumber - 1);
        task.unMark();
        ui.unmarkMessage(task);
    }
}
