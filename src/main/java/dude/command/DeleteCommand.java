package dude.command;

import dude.Storage;
import dude.TaskList;
import dude.Ui;
import dude.task.Task;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int taskIndex;
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            System.out.println("Executing Delete Command");
            Task deletedTask = taskList.deleteTask(taskIndex);
            int nTasks = taskList.getSize();
            ui.showDeletedTask(deletedTask, nTasks);
            storage.saveTasksToDisk(taskList);
        } catch (IOException e) {
            System.out.println("Error in Delete Command");
        }
    }
}
