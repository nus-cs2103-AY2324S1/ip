package main.java.command;

import main.java.Task;
import main.java.tasklist.TaskList;
import main.java.ui.Ui;

public class MarkCommand extends Command {
    private final int markTaskIndex;

    public MarkCommand(int i) {
        this.markTaskIndex = i;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        System.out.println("Nice! I've marked this task as done:");
        Task markTask = taskList.getTask(this.markTaskIndex);
        markTask.markDone();
        System.out.println(markTask + "\n");
    }
}
