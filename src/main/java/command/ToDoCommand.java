package main.java.command;

import main.java.Task;
import main.java.ToDo;
import main.java.tasklist.TaskList;
import main.java.ui.Ui;

/**
 * Represents a todo command, which will create a task without any date/time attached to it.
 */
public class ToDoCommand extends Command {
    private Task task;

    public ToDoCommand(String description) {
        this.task = new ToDo(description);
    }

    /**
     * Executes the todo command, creating a todo task that will be stored in the current tasklist.
     *
     * @param taskList The current taskList stored in Botty.
     * @param ui The current ui details.
     * @return Result to be displayed to user.
     */
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
