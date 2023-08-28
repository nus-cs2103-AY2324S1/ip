package main.java.command;

import main.java.Event;
import main.java.Task;
import main.java.tasklist.TaskList;
import main.java.ui.Ui;

public class EventCommand extends Command {

    private Task task;

    public EventCommand(String description, String from, String to) {
        this.task = new Event(description, from, to);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        taskList.addTask(this.task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this.task);
        System.out.println("Now you have " + taskList.numOfTasks() + " tasks in the list.\n");
    }

}
