package commands;

import exceptions.JamesBondException;
import storage.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {

    int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;

    }


    @Override
    public void runCommand(TaskList taskList, Ui ui, Storage storage) {
        Task task = taskList.get(taskNumber -1);
        taskList.delete(taskNumber - 1);
        int len = taskList.len();
        ui.showDeleteMessage(task, len);
    }
}
