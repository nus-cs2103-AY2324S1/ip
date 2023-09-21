package echobot.command;

import java.util.ArrayList;

import echobot.storage.Storage;
import echobot.task.Task;
import javafx.scene.layout.VBox;

/**
 * Represents a command to find specific task based on the keyword
 */
public class FindCommand extends Command<Task> {
    private final String keyword;
    private String responseText;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds tasks based on the specified keyword.
     *
     * @param tasks   The list of tasks.
     * @param keyword The keyword to search for.
     * @return A list of tasks containing the keyword.
     */
    public ArrayList<Task> findTasks(ArrayList<Task> tasks, String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().matches(".*\\b" + keyword.toLowerCase() + "\\b.*")) {
                assert task.getDescription() != null : "Task description should not be null.";
                matchingTasks.add(task);
            }
        }

        return matchingTasks;
    }


    @Override
    public String doCommand(ArrayList<Task> tasks, Storage storage, VBox dialogContainer) {
        ArrayList<Task> matchingTasks = findTasks(tasks, keyword);

        if (keyword.isEmpty()) {
            responseText = "Please specify a keyword to search for!\n";
        } else if (!matchingTasks.isEmpty()) {
            responseText = "Here are the matching tasks in your list:\n";

            for (int i = 0; i < matchingTasks.size(); i++) {
                Task task = matchingTasks.get(i);
                responseText += "    " + (i + 1) + ". " + task.display() + "\n";
            }
        } else {
            responseText = "No tasks match the keyword: " + keyword + "\n";
        }

        return responseText;
    }
    public String getResponse() {
        return responseText;
    }

}
