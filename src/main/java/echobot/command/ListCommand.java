package echobot.command;

import java.util.ArrayList;

import echobot.storage.Storage;
import echobot.task.Task;
import javafx.scene.layout.VBox;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command<Task> {
    @Override
    public String doCommand(ArrayList<Task> tasks, Storage storage, VBox dialogContainer) {
        if (tasks.isEmpty()) {
            return "You don't have any tasks yet.";
        }

        StringBuilder responseText = new StringBuilder();

        responseText.append("Here are the tasks in your list:\n");

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            responseText.append("    ").append(i + 1).append(". ").append(task.display()).append("\n");
        }

        return responseText.toString();
    }
}
