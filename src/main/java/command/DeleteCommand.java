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
    public String execute(TaskList taskList, Ui ui) {
        StringBuilder str = new StringBuilder();
        str.append("Noted. I've removed this task:\n");
        Task deletedTask = taskList.getTask(this.deleteTaskIndex);
        str.append("  " + deletedTask + "\n");
        taskList.removeTask(deletedTask);
        str.append("Now you have " + taskList.numOfTasks() + " tasks in the list.\n");
        return str.toString();
    }
}
