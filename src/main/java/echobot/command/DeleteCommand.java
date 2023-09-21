package echobot.command;

import java.util.ArrayList;

import echobot.storage.Storage;
import echobot.task.Task;
import javafx.scene.layout.VBox;

/**
 * Represents a command to delete a task from the list.
 */
public class DeleteCommand extends Command<Task> {
    private int taskNum;
    private String responseText;
    private String indent2Spaces = "  ";

    /**
     * Constructs a DeleteCommand instance.
     *
     * @param taskNum The task number to be deleted.
     */
    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public String doCommand(ArrayList<Task> tasks, Storage storage, VBox dialogContainer) {
        if (taskNum <= 0) {
            responseText = "Sorry, the task doesn't exist.";
        } else if (taskNum >= 1 && taskNum <= tasks.size()) {
            Task deletedTask = tasks.remove(taskNum - 1);

            responseText = "Noted. I've removed this task:\n";
            responseText += indent2Spaces + deletedTask.display() + "\n";
            responseText += "Now you have " + tasks.size() + " tasks in the list.\n";

            storage.saveTasks(tasks, dialogContainer); // Save after deleting
        } else {
            responseText = "Sorry, you only have " + tasks.size() + " tasks in your list.\n";
        }

        return responseText;
    }

    public String getResponse() {
        return responseText;
    }
}
