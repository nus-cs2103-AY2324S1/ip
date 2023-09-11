package dude.command;

import dude.Storage;
import dude.TaskList;
import dude.Ui;
import dude.task.Task;

import java.io.IOException;

public class MarkCommand extends Command {
    private int taskIndex;
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            System.out.println("Executing Mark Command");;
            Task markedTask = taskList.markTask(taskIndex);
            ui.showMarkedTask(markedTask);
            storage.saveTasksToDisk(taskList);
        } catch (IOException e) {
            System.out.println("Error in Mark Command");
        }
    }
}
