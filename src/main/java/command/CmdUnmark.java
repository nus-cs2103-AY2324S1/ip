package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class CmdUnmark extends Command {
    private int index;

    public CmdUnmark(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.markNotDone(index);
        ui.print("Meow! I've marked this task as not done yet:");
        ui.print(taskList.getTask(index).toString());
        Storage.writeToFile(taskList);
    }

}
