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
    public String execute(TaskList taskList, Ui ui) {
        StringBuilder str = new StringBuilder();
        taskList.addTask(this.task);
        str.append("Got it. I've added this task:\n");
        str.append("  " + this.task + "\n");
        str.append("Now you have " + taskList.numOfTasks() + " tasks in the list.\n");
        return str.toString();
    }

}
