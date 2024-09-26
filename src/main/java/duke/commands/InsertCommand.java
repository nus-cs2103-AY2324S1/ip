package duke.commands;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.Messages;
import duke.Parser;
import duke.TaskList;
import duke.tasks.Task;

/**
 * Inserts item into the task list.
 */
public class InsertCommand implements Command {
    /**
     * Adds an item to the application's task list.
     *
     * @param input    The user input of the item to add.
     * @param taskList The application's task list.
     * @return The string output of the command's execution.
     */
    @Override
    public String run(String input, TaskList taskList) {
        String[] args = input.split(" ", 2);
        String taskType = args[0];
        String taskInput = args.length > 1 ? args[1] : "";
        try {
            // Create a task based on task type
            Task task = Parser.createTask(taskType, taskInput);
            taskList.insertTask(task);
            int taskCount = taskList.getTaskCount();
            return String.format(Messages.INSERT_MESSAGE, task, taskCount, taskCount == 1 ? "task"
                    : "tasks");
        } catch (IllegalArgumentException | DateTimeParseException | IOException e) {
            return String.format(Messages.ERROR_PREFIX, e.getMessage());
        }
    }
}
