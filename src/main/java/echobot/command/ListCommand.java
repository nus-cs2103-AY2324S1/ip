package echobot.command;

import echobot.storage.Storage;
import echobot.task.Task;
import echobot.ui.Ui;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand extends Command {
    @Override
    public String doCommand(ArrayList<Task> tasks, Ui ui, Storage storage, Scene scene, VBox dialogContainer) {
        StringBuilder responseText = new StringBuilder();

        responseText.append("Here are the tasks in your list:\n");

            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                responseText.append("    ").append(i + 1).append(". ").append(task.display()).append("\n");
            }

            return responseText.toString();
        }
}
