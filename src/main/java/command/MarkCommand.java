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
    public String execute(TaskList taskList, Ui ui) {
        StringBuilder str = new StringBuilder();
        str.append("Nice! I've marked this task as done:\n");
        Task markTask = taskList.getTask(this.markTaskIndex);
        markTask.markDone();
        str.append(markTask + "\n");
        return str.toString();
    }
}
