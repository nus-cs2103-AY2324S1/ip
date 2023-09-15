package martin.commands;

import martin.task.Task;

import java.util.ArrayList;

/**
 * Represents a command that searches and lists tasks that contain a given keyword.
 */
public class FindCommand implements Command {

    private String command;
    private ArrayList<Task> tasks;

    public FindCommand(String command, ArrayList<Task> tasks) {
        this.command = command;
        this.tasks = tasks;
    }

    /**
     * Finds tasks that match the given keyword.
     *
     * @return A formatted string listing all the tasks that match the keyword.
     */
    @Override
    public String execute() {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        String keyword = command.substring(5);

        for (Task task : tasks) {
            if (task.contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        StringBuilder result = new StringBuilder();
        result.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            result.append((i + 1) + "." + matchingTasks.get(i).toString()).append("\n");
        }
        return result.toString().trim();
    }
}

