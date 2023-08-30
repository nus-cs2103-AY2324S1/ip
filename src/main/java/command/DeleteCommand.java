package main.java.command;

import main.java.Task;
import main.java.tasklist.TaskList;
import main.java.ui.Ui;

public class DeleteCommand extends Command {
    private int deleteTaskIndex;

    public DeleteCommand(int i) {
        this.deleteTaskIndex = i;
    }
    @Override
    public void execute(TaskList taskList, Ui ui) {
        System.out.println("Noted. I've removed this task:");
        Task deletedTask = taskList.getTask(this.deleteTaskIndex);
        System.out.println("  " + deletedTask);
        taskList.removeTask(deletedTask);
        System.out.println("Now you have " + taskList.numOfTasks() + " tasks in the list.\n");

    }
}
