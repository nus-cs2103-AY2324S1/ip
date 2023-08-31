package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class CmdDelete extends Command {
    private int index;

    public CmdDelete(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        Task deleted = taskList.deleteTask(index);
        ui.print("Meow. I've removed this task:");
        ui.print(deleted.toString());
        ui.print(String.format("Now you have %d tasks in the list.", taskList.size()));
        Storage.writeToFile(taskList);
    }

}
