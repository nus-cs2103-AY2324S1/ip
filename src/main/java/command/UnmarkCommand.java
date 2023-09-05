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
    public String execute(TaskList taskList, Ui ui) {
        StringBuilder str = new StringBuilder();
        str.append("OK, I've marked this task as not done yet:\n");
        Task unmarkTask = taskList.getTask(this.unmarkTaskIndex);
        unmarkTask.markUndone();
        str.append(unmarkTask + "\n");
        return str.toString();
    }
}
