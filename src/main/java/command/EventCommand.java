package main.java.command;

import main.java.Event;
import main.java.Task;
import main.java.tasklist.TaskList;
import main.java.ui.Ui;

/**
 * Represents an event command, which will create a task that start at a specific date/time and ends at a specific date/time.
 */
public class EventCommand extends Command {

    private Task task;

    public EventCommand(String description, String from, String to) {
        this.task = new Event(description, from, to);
    }


    /**
     * Executes the event command, creating an event task that will be stored in the current tasklist.
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
