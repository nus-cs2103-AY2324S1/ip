package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class MarkCommand extends Command{
    private final int num;

    public MarkCommand(int num){
        this.num = num;
    }
    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) {
        tasksList.markTask(num);
    }
}
