package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

public class CmdMark extends Command {
    private int index;

    public CmdMark(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.markDone(index);
        ui.print("Meow! I've marked this task as done:");
        ui.print(taskList.getTask(index).toString());
        Storage.writeToFile(taskList);
    }

}
