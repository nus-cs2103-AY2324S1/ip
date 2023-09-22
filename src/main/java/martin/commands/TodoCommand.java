package martin.commands;

import martin.exceptions.EmptyTaskDescriptionException;
import martin.exceptions.MartinException;
import martin.task.Task;
import martin.task.Todo;

import java.util.ArrayList;

/**
 * Adds a new Todo task to the task list.
 */
public class TodoCommand implements Command {

    private String input;
    private ArrayList<Task> tasks;

    /**
     * Creates a Todo Command.
     *
     * @param input The user command input.
     * @param tasks The list of tasks.
     */
    public TodoCommand(String input, ArrayList<Task> tasks) {
        this.input = input;
        this.tasks = tasks;
    }

    /**
     * Adds a new ToDo task to the task list.
     * 
     * @return A string indicating the task has been added or an error message.
     * @throws EmptyTaskDescription If no task description is given in the input.
     * @throws MartinException If there's any other error executing the command.
     */
    @Override
    public String execute() throws MartinException {
        if (input.length() <= 4) {
            throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        
        String description = input.substring(5);
        if (description.isEmpty()) {
            throw new EmptyTaskDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        tasks.add(new Todo(description));
        return "Got it. I've added this task:\n       " + tasks.get(tasks.size() - 1) + "\n     Now you have " + tasks.size() + " tasks in the list.";
    }
}
