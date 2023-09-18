package echobot.command;

import java.util.ArrayList;

import echobot.storage.Storage;
import echobot.task.Deadline;
import echobot.task.Event;
import echobot.task.Task;
import javafx.scene.layout.VBox;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command<Task> {
    private int taskNum;
    private String responseText;

    /**
     * Constructs a MarkCommand instance.
     *
     * @param taskNum The task number to be marked as done.
     */
    public MarkCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public String doCommand(ArrayList<Task> tasks, Storage storage, VBox dialogContainer) {
        if (taskNum >= 1 && taskNum <= tasks.size()) {
            Task task = tasks.get(taskNum - 1);

            task.mark();

            responseText = "Nice! I've marked this task as done:\n";
            responseText += "[" + task.getStatusIcon() + "] " + task.getDescription();

            // Use type casting for specific task types to access additional information
            if (task instanceof Event) {
                responseText += " (from: " + ((Event) task).getStart() + " to: " + ((Event) task).getEnd() + ")";
            } else if (task instanceof Deadline) {
                responseText += " (by: " + ((Deadline) task).getDueDate() + ")";
            }

            assert storage != null : "Storage should not be null.";
            storage.saveTasks(tasks, dialogContainer); // Save after marking
        }

        return responseText;
    }

    public String getResponse() {
        return responseText;
    }

}
