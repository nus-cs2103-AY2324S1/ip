package urchatbot.commands;

import urchatbot.storage.Storage;
import urchatbot.taskList.TaskList;
import urchatbot.ui.Ui;

public class ClearCommand extends Command{
    public ClearCommand(String taskDescription) {
        super(taskDescription);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.clearTask();
        Storage.save(tasks);
        ui.showClearMessage();
    }
}
