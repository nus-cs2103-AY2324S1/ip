package main.java.command;

import main.java.Deadline;
import main.java.Task;
import main.java.tasklist.TaskList;
import main.java.ui.Ui;

/**
 * Represents a deadline command, which will create a task that need to be done before a specific date/time.
 */
public class DeadlineCommand extends Command {
    private final Task task;
    public DeadlineCommand(String description, String by) {
        this.task = new Deadline(description, by);
    }

    /**
     * Executes the deadline command creating a deadline task that will be stored in the current tasklist.
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
