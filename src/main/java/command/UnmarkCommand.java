package main.java.command;

import main.java.Task;
import main.java.tasklist.TaskList;
import main.java.ui.Ui;

public class UnmarkCommand extends Command {
    private final int unmarkTaskIndex;
    public UnmarkCommand(int i) {
        this.unmarkTaskIndex = i;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        System.out.println("OK, I've marked this task as not done yet:");
        Task unmarkTask = taskList.getTask(this.unmarkTaskIndex);
        unmarkTask.markUndone();
        System.out.println(unmarkTask + "\n");
    }
}
