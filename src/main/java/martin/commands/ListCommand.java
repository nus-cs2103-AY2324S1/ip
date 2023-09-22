package martin.commands;

import martin.task.Task;

import java.util.ArrayList;

/**
 * Represents a command that lists all the tasks from the task list.
 */
public class ListCommand implements Command {

    private ArrayList<Task> tasks;

    /**
     * Creates a List Command.
     *
     * @param input The user command input.
     */
    public ListCommand(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Lists all tasks currently in the list.
     *
     * @return A string representation of the list of all tasks.
     */
    @Override
    public String execute() {
        if (tasks.isEmpty()) {
            return "The task list is currently empty!";
        }

        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < tasks.size(); i++) {
            result.append((i + 1) + ". " + tasks.get(i)).append("\n");
        }

        return result.toString().trim();
    }
}
