package duke.command;

import java.util.ArrayList;
import javax.swing.JTextArea;

import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Represents a command to find specific task based on the keyword
 */
public class FindCommand extends Command {
    private final String keyword;

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
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Executes the doCommand, searching for tasks.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The data storage.
     * @param chatArea JTextArea for displaying messages in the GUI.
     */
    @Override
    public void doCommand(ArrayList<Task> tasks, Ui ui, Storage storage, JTextArea chatArea) {
        ArrayList<Task> matchingTasks = findTasks(tasks, keyword);

        if (!matchingTasks.isEmpty()) {
            chatArea.append("Here are the matching tasks in your list:\n");

            for (int i = 0; i < matchingTasks.size(); i++) {
                Task task = matchingTasks.get(i);
                chatArea.append((i + 1) + ". ");
                task.display(chatArea);
            }

        } else {
            chatArea.append("No tasks match the keyword: " + keyword + "\n");
        }
    }
}
