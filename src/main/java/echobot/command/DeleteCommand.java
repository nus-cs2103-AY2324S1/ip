package echobot.command;

import java.util.ArrayList;

import echobot.storage.Storage;
import echobot.task.Task;
import echobot.ui.Ui;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

/**
 * Represents a command to delete a task from the list.
 */
public class DeleteCommand extends Command {
    private int taskNum;
    private String responseText;

    /**
     * Constructs a DeleteCommand instance.
     *
     * @param taskNum The task number to be deleted.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public String doCommand(ArrayList<Task> tasks, Ui ui, Storage storage, Scene scene, VBox dialogContainer) {
        if (taskNum >= 1 && taskNum <= tasks.size()) {
            Task deletedTask = tasks.remove(taskNum - 1);

            // Format the EchoBot's response with a prefix
            responseText = "Noted. I've removed this task:\n";
            responseText += deletedTask.display() + "\n";
            responseText += "Now you have " + tasks.size() + " tasks in the list.\n";

            storage.saveTasks(tasks, dialogContainer); // Save after deleting
        }

        return responseText;
    }

    public String getResponse() {
        return responseText;
    }
}
