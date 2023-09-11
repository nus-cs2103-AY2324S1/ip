package main.java.command;

import main.java.Task;
import main.java.ToDo;
import main.java.tasklist.TaskList;
import main.java.ui.Ui;

public class ToDoCommand extends Command {
    private Task task;

    public ToDoCommand(String description) {
        this.task = new ToDo(description);
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
