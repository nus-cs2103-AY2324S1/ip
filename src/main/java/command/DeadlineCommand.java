package main.java.command;

import main.java.Deadline;
import main.java.Task;
import main.java.tasklist.TaskList;
import main.java.ui.Ui;

public class DeadlineCommand extends Command {
    private final Task task;
    public DeadlineCommand(String description, String by) {
        this.task = new Deadline(description, by);
    }

    @Override
    public String execute(TaskList taskList, Ui ui) {
        StringBuilder str = new StringBuilder();
        taskList.addTask(this.task);
        str.append("Got it. I've added this task:\n");
        str.append("  " + this.task + "\n");
        str.append("Now you have " + taskList.numOfTasks() + " tasks in the list.\n");
        return str.toString();
    }
}
