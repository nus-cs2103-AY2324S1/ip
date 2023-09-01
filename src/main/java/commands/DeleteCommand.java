package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class DeleteCommand extends Command{
    private final int num;

    public DeleteCommand(int num){
        this.num = num;
    }
    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) {
        ui.showRemovedTask(tasksList, num);
        tasksList.removeTask(num);
    }
}
