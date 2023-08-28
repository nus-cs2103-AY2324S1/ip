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
    public void execute(TaskList taskList, Ui ui) {
        taskList.addTask(this.task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this.task);
        System.out.println("Now you have " + taskList.numOfTasks() + " tasks in the list.\n");

    }
}
