package main.java.command;

import main.java.Task;
import main.java.Todo;
import main.java.tasklist.TaskList;
import main.java.ui.Ui;

public class TodoCommand extends Command {
    private Task task;

    public TodoCommand(String description) {
        this.task = new Todo(description);
    }
    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addTask(this.task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this.task);
        System.out.println("Now you have " + taskList.numOfTasks() + " tasks in the list.\n");
    }

}
