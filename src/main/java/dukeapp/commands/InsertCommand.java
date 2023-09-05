package dukeapp.commands;

import dukeapp.Messages;
import dukeapp.Parser;
import dukeapp.TaskList;
import dukeapp.Ui;
import dukeapp.exceptions.InsufficientArgumentsException;
import dukeapp.tasks.Task;

import java.io.IOException;
import java.time.format.DateTimeParseException;

/**
 * Inserts item into application state.
 */
public class InsertCommand implements Command {
    /**
     * Adds an item to the application state.
     *
     * @param input    The user input of the item to add.
     * @param taskList The application's task list.
     * @param ui       The UI of the application.
     */
    @Override
    public void run(String input, TaskList taskList, Ui ui) {
        String[] args = input.split(" ", 2);
        String taskType = args[0];
        String taskInput = args.length > 1 ? args[1] : "";
        try {
            // Create a task based on task type
            Task task = Parser.createTask(taskType, taskInput);
            taskList.insertTask(task);
            int taskCount = taskList.getTaskCount();
            ui.displayMessage(String.format(Messages.INSERT_MESSAGE,
                    task, taskCount, taskCount == 1 ? "task" : "tasks"));
        } catch (InsufficientArgumentsException | DateTimeParseException | IOException e) {
            ui.displayError(e.getMessage());
        }
    }
}
