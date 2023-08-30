package dukeapp.commands;

import dukeapp.DukeConstants;
import dukeapp.DukeState;
import dukeapp.exceptions.InsufficientArgumentsException;
import dukeapp.tasks.Task;

import java.time.format.DateTimeParseException;

/**
 * Inserts item into application state.
 */
public class InsertCommand implements Command {
    private final DukeState state;

    public InsertCommand(DukeState state) {
        this.state = state;
    }

    /**
     * Adds an item to the application state.
     *
     * @param input The user input of the item to add.
     */
    @Override
    public void run(String input) {
        String[] args = input.split(" ", 2);
        String taskType = args[0];
        String taskInput = args.length > 1 ? args[1] : "";
        try {
            // Create a task based on task type
            Task task = Task.createTask(taskType, taskInput);
            this.state.insertTask(task);
            int taskCount = this.state.getTaskCount();
            System.out.printf((DukeConstants.INSERT_MESSAGE) + "%n",
                    task, taskCount, taskCount == 1 ? "task" : "tasks");
        } catch (InsufficientArgumentsException | DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }
}
