package aichan.command;

import aichan.Storage;
import aichan.TaskList;
import aichan.Ui;
import aichan.task.Task;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage("Here are the tasks in your list:");
        int size = tasks.getSize();
        for (int i = 1; i <= size; i++) {
            ui.showMessage(i + "." + tasks.getTask(i).toString());
        }
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
