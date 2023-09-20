package martin.commands;

import martin.exceptions.NoTaskFoundException;
import martin.task.Task;

import java.util.ArrayList;

/**
 * Represents a command that searches and lists tasks that contain a given keyword.
 */
public class FindCommand implements Command {

    private String input;
    private ArrayList<Task> tasks;

    /**
     * Creates a Find Command.
     *
     * @param input The user command input.
     * @param tasks The list of tasks.
     */
    public FindCommand(String input, ArrayList<Task> tasks) {
        this.input = input;
        this.tasks = tasks;
    }

    /**
     * Finds tasks that match the given keyword.
     *
     * @return A formatted string listing all the tasks that match the keyword.
     * @throws NoTaskFoundException If no tasks match the keyword.
     */
    @Override
    public String execute() throws NoTaskFoundException {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        String keyword = input.substring(5);

        for (Task task : tasks) {
            if (task.contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            throw new NoTaskFoundException("No tasks found that match the keyword: " + keyword);
        }

        StringBuilder result = new StringBuilder();
        result.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            result.append((i + 1) + "." + matchingTasks.get(i).toString()).append("\n");
        }
        return result.toString().trim();
    }
}