package commands;

import storage.Storage;
import taskList.TaskList;
import ui.Ui;

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
